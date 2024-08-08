package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.MuseumGetReqeustDTO;
import com.timmy.TimmyRoom.service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/museum")
public class MuseumController {
    private final MuseumService museumService;

    @PostMapping("/near")
    public ResponseEntity<?> getMuseumByDistance(@RequestBody MuseumGetReqeustDTO museumGetReqeustDTO){
        return ResponseEntity.ok(museumService.getNearByMuseum(museumGetReqeustDTO));
    }
}
