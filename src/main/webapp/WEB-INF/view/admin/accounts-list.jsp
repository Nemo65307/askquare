<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<table class="table">
    <thead class="thead-light text-center">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Username</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="account" items="${accountList}">
        <tr class="text-center">
            <th scope="row">${account.id}</th>
            <td>${account.firstName}</td>
            <td>${account.lastName}</td>
            <td>${account.login}</td>
            <td>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <a href="/admin/edit-account?id=${account.id}" type="button" class="btn btn-secondary">Edit</a>
                    <a href="/admin/delete-account?id=${account.id}" type="button" class="btn btn-secondary">Delete</a>
                    <a href="/admin/change-active?id=${account.id}" type="button" class="btn btn-secondary">(De)Activate</a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-end">
        <li class="page-item">
            <a class="page-link" href="/admin/accounts-list?page=${firstPage}" tabindex="-1">First</a>
        </li>
        <li class="page-item"><a class="page-link" href="/admin/accounts-list?page=${page1}">${page1}</a></li>
        <li class="page-item"><a class="page-link" href="/admin/accounts-list?page=${page2}">${page2}</a></li>
        <li class="page-item"><a class="page-link" href="/admin/accounts-list?page=${page3}">${page3}</a></li>
        <li class="page-item">
            <a class="page-link" href="/admin/accounts-list?page=${lastPage}">Last</a>
        </li>
    </ul>
</nav>