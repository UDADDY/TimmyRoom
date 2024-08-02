package com.timmy.TimmyRoom.S3;

import com.timmy.TimmyRoom.entity.File;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("file") MultipartFile multipartFile
    ){
        File file = fileService.uploadFile(multipartFile, userDetails.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(file);
    }

    @GetMapping("/download")
    public ResponseEntity<String> downloadFile(){
        return null;
    }


}
