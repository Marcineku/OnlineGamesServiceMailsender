package pl.edu.wat.wcy.pz.project.mailsender.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendHtmlMessage(String to, String subject, String message);

    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String... templateArgs);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
