package com.nemo.askquare.filter;

import com.nemo.askquare.service.AdminService;
import com.nemo.askquare.service.CommonService;
import com.nemo.askquare.service.impl.ServiceManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {

    private ServiceManager serviceManager;

    @Override
    public final void init(FilterConfig config) throws ServletException {
        serviceManager = ServiceManager.getInstance(config.getServletContext());
        initFilter();
    }

    protected void initFilter() {
    }

    //made final to prevent calling this abstract type of method. http-version of doFilter() is the one to be called
    public final void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        doFilter(httpReq, httpResp, chain);
    }

    public CommonService getCommonService() {
        return serviceManager.getCommonService();
    }

    public AdminService getAdminService() {
        return serviceManager.getAdminService();
    }

    public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException;

    @Override
    public void destroy() {
    }

}
