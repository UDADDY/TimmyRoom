package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.MuseumGetReqeustDTO;
import com.timmy.TimmyRoom.repository.MuseumRepository;
import com.timmy.TimmyRoom.service.MuseunService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/museum")
public class MuseumController {
    private final MuseunService museunService;

    @PostMapping("/near")
    public ResponseEntity<?> getMuseumByDistance(@RequestBody MuseumGetReqeustDTO museumGetReqeustDTO){
        return ResponseEntity.ok(museunService.getNearByMuseum(museumGetReqeustDTO));
    }
}
