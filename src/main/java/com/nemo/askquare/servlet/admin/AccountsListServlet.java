package com.nemo.askquare.servlet.admin;

import com.nemo.askquare.model.Account;
import com.nemo.askquare.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountsListServlet", urlPatterns = "/admin/accounts-list")
public class AccountsListServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = 30;
        int offset;
        int currentPage = 1;
        if (req.getParameter("page") == null) {
            offset = 0;
        } else {
            currentPage = Integer.valueOf(req.getParameter("page"));
            offset = limit * (currentPage - 1);
        }
        setPages(req, currentPage, limit);
        List<Account> accountList = getAdminService().findAllAccountsOffset(limit, offset);
        req.setAttribute("accountList", accountList);
        forwardToPage("admin/accounts-list.jsp", req, resp);
    }

    private void setPages(HttpServletRequest req, int currentPage, int limit) { //TODO replace the hardcode
        long accountsNum = getCommonService().count();
        int maxPages = (int) Math.ceil(accountsNum / (double) limit);
        if (currentPage == 1) {
            req.setAttribute("page1", currentPage);
            req.setAttribute("page2", currentPage + 1);
            req.setAttribute("page3", currentPage + 2);
        } else if (currentPage > 0 && currentPage < maxPages) {
            req.setAttribute("page1", currentPage - 1);
            req.setAttribute("page2", currentPage);
            req.setAttribute("page3", currentPage + 1);
        }
        req.setAttribute("firstPage", 1);
        req.setAttribute("lastPage", maxPages);
    }

}
