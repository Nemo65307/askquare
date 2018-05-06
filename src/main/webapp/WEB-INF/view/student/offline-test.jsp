<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<table class="table">
    <c:set var="answers" scope="page" value="${answerList}"/>
    <c:set var="i" scope="page" value="1"/>
    <tbody>
    <c:forEach var="question" items="${questionList}">
        <thead>
            <th>${i}. ${question.name}</th>
            <c:set var="i" scope="page" value="${i + 1}"/>
        </thead>
        <c:forEach var="ans" items="${answers}">
            <c:if test="${ans.questionId == question.id}">
                <tr>
                    <td>${ans.name}</td>
                </tr>
            </c:if>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>