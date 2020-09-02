package com.bridgelabz.usermanagement.listener;

import com.bridgelabz.usermanagement.model.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class EmailListener {

    @Autowired
    JavaMailSender javaMailSender;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @RabbitListener(queues = "${rabbitmqQueue}")
    public void recievedMessage(Email email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(email.getId());
        helper.setText(email.getMessage());
        helper.setSubject(email.getSubject());
        executorService.submit(() -> {
            try {
                javaMailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
