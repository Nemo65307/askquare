<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Sign up | Askquare</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="/static/css/signup.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <form class="form-signin text-center" method="post" action="/signup">
        <h1 class="h3 mb-3 font-weight-normal text">Create account</h1>
        <label for="inputLogin" class="sr-only">Login</label>
        <input name="login" type="text" id="inputLogin" class="form-control" placeholder="Login" required autofocus>
        <label for="inputEmail" class="sr-only">Email</label>
        <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email" required>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <label for="inputPasswordAgain" class="sr-only">Password again</label>
        <input type="password" id="inputPasswordAgain" class="form-control" placeholder="Password again" required>
        <label for="inputFirstName" class="sr-only">First name</label>
        <input name="firstName" type="text" id="inputFirstName" class="form-control" placeholder="First name" required>
        <label for="inputSecondName" class="sr-only">Second name</label>
        <input name="secondName" type="text" id="inputSecondName" class="form-control" placeholder="Second name">
        <label for="inputLastName" class="sr-only">Last name</label>
        <input name="lastName" type="text" id="inputLastName" class="form-control" placeholder="Last name" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        <br>
        <p>Already have an account? <a href="/login">Sign in</a></p>
        <p class="mt-5 mb-3 text-muted">&copy;Nemo65307 2018</p>
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