package com.nemo.askquare.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class ApplicationSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se.getSession().getId() + " session created");

    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(se.getSession().getId() + " session destroyed");
    }
}
