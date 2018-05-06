package com.nemo.askquare.servlet.admin;

import com.nemo.askquare.model.Account;
import com.nemo.askquare.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeActiveServlet", urlPatterns = "/admin/change-active")
public class ChangeActiveServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("id"));
        Account account = getCommonService().findById(accountId);
        boolean isActive = account.getActive();
        getCommonService().updateActive(!isActive, account.getId());
        resp.sendRedirect("/admin/accounts-list");
    }
}
