package com.cn.utils;


import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class SendMailTest {


    public static void main(String[] args) {
        final String username = "huangzhijuan@inke.cn";
                 final String password = "Hzj-Inke0514";

                 Properties props = new Properties();
                 props.put("mail.smtp.auth", "true");
                 props.put("mail.smtp.starttls.enable", "true");
                 props.put("mail.smtp.host", "mail.inke.cn");
                 props.put("mail.smtp.port", "465");

                 Session session = Session.getInstance(props, new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication("huangzhijuan", password);
             }
           });

                 try {
                         Message message = new MimeMessage(session);
                         message.setFrom(new InternetAddress("huangzhijuan@inke.cn"));
                         message.setRecipients(Message.RecipientType.TO,
                                 InternetAddress.parse("huhaili@inke.cn"));
                         message.setSubject("Testing Subject");
                         message.setText("Dear Mail Crawler,"
                                     + "\n\n No spam to my email, please!");

                        Transport.send(message);
                         System.out.println("Done");

                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }
    }

}
