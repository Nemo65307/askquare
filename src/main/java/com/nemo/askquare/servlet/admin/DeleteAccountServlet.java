package com.nemo.askquare.servlet.admin;

import com.nemo.askquare.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteAccountServlet", urlPatterns = "/admin/delete-account")
public class DeleteAccountServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAdminService().deleteAccount(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/admin/accounts-list");
    }
}
