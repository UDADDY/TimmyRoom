package com.timmy.TimmyRoom.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FcmMessageDTO {
    private boolean validateOnly;
    private FcmMessageDTO.Message message;

    @Data
    public static class Message{
        private FcmMessageDTO.Notification notification;
        private String token;

        @Builder
        public Message(Notification notification, String token) {
            this.notification = notification;
            this.token = token;
        }
    }

    @Data
    public static class Notification{
        private String title;
        private String body;
        private String image;

        @Builder
        public Notification(String title, String body, String image) {
            this.title = title;
            this.body = body;
            this.image = image;
        }
    }

    @Builder
    public FcmMessageDTO(boolean validateOnly, Message message) {
        this.validateOnly = validateOnly;
        this.message = message;
    }
}
