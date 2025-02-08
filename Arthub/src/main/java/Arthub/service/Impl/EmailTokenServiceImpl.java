package Arthub.service.Impl;

import Arthub.service.EmailTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;


@Service

public class EmailTokenServiceImpl implements EmailTokenService {

    private final JavaMailSender javaMailSender;
    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public EmailTokenServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String generateAndSendToken(String email) {

        String token = String.valueOf(100000+secureRandom.nextInt(999999)) ;

        String subject = "Your Verification Token";
        String body = "Dear user,\n\nYour verification token is: " + token + "\n\nRegards,\nArtHub Team";

        sendEmail(email, subject, body);

        return token;
    }


    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}