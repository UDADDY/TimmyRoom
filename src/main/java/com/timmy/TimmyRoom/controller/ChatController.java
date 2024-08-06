package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.entity.ChatRoom;
import com.timmy.TimmyRoom.dto.request.ChatRoomCreateRequestDTO;
import com.timmy.TimmyRoom.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<?> getAllChatRooms(){
        List<ChatRoom> allRomms = chatService.findAllRomms();

        return ResponseEntity.ok(allRomms);
    }

    @PostMapping
    public ResponseEntity<?> createChatRoom(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ChatRoomCreateRequestDTO chatRoomCreateRequestDTO
    ){
        ChatRoom chatRoom = chatService.createChatRoom(chatRoomCreateRequestDTO.getChatRoomName(), userDetails.getUsername());

        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getChatRoom(@PathVariable("roomId") String roomId){
        ChatRoom chatRoom = chatService.findRoomById(roomId);

        return ResponseEntity.ok(chatRoom);
    }
}
