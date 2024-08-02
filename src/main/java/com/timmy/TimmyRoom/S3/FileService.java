package com.timmy.TimmyRoom.S3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.timmy.TimmyRoom.entity.File;
import com.timmy.TimmyRoom.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 실패");
        }

        File file = File.builder()
                .id(key)
                .contentType(multipartFile.getContentType())
                .size(multipartFile.getSize())
                .name(multipartFile.getOriginalFilename())
                .build();

        return fileRepository.save(file);
    }

    private String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(fileName);
    }
}
