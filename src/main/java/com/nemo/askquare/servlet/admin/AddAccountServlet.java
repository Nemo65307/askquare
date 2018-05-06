package com.nemo.askquare.servlet.admin;

import com.nemo.askquare.model.Account;
import com.nemo.askquare.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAccountServlet", urlPatterns = "/admin/add-account")
public class AddAccountServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //TODO make real
        Account account = getAdminService().save(new Account("test", "testpass", "Test",
                "Testtovich", "Test2nd", "test@email"));
        System.out.println(account);
        resp.sendRedirect("/admin/accounts-list");
    }
}
