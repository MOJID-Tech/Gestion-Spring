package com.example.demo.service;

import com.example.demo.log.LogArgumentsAndResult;
import com.example.demo.utils.MailType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class MailService {
    @Lazy
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean send(MailType type, String to, String[] data) {
        return send(type, to, null, data);
    }

    @LogArgumentsAndResult
    public boolean send(MailType type, String to, File attachment, String[] data) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(to);
            helper.setSubject(type.getSubject());
            String content = String.format(type.getContent(), data);

            content = "<p>Bonjour,</p><p>" + content + "</p><p>Cordialement.</p>";

            helper.setText(content, true);
            if (attachment != null) {
                helper.addAttachment(attachment.getName(), attachment);
            }
            javaMailSender.send(msg);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}

