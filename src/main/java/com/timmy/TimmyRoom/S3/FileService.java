package com.timmy.TimmyRoom.S3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.timmy.TimmyRoom.entity.File;
import com.timmy.TimmyRoom.exception.FileNotFoundException;
import com.timmy.TimmyRoom.repository.FileRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public File uploadFile(MultipartFile multipartFile, String email) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        String key = email + "/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());

        // S3에 업로드
        try(InputStream inputStream = multipartFile.getInputStream()){
            amazonS3Client.putObject(bucket, key, inputStream, metadata);
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 실패");
        }

        File file = File.builder()
                .id(key)
                .contentType(multipartFile.getContentType())
                .size(multipartFile.getSize())
                .name(multipartFile.getOriginalFilename())
                .build();

        return fileRepository.save(file);
    }

    public ResponseEntity<?> downloadFileBlob(String id){
        File file = fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException("파일이 없습니다."));
        String downloadFileName = file.getName();

        try (S3Object s3Object = amazonS3Client.getObject(bucket, file.getId()); S3ObjectInputStream objectInputStream = s3Object.getObjectContent()){
            byte[] bytes = IOUtils.toByteArray(objectInputStream);

//            String fileName = makeFileName(Objects.requireNonNullElse(downloadFileName, file.getId()));
            String fileName = downloadFileName;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(file.getContentType()));
            headers.setContentLength(bytes.length);
            headers.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e){
            log.debug(e.getMessage(), e);
            throw new RuntimeException("파일 다운로드 실패");
        }
    }

    private String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(fileName);
    }
}
