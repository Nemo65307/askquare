package com.nemo.askquare.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorHandler")
public class ErrorHandler extends AbstractFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
        } catch (Throwable th) {
            LOGGER.error("Error during request: " + req.getRequestURI(), th);
            resp.sendRedirect("/error");
        }
    }

}
