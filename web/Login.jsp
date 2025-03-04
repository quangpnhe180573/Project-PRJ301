<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <title>Login Form</title>
    <style>
        /* Hide sign-up and change password forms by default */
        .form-signup,
        .form-change-password,
        .form-reset {
            display: none; /* Initially hide these forms */
        }

        /* Additional styling for forms */
        #logreg-forms {
            max-width: 400px; /* Set a max width for the forms */
            margin: auto; /* Center the forms */
        }

        .form-control {
            margin-bottom: 15px; /* Add space between input fields */
        }

        .btn {
            margin-top: 10px; /* Add space above buttons */
        }

        h1 {
            margin-bottom: 20px; /* Add space below heading */
        }

        .text-danger {
            text-align: center; /* Center error messages */
        }
    </style>
</head>
<body>
    <div id="logreg-forms">
        <form class="form-signin" action="login" method="post">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign in</h1>
            <p class="text-danger">${mess}</p>
            <input name="user" value="${username}" type="text" id="inputEmail" class="form-control" placeholder="Username" required="" autofocus="">
            <input name="pass" value="${password}" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
            <div class="form-group form-check">
                <input name="remember" value="yes" type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Remember me</label>
            </div>
            <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Sign in</button>
            <hr>
            <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> Sign up New Account</button>
            <button class="btn btn-link btn-block" type="button" id="forgot_pswd"><i class="fas fa-key"></i> Forgot Password?</button>
        </form>

        <form action="signup" method="post" class="form-signup">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign up</h1>
            <input name="name" type="text" id="user-name" class="form-control" placeholder="Name" required="" autofocus="">
            <input name="address" type="text" id="user-address" class="form-control" placeholder="Address" required="" autofocus="">
            <input name="phone" type="text" id="user-phone" class="form-control" placeholder="Phone" required="" autofocus="">
            <input name="user" type="text" id="user-username" class="form-control" placeholder="Username" required="" autofocus="">
            <input name="email" type="email" class="form-control" placeholder="Email" required autofocus="">
            <input name="pass" type="password" id="user-pass" class="form-control" placeholder="Password" required="">
            <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required="">
            <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
            <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
        </form>

        <form action="change-password" method="post" class="form-change-password">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Change Password</h1>
            <input name="user" type="text" class="form-control" placeholder="Username" required="" autofocus="">
            <input name="oldpass" type="password" class="form-control" placeholder="Old Password" required="">
            <input name="newpass" type="password" class="form-control" placeholder="New Password" required="">
            <input name="repass" type="password" class="form-control" placeholder="Repeat New Password" required="">
            <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-key"></i> Change Password</button>
            <a href="#" id="cancel_change"><i class="fas fa-angle-left"></i> Back</a>
        </form>

        <form action="forgot-password" method="post" class="form-reset">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Retrieve Password</h1>
            <input name="username" type="text" class="form-control" placeholder="Enter your username" required autofocus="">
            <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-envelope"></i> Retrieve Password</button>
            <a href="#" id="cancel_reset"><i class="fas fa-angle-left"></i> Back</a>
        </form>

        <br>
        <h3 style="font-size: 20px; text-align: center; color: red">${requestScope.err}</h3>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        function toggleResetPswd(e) {
            e.preventDefault();
            $('#logreg-forms .form-signin').toggle(); // display:block or none
            $('#logreg-forms .form-reset').toggle(); // display:block or none
        }

        function toggleSignUp(e) {
            e.preventDefault();
            $('#logreg-forms .form-signin').toggle(); // display:block or none
            $('#logreg-forms .form-signup').toggle(); // display:block or none
        }

        function toggleChangePassword(e) {
            e.preventDefault();
            $('#logreg-forms .form-signin').toggle(); // display:block or none
            $('#logreg-forms .form-change-password').toggle(); // display:block or none
        }

        $(() => {
            // Login Register Form
            $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
            $('#logreg-forms #cancel_reset').click(toggleResetPswd);
            $('#logreg-forms #btn-signup').click(toggleSignUp);
            $('#logreg-forms #cancel_signup').click(toggleSignUp);
            $('#logreg-forms #btn-change').click(toggleChangePassword); // Add this line
            $('#logreg-forms #cancel_change').click(toggleChangePassword); // Add this line
        });
    </script>
</body>
</html>