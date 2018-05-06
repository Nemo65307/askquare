package com.nemo.askquare.servlet;

import com.nemo.askquare.exception.ValidationException;
import com.nemo.askquare.form.SignupForm;
import com.nemo.askquare.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignupServlet", urlPatterns = "/signup")
public class SignupServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignupForm form = createForm(SignupForm.class, req);
        try {
            Account account = getCommonService().signup(form);
            System.out.println(account + " has been successfully created!");
            getCommonService().createConfirmation(account, req.getRequestURL().toString());
            System.out.println("Confirmation has been sent to " + account.getEmail());
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            forwardToPage("signup.jsp", req, resp);
        }
    }

}
