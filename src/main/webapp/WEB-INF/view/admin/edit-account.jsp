<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Edit account | Askquare</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="/static/css/signup.css" rel="stylesheet">

</head>

<body>
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
        <a href="javascript:history.back()" class="btn btn-lg btn-danger">Cancel</a>
        <button class="btn btn-lg btn-primary" type="submit">Submit</button>
        <br>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
