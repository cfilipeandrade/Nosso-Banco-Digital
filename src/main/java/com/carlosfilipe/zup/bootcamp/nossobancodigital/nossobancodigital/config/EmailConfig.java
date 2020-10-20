package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:env/mail.properties")
public class EmailConfig {

    @Autowired
    private Environment enviar;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(enviar.getProperty("mail.smtp.host"));
        mailSender.setPort(enviar.getProperty("mail.smtp.port", Integer.class));
        mailSender.setUsername(enviar.getProperty("mail.smtp.username"));
        mailSender.setPassword(enviar.getProperty("mail.smtp.password"));

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.connectiontimeout", 10000);

        mailSender.setJavaMailProperties(props);

        
        return mailSender;

    }

    
}
