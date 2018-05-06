package com.nemo.askquare.servlet.student;

import com.nemo.askquare.model.Test;
import com.nemo.askquare.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TestsListServlet", urlPatterns = "/student/tests-list")
public class TestsListServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Test> testList = getCommonService().findAllTests();
        req.setAttribute("testList", testList);
        forwardToPage("student/tests-list.jsp", req, resp);
    }

}
