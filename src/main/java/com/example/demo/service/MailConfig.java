package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Slf4j
@Configuration
public class MailConfig {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${proxy.active:false}")
    private boolean isProxyActive;

   /* @Value("${proxy.host}")
    private String proxyHost;

    @Value("${proxy.port}")
    private Integer proxyPort;*/

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setProtocol("smtp");
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);

        Properties mailProps = new Properties();
        mailProps.put("mail.smtps.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.debug", "true");
        mailProps.put("mail.smtp.ssl.trust", host);
      /*  if (isProxyActive) {
            mailProps.put("mail.smtp.proxy.host", proxyHost);
            mailProps.put("mail.smtp.proxy.port", proxyPort);
        }*/
        sender.setJavaMailProperties(mailProps);
        log.debug("output :: sender :: {} ", sender);
        return sender;
    }
}
