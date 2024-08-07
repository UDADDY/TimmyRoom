package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.entity.ChatRoom;
import com.timmy.TimmyRoom.excpetion.ChatRoomNotFound;
import com.timmy.TimmyRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAllChatRooms(){
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(Long id){
        return chatRoomRepository.findById(id).orElseThrow(() -> new ChatRoomNotFound());
    }

    public ChatRoom createChatRoom(String chatRoomName, String host){
        ChatRoom chatRoom = ChatRoom.builder()
                .host(host)
                .name(chatRoomName)
                .build();

        return chatRoomRepository.save(chatRoom);
    }
}
