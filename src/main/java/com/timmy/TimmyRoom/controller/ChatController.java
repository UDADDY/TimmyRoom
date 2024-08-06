package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.ChatRoomDTO;
import com.timmy.TimmyRoom.entity.ChatRoom;
import com.timmy.TimmyRoom.dto.request.ChatRoomCreateRequestDTO;
import com.timmy.TimmyRoom.gloabl.error.ErrorResponse;
import com.timmy.TimmyRoom.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "채팅방")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    @Operation(summary = "모든 채팅방 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "조회 성공",
                            responseCode = "200",
                            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChatRoomDTO.class)))}
                    ),
                    @ApiResponse(
                            description = "사용자 인증 실패",
                            responseCode = "401",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    public ResponseEntity<List<ChatRoomDTO>> getAllChatRooms(){
        List<ChatRoom> allChatRooms = chatService.findAllRomms();
        List<ChatRoomDTO> chatRoomDTOS = allChatRooms.stream().map(chatRoom ->
                ChatRoomDTO.fromEntity(chatRoom)).toList();

        return ResponseEntity.ok(chatRoomDTOS);
    }

    @PostMapping
    public ResponseEntity<?> createChatRoom(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid ChatRoomCreateRequestDTO chatRoomCreateRequestDTO
    ){
        ChatRoom chatRoom = chatService.createChatRoom(chatRoomCreateRequestDTO.getChatRoomName(), userDetails.getUsername());
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.fromEntity(chatRoom);

        return ResponseEntity.ok(chatRoomDTO);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getChatRoom(@PathVariable("roomId") String roomId){
        ChatRoom chatRoom = chatService.findRoomById(roomId);
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.fromEntity(chatRoom);

        return ResponseEntity.ok(chatRoomDTO);
    }
}
