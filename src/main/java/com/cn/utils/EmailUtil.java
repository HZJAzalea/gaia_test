package com.cn.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class EmailUtil {

    public static boolean SendEmail(String sender,String password,String host,String port,String receiver)
    {

        try{

            Properties props = new Properties();
            // 开启debug调试
            props.setProperty("mail.debug", "true");  //false
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
//            props.setProperty("mail.host", host);
            props.setProperty("mail.smtp.host",host);
            // 发送邮件协议名称 这里使用的是smtp协议
            props.setProperty("mail.transport.protocol", "smtp");
            // 服务端口号
            props.setProperty("mail.smtp.port", port);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required","true");

            props.put("mail.smtp.ssl.trust",host);
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.socketFactory.fallback","false");


            // 设置环境信息
            Session session = Session.getInstance(props);

            // 创建邮件对象
            MimeMessage msg = new MimeMessage(session);

            // 设置发件人
            msg.setFrom(new InternetAddress(sender));

            // 设置收件人
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            // 设置邮件主题
            msg.setSubject("this is subject");

            // 设置邮件内容
            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();

            //发送邮件的文本内容
            textPart.setText("this is the text");
            multipart.addBodyPart(textPart);

            // 添加附件
            MimeBodyPart attachPart = new MimeBodyPart();
            //可以选择发送文件...
            //DataSource source = new FileDataSource("C:\\Users\\36268\\Desktop\\WorkSpace\\MyApp\\Program.cs");
            //attachPart.setDataHandler(new DataHandler(source));
            //设置文件名
            //attachPart.setFileName("Program.cs");
            multipart.addBodyPart(attachPart);

            msg.setContent(multipart);

            Transport transport = session.getTransport();

            // 连接邮件服务器
            transport.connect(sender, password);
            // 发送邮件
            transport.sendMessage(msg,new Address[]{new InternetAddress(receiver)});
            // 关闭连接
            transport.close();

            return true;
        }catch( Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

        String sender = "huangzhijuan@inke.cn";
        String password = "Hzj-Inke0514"; //填写你的outlook帐户的密码

        // 收件人邮箱地址
        String receiver = "huhaili@inke.cn";

        // office365 邮箱服务器地址及端口号
        //这个就是之前的Server Name，注意：你使用的Outlook应用可能使用了不同的服务器，根据自己刚才拿到的地址为准
        String host = "mail.inke.cn";//mail.inke.cn 192.168.3.18
        String port = "465";    //这个就是拿到的port
        boolean b = SendEmail(sender, password, host, port, receiver);
        if(b)
        {
            System.out.println("发送成功");
        }else
        {
            System.out.println("发送失败");
        }
    }

}
