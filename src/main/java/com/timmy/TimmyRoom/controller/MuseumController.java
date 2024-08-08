package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.repository.MuseumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/museum")
public class MuseumController {
    private final MuseumRepository museumRepository;

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok(museumRepository.findAll());
    }
}
