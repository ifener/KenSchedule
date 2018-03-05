package com.wey.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-core.xml")
public class MailSendTest {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Test
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("15024152104@163.com");
        message.setCc("15024152104@163.com");
        message.setTo("357452491@qq.com");
        message.setSubject("7亿中国人都被这个坏习惯害了！好多人仍知错不改");
        message.setText("然而，很多烟民并不以为意，有人还会故作幽默地来一句「吓得我赶紧抽支烟压压惊」。");
        
        mailSender.send(message); 
        
        
    }
    
}
