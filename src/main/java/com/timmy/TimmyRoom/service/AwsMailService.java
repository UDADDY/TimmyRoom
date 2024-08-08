package com.timmy.TimmyRoom.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.timmy.TimmyRoom.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AwsMailService {
    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void send(){
        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses("udaddy@naver.com"))
                .withMessage(new Message()
                        .withBody(new Body().withText(new Content().withCharset("UTF-8").withData("테스트입니다.")))
                        .withSubject(new Content().withCharset("UTF-8").withData("테스트 제목")))
                .withSource("gustmd5715@gmail.com");
        amazonSimpleEmailService.sendEmail(request);
    }
}
