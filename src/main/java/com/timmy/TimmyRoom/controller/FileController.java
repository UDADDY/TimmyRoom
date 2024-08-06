package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.gloabl.error.ErrorResponse;
import com.timmy.TimmyRoom.service.FileService;
import com.timmy.TimmyRoom.entity.File;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/file")
@Tag(name = "파일")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    @Operation(summary = "파일 업로드")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "업로드 성공",
                            responseCode = "201",
                            content = {@Content(schema = @Schema(implementation = File.class))}
                    ),
                    @ApiResponse(
                            description = "사용자 인증 실패",
                            responseCode = "401",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            description = "S3 업로드 실패",
                            responseCode = "500",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
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
