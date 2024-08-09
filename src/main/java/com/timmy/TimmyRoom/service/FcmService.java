package com.timmy.TimmyRoom.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.timmy.TimmyRoom.dto.request.FcmMessageDTO;
import com.timmy.TimmyRoom.dto.request.FcmSendDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FcmService {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    // Firebase SDK 사용한 메서드
    public String sendMessage(FcmSendDTO fcmSendDTO){
        Message message = Message.builder()
                .putData("title", fcmSendDTO.getTitle())
                .putData("content", fcmSendDTO.getBody())
                .setToken(fcmSendDTO.getToken())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            return response;
        } catch (FirebaseMessagingException e){
            e.printStackTrace();
            return "FAIL";
        }
    }

    public int sendMessageTo(FcmSendDTO fcmSendDTO) throws IOException{
        String message = makeMessage(fcmSendDTO);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity entity = new HttpEntity<>(message, headers);
        String API_URL = "<https://fcm.googleapis.com/v1/projects/timmyroom-8d5dd/messages:send>";
        ResponseEntity response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        log.debug("Fcm 응답값: {}", response.getStatusCode());

        return response.getStatusCode() == HttpStatus.OK ? 1 : 0;
    }

    private String getAccessToken() throws IOException{
        String firebaseConfigPath = "firebase/timmyroom-8d5dd-firebase-adminsdk-yaj9u-6fb31e4259.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("<https://www.googleapis.com/auth/cloud-platform>"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    private String makeMessage(FcmSendDTO fcmSendDTO) throws JsonProcessingException {
        FcmMessageDTO fcmMessageDTO = FcmMessageDTO.builder()
                .message(FcmMessageDTO.Message.builder()
                        .token(fcmSendDTO.getToken())
                        .notification(FcmMessageDTO.Notification.builder()
                                .title(fcmSendDTO.getTitle())
                                .body(fcmSendDTO.getBody())
                                .image(null)
                                .build())
                        .build()
                ).validateOnly(false)
                .build();

        return mapper.writeValueAsString(fcmMessageDTO);
    }
}
