package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.MuseumLikeRequestDTO;
import com.timmy.TimmyRoom.entity.Museum;
import com.timmy.TimmyRoom.entity.UserMuseum;
import com.timmy.TimmyRoom.service.UserMuseumService;
import com.timmy.TimmyRoom.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "유저")
public class UserController {

    private final UserService userService;
    private final UserMuseumService userMuseumService;


    @PostMapping("/likeMuseum")
    public ResponseEntity<?> likeMuseum(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MuseumLikeRequestDTO museumLikeRequestDTO
    ){
        UserMuseum userMuseum = userMuseumService.likeMuseum(userDetails.getUsername(), museumLikeRequestDTO.getMuseumId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/likeMuseum")
    public ResponseEntity<?> getLikedMuseum(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<Museum> museums = userMuseumService.getMuseumByEmail(userDetails.getUsername());
        return ResponseEntity.ok(museums);
    }
}
