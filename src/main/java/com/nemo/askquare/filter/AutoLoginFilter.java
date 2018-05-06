package com.nemo.askquare.filter;

import com.nemo.askquare.model.Account;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nemo.askquare.Constants.AUTOLOGIN_COOKIE;
import static com.nemo.askquare.Constants.CURRENT_ACCOUNT;

@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter extends AbstractFilter {

//    private CommonService commonService;
//
//    @Override
//    public void init(FilterConfig config) {
//        commonService = ServiceManager.getInstance(config.getServletContext()).getCommonService();
//    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (req.getSession().getAttribute(CURRENT_ACCOUNT) == null) {
            if (req.getCookies() != null) {
                for (Cookie cookie : req.getCookies()) {
                    if (cookie.getName().equals(AUTOLOGIN_COOKIE)) {
                        String token = cookie.getValue();
                        Account account = getCommonService().findByRememberMeToken(token);
                        if (account != null) {
                            account.setRoleId(getCommonService().findAccountRole(account.getId()).getRoleId());
                            req.getSession().setAttribute(CURRENT_ACCOUNT, account);
                        }
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }
}
