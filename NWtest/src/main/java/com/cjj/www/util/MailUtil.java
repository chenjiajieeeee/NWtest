package com.cjj.www.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil implements Runnable{
    private String mail;
    private String activateCode;

    public MailUtil() {
    }

    public MailUtil(String mail, String activateCode) {
        this.mail = mail;
        this.activateCode = activateCode;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }
    public void run(){
           //1.创建连接对象
           //2.创建邮件对象
           //3.发送邮件
          String from="2868203618@qq.com";
          //指定发送邮件的主机
         String host="smtp.qq.com";
         //获取系统属性
        Properties properties=System.getProperties();
        properties.setProperty("mail.smtp.host",host);
        properties.setProperty("mail.smtp.auth","true");

        try {
            MailSSLSocketFactory sslSocketFactory=new MailSSLSocketFactory();
            sslSocketFactory.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sslSocketFactory);

            //获取默认session对象
            Session session=Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,"fmbxwvmrkosbdcgd");

                }
            });

            //创建邮件对象
            Message message=new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress(from));
            //设置接收人
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail));
            //设置邮件主题
            message.setSubject("账号激活验证码");
            //设置邮件内容
            String context="<html><head></head><body><h1>这是一封激活邮件,激活码如下：</h1><h3>"+activateCode+"</h3></body></html>";
            //发送邮件
            message.setContent(context,"text/html;charset=UTF-8");
            Transport.send(message);
        } catch (GeneralSecurityException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
