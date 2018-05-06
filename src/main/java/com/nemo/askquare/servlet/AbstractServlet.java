package com.nemo.askquare.servlet;

import com.nemo.askquare.exception.ApplicationException;
import com.nemo.askquare.service.AdminService;
import com.nemo.askquare.service.CommonService;
import com.nemo.askquare.service.impl.ServiceManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;


public abstract class AbstractServlet extends HttpServlet {

    private ServiceManager serviceManager;

    //made final to prevent absence of super.init() in sub-servlets
    @Override
    public final void init() throws ServletException {
        serviceManager = ServiceManager.getInstance(getServletContext());
        initServlet();
    }

    //if a sub-servlet needs it's own init() method
    protected void initServlet() {
    }

    //not to call this one instead of init()
    @Override
    public final void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public CommonService getCommonService() {
        return serviceManager.getCommonService();
    }

    public AdminService getAdminService() {
        return serviceManager.getAdminService();
    }

    protected void forwardToPage(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/WEB-INF/view/page-template.jsp").forward(req, resp);
    }

    protected <T> T createForm(Class<T> formClass, HttpServletRequest req) {
        try {
            T form = formClass.newInstance();
            Field[] fields = formClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String value = req.getParameter(field.getName());
                Object convertedValue = convert(field.getType(), value);
                field.set(form, convertedValue);
            }
            return form;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ApplicationException(e);
        }
    }

    private Object convert(Class<?> type, String value) {
        if (type == String.class) {
            return value;
        } else if (type == Integer.TYPE) {
            return Integer.parseInt(value);
        } else if (type == Boolean.TYPE) {
            return value != null;
        } else {
            throw new IllegalArgumentException("Can't convert to " + type);
        }
    }

}
