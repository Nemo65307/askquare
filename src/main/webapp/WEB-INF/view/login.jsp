<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Sign in | Askquare</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="/static/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-signin" method="post" action="/login">
    <c:if test="${error != null}">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputLogin" class="sr-only">Login</label>
    <input name="login" type="text" id="inputLogin" class="form-control" placeholder="Login" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <select name="role" id="inputRole" class="custom-select my-1 mr-sm-2" required> <!--class form control? try-->
        <option disabled selected>Login role...</option>
        <option value="4">Student</option>
        <option value="3">Tutor</option>
        <option value="2">Advanced Tutor</option>
        <option value="1">Admin</option>
    </select>
    <div class="checkbox mb-3">
        <label>
            <input name="rememberMe" type="checkbox" value="rememberMe"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <br>
    <a href="/signup">New here? Sign up</a>
    <p class="mt-5 mb-3 text-muted">&copy;Nemo65307 2018</p>
</form>
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
