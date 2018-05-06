package com.nemo.askquare.filter;

import com.nemo.askquare.Constants;
import com.nemo.askquare.model.Account;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "IsLoggedInFilter")
public class IsLoggedInFilter extends AbstractFilter {

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
        String requestedURL = req.getRequestURI();

        if (account == null && isProtectedURL(requestedURL)) {
            resp.sendRedirect("/login");
        } else {
            chain.doFilter(req, resp);
        }
    }

    private boolean isProtectedURL(String requestedURL) {
        if (requestedURL.startsWith("/admin/")) {
            return true;
        }
        if (requestedURL.startsWith("/tutor/")) {
            return true;
        }
        if (requestedURL.startsWith("/advanced-tutor/")) {
            return true;
        }
        if (requestedURL.startsWith("/student/")) {
            return true;
        }
        return false;
    }

}
