package com.nemo.askquare.servlet;

import com.nemo.askquare.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        Cookie cookie = new Cookie(Constants.AUTOLOGIN_COOKIE, null);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect("/login");
    }
}
