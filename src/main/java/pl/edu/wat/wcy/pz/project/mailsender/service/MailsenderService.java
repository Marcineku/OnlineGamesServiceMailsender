package pl.edu.wat.wcy.pz.project.mailsender.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.pz.project.mailsender.dto.EmailDTO;

@AllArgsConstructor
@Service
public class MailsenderService {

    private EmailService emailService;

    private EmailTemplates emailTemplates;

    private static final Logger LOGGER = LoggerFactory.getLogger(MailsenderService.class);

    public void processEmail(EmailDTO dto) {
        switch (dto.getEmailType()) {
            case VERIFICATION_EMAIL:
                LOGGER.info("Sending verification email to address " + dto.getEmailAddress());
                sendVerificationEmail(dto);
                break;
            case PASSWORD_RESET:
                LOGGER.info("Sending password reset email");
                sendPasswordResetEmail(dto);
                break;
            case OTHER:
                LOGGER.info("Sending email to address " + dto.getEmailAddress());
                emailService.sendSimpleMessage(dto.getEmailAddress(), dto.getEmailSubject(), dto.getEmailText());
                break;
        }
    }

    private void sendPasswordResetEmail(EmailDTO dto) {
        String messageBody = emailTemplates.getResetPasswordTemplateBody(dto.getUsername(), dto.getEmailText());
        String subject = emailTemplates.getResetPasswordTemplateSubject();
        emailService.sendHtmlMessage(dto.getEmailAddress(), subject, messageBody);
    }

    private void sendVerificationEmail(EmailDTO dto) {
        String messageBody = emailTemplates.getVerificationEmailBody(dto.getUsername(), dto.getUrl());
        String subject = emailTemplates.getVerificationTemplateSubject();
        emailService.sendHtmlMessage(dto.getEmailAddress(), subject, messageBody);
    }
}
