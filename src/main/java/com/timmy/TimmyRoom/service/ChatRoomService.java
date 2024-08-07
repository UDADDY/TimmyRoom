package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.entity.ChatRoom;
import com.timmy.TimmyRoom.excpetion.ChatRoomNotFound;
import com.timmy.TimmyRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final Map<Long, Set<WebSocketSession>> chatRoomSessions = new HashMap<>();

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

    public Set<WebSocketSession> getSessions(Long chatRoomId){
        return chatRoomSessions.computeIfAbsent(chatRoomId, k -> new HashSet<>());
    }

    public void addSession(Long chatRoomId, WebSocketSession session){
        Set<WebSocketSession> sessions = getSessions(chatRoomId);
        sessions.add(session);
    }

    public void removeSession(Long chatRoomId, WebSocketSession session){
        Set<WebSocketSession> sessions = getSessions(chatRoomId);
        sessions.remove(session);
        if(sessions.isEmpty())
            chatRoomSessions.remove(chatRoomId);
    }
}
