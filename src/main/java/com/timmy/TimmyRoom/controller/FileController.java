package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.service.FileService;
import com.timmy.TimmyRoom.entity.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Slf4j
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

    @PostMapping("/download")
    public ResponseEntity<?> downloadFile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("id") String id
    ){
        log.debug("download: ${}, email: ${}", id, userDetails.getUsername());
        return fileService.downloadFileBlob(id);
    }

    @GetMapping
    public ResponseEntity<List<File>> findAllFileByEmail(@AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();
        List<File> files = fileService.findAllFileByEmail(email);

        return ResponseEntity.ok(files);
    }
}
