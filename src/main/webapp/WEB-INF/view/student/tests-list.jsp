<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<table class="table">
    <thead class="thead-light text-center">
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Time per question</th>
        <th scope="col">Author's ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="test" items="${testList}">
        <tr class="text-center">
            <td>${test.name}</td>
            <td>${test.timePerQuestion}</td>
            <td>${test.accountId}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>