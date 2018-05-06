<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>


<div class="container">
    <form class="form-signin text-center" method="post" action="/admin/edit-account">
        <c:set var="account" value="${account}" scope="page"/>
        <h1 class="h3 mb-3 font-weight-normal text">Edit account</h1>
        <label for="inputLogin" class="sr-only">Login</label>
        <input name="login" type="text" id="inputLogin" class="form-control" placeholder="Login"
               value="${account.login}" autofocus>
        <label for="inputEmail" class="sr-only">Email</label>
        <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email"
               value="${account.email}" disabled>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password">
        <label for="inputPasswordAgain" class="sr-only">Password again</label>
        <input type="password" id="inputPasswordAgain" class="form-control" placeholder="Password again">
        <label for="inputFirstName" class="sr-only">First name</label>
        <input name="firstName" type="text" id="inputFirstName" class="form-control" placeholder="First name"
               value="${account.firstName}">
        <label for="inputSecondName" class="sr-only">Second name</label>
        <input name="secondName" type="text" id="inputSecondName" class="form-control" placeholder="Second name"
               value="${account.secondName}">
        <label for="inputLastName" class="sr-only">Last name</label>
        <input name="lastName" type="text" id="inputLastName" class="form-control" placeholder="Last name"
               value="${account.lastName}">
        <a href="/home" class="btn btn-lg btn-danger">Cancel</a>
        <button class="btn btn-lg btn-primary" type="submit">Submit</button>
        <br>
    </form>
</div>
