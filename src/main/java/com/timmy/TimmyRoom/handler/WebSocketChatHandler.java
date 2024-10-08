package com.timmy.TimmyRoom.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmy.TimmyRoom.dto.ChatMessageDTO;
import com.timmy.TimmyRoom.entity.ChatRoom;
import com.timmy.TimmyRoom.entity.MessageType;
import com.timmy.TimmyRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    private final ChatRoomService chatRoomService;

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} 연결됨", session.getId());
    }

    // 소켓 통신 시 메시지의 전송을 다루는 부분
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload: {}", payload);

        // payload -> chatMessageDTO로 변환
        ChatMessageDTO chatMessageDTO = mapper.readValue(payload, ChatMessageDTO.class);
        log.info("session {}", chatMessageDTO.toString());

        Long chatRoomId = chatMessageDTO.getChatRoomId();
        Set<WebSocketSession> chatRoomSessions = chatRoomService.getSessions(chatRoomId);

        if(chatMessageDTO.getMessageType().equals(MessageType.ENTER)){
            chatRoomService.addSession(chatRoomId, session);
            chatMessageDTO.setMessage(chatMessageDTO.getSender() + "님이 입장했습니다.");
        } else if (chatMessageDTO.getMessageType().equals(MessageType.QUIT)) {
            chatRoomService.removeSession(chatRoomId, session);
            chatMessageDTO.setMessage(chatMessageDTO.getSender() + "님이 퇴장했습니다.");
        }
        sendMessageToChatRoom(chatMessageDTO, chatRoomSessions);
    }

    // 소켓 종료 확인
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} 연결 끊김.", session.getId());

        // 세션이 속한 모든 채팅방에서 세션 제거
//        chatRoomService.getSessions().forEach((chatRoomId, sessions) -> sessions.remove(session));
    }

    private void sendMessageToChatRoom(ChatMessageDTO chatMessageDTO, Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.parallelStream().forEach(session -> sendMessage(session, chatMessageDTO));
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            String jsonString = mapper.writeValueAsString(message);
            log.debug("Converted JSON String: ", jsonString);
            session.sendMessage(new TextMessage(jsonString));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}