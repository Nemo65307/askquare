package com.nemo.askquare.servlet;

import com.nemo.askquare.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmAccountServlet", urlPatterns = "/confirm")
public class ConfirmAccountServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        Long idAccount = getCommonService().findByCode(code).getAccountId();
        Account account = getCommonService().findById(idAccount);
        getCommonService().updateActive(true, account.getId());
        System.out.println("Account" + account + "has been confirmed and activated!");
        resp.sendRedirect("/login");
    }

}
