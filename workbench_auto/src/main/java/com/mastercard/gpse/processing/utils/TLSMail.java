package com.mastercard.gpse.processing.utils;

import javax.mail.Session;
import java.util.Properties;

public class TLSMail {
        public static void main(String[] args) {

            System.out.println("SimpleEmail Start");

            String smtpHostServer = "10.168.129.163";
            String emailID = "user1@mpe.com";

            Properties props = System.getProperties();

            props.put("mail.smtp.host", smtpHostServer);

            Session session = Session.getInstance(props, null);

            //EmailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
        }

}