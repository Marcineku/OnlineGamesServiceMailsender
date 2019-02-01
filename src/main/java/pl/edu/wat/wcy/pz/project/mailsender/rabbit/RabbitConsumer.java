package pl.edu.wat.wcy.pz.project.mailsender.rabbit;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.wcy.pz.project.mailsender.dto.EmailDTO;
import pl.edu.wat.wcy.pz.project.mailsender.service.MailsenderService;

@AllArgsConstructor
@Component
public class RabbitConsumer {

    private MailsenderService mailsenderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue}", containerFactory = "rcFactory")
    public void getMessageFromQueue(EmailDTO dto) {
        LOGGER.info("Message received from queue");
        mailsenderService.processEmail(dto);
    }
}
