package com.nemo.askquare.servlet.student;

import com.nemo.askquare.model.Answer;
import com.nemo.askquare.model.Question;
import com.nemo.askquare.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OfflineTestServlet", urlPatterns = "/student/offline-test")
public class OfflineTestServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long testId = Long.parseLong(req.getParameter("id"));
        List<Question> questionList = getCommonService().findQuestionsByTestId(testId);
        List<Answer> answerList = getCommonService().findAnswersByTestId(testId);
        req.setAttribute("questionList", questionList);
        req.setAttribute("answerList", answerList);
        forwardToPage("student/offline-test.jsp", req, resp);
    }
}
