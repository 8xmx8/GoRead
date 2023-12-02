package com.yjy.read.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {

    @Autowired
    private JavaMailSender sender;

    public void send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("测试邮件");
        message.setText("邮件内容");
        message.setTo("收件人@qq.com");
        message.setFrom("发件人@qq.com");

        sender.send(message);
    }
}
