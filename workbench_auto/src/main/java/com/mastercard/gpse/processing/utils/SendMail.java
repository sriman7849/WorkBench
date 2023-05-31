package com.mastercard.gpse.processing.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
        public static void main(String[] args) {

            String host="mail.mpe.com";
            final String user="user1@mpe.com";//change accordingly
            final String password="xxxxx";//change accordingly

            String to="sonoojaiswal1987@gmail.com";//change accordingly

            //Get the session object
            Properties props = new Properties();
            props.put("mail.smtp.host",host);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(user,password);
                        }
                    });

            //Compose the message
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
                message.setSubject("javatpoint");
                message.setText("This is simple program of sending email using JavaMail API");

                //send the message
                Transport.send(message);

                System.out.println("message sent successfully...");

            } catch (MessagingException e) {e.printStackTrace();}
        }
    }
