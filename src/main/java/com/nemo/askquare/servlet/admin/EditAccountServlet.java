package com.nemo.askquare.servlet.admin;

import com.nemo.askquare.model.Account;
import com.nemo.askquare.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditAccountServlet", urlPatterns = "/admin/edit-account")
public class EditAccountServlet extends AbstractServlet {

    private Account account;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("id"));
        account = getCommonService().findById(accountId);
        req.setAttribute("account", account);
        req.getRequestDispatcher("/WEB-INF/view/admin/edit-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String lastName = req.getParameter("lastName");
        if (!(account.getLogin().equals(login) && account.getFirstName().equals(firstName) &&
                account.getSecondName().equals(secondName) && account.getLastName().equals(lastName) &&
                (account.getPassword().equals(password) || password.length() == 0))) {
            if (password.length() == 0) password = account.getPassword();
            getAdminService().updateAccount(login, password, firstName, secondName, lastName, account.getId());
        }
        resp.sendRedirect("/admin/accounts-list");
    }
}
