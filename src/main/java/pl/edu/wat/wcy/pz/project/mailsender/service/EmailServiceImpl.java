package pl.edu.wat.wcy.pz.project.mailsender.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender emailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        MimeMessage mail = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false);
            emailSender.send(mail);
        } catch (MailException | MessagingException e) {
            LOGGER.error("Exception in sendSimpleMessage method", e);
        }
    }

    @Override
    public void sendHtmlMessage(String to, String subject, String message) {
        MimeMessage mail = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, false, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            emailSender.send(mail);
        } catch (MessagingException e) {
            LOGGER.error("Exception in sendHtmlMessage method", e);
        }
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String... templateArgs) {
//        String text = String.format(template.getText(), templateArgs);
//        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

    }
}