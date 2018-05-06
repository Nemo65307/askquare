package com.nemo.askquare.listener;

import com.nemo.askquare.service.impl.ServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ApplicationListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ApplicationListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        ServiceManager.getInstance(sce.getServletContext());
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        ServiceManager.getInstance(sce.getServletContext()).shutdown();
    }

}
