package com.nemo.askquare.servlet;

import com.nemo.askquare.exception.ValidationException;
import com.nemo.askquare.form.LoginForm;
import com.nemo.askquare.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nemo.askquare.Constants.AUTOLOGIN_COOKIE;
import static com.nemo.askquare.Constants.CURRENT_ACCOUNT;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(CURRENT_ACCOUNT) != null) {
            Account account = (Account) req.getSession().getAttribute(CURRENT_ACCOUNT);
            resp.sendRedirect(pageToRedirect(account.getRoleId()));
        } else {
            req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginForm form = createForm(LoginForm.class, req); //TODO add the same form logic to other servlets
        try {
            Account account = getCommonService().login(form);
            req.getSession().setAttribute(CURRENT_ACCOUNT, account);
            if (form.isRememberMe()) {
                String token = getCommonService().generateRememberMeToken(account);
                Cookie cookie = new Cookie(AUTOLOGIN_COOKIE, token);
                cookie.setMaxAge(60 * 60 * 24 * 3); // seconds. 3 days expiring age
                resp.addCookie(cookie);
            }
            System.out.println(account + " has successfully logged in with role id=" + account.getRoleId());
            resp.sendRedirect(pageToRedirect(form.getRole()));
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            forwardToPage("login.jsp", req, resp);
        }
    }

    private String pageToRedirect(int role) {
        switch (role) {
            case 1:
                return "/admin/accounts-list";
            case 4:
                return "/student/tests-list";
            default:
                return "/home";
        }
    }
}
