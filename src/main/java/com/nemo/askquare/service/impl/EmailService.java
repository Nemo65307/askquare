package com.nemo.askquare.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public final class EmailService {

    private static final String HOST = "smtp.mail.yahoo.com";
    private static final int PORT = 465;
    private static final boolean SSL_FLAG = true;

    private EmailService() {
    }

    public static void sendConfirmation(String emailAddress, String subject, String message) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(HOST);
        email.setSmtpPort(PORT);
        email.setAuthenticator(new DefaultAuthenticator("askquare@yahoo.com", "pswdaskqr"));
        email.setSSLOnConnect(SSL_FLAG);
        email.setFrom("askquare@yahoo.com");
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(emailAddress);
        email.send();
    }

}
