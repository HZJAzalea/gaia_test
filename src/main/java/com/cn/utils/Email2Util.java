package com.cn.utils;

import javax.mail.*;
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
public class Email2Util {
    public static String USER_NAME = "huangzhijuan@inke.cn";
    public static String PASSWORD = "Hzj-Inke0514";

    public static boolean sendSMTPMail(String to, String text, String title) {
        String host = "192.168.3.18";//mail.inke.cn
        String mailStoreType = "smtp";
        String popPort = "465";
        final Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);//mail.smtp.host
        props.put("mail.store.protocol", mailStoreType);
        props.put( "mail.smtp.port", popPort );
        //设置tls认证
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required","true");
        //开启ssl认证
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.socketFactory.port",popPort);
        props.put("mail.smtp.socketFactory.fallback","false");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        props.put("mail.smtp.ssl.trust",host);

//        props.put("mail.debug","true");//启用调试
//        props.put("mail.smtp.timeout","1000");//设置链接超时

        try {
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER_NAME, PASSWORD);//账号密码
                }
            });
            session.setDebug(true);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            // 设置发件人
            InternetAddress form = new InternetAddress(USER_NAME);
            message.setFrom(form);
            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");

//            Transport transport = session.getTransport();
//            transport.connect(USER_NAME,PASSWORD);
            // 发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String to = "huhaili@inke.cn";
        String text = "你好！";
        String title = "test";
        sendSMTPMail(to,text,title);
    }
}
