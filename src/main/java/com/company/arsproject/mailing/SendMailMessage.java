package com.company.arsproject.mailing;

import com.company.arsproject.dto.flights.FlightReadDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMailMessage {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Async
    public void sendNewFlightToMail(FlightReadDto dto, String username, String subjectMessage, String templateName) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom("noreply@gmail.com");
            messageHelper.setTo("%s@mail.ru".formatted(username));
            messageHelper.setSubject(subjectMessage);
            Template template = configuration.getTemplate(templateName);
            var map = Map.of("flight", dto);
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            messageHelper.setText(htmlContent, true);
            javaMailSender.send(message);
            log.info("Message sent successfully!!!");
        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
