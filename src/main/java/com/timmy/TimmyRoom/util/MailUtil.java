package com.timmy.TimmyRoom.util;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailUtil {

    public static SendRawEmailRequest getSendRawEmailRequest(String title, String content, String receiver, String html)
            throws MessagingException, IOException {
        // title : 메일 제목
        // content : 안에 내용
        // receiver : 받는 사람
        // html : 이메일 템플릿
        // fileRoot : 파일 경로
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);

        message.setSubject(title);
        message.setFrom("gustmd5715@gmail.com");
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        message.setText(content);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);
        RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
        return new SendRawEmailRequest(rawMessage);
    }
}
