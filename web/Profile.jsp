<%-- 
    Document   : Profile
    Created on : Oct 12, 2024, 9:25:06 PM
    Author     : admin1711
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.Account"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>

        .navbar-nav .nav-link {
            padding: 15px 20px; /* Adjust padding if necessary */
        }
        .navbar-nav .nav-item {
            margin-left: 10px; /* Space between items */
        }
        /* Custom styles for the navbar */
        .navbar {
            background-color: #343a40; /* Dark background */
        }
        .navbar-brand {
            font-size: 1.5rem; /* Larger brand text */
            font-weight: bold; /* Bold text */
        }
        .navbar-nav .nav-link {
            color: #ffffff; /* White text for links */
            padding: 15px 20px; /* More padding for links */
        }
        .navbar-nav .nav-link:hover {
            background-color: #495057; /* Darker background on hover */
            border-radius: 5px; /* Rounded corners on hover */
        }
        .navbar-toggler {
            border-color: rgba(255, 255, 255, 0.1); /* Light border for toggler */
        }
        .navbar-toggler-icon {
            background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 30 30'%3E%3Cpath stroke='rgba(255, 255, 255, 1)' stroke-width='2' d='M4 7h22M4 15h22M4 23h22'/%3E%3C/svg%3E"); /* Customize toggler icon */
        }
    </style>
</head>
<%
Account a = (Account) session.getAttribute("acc");
if (a == null ) {
response.sendRedirect("Login.jsp");
return; // Ensure no further code is executed
}
  


%>


<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">Home</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto"> <!-- Added mr-auto for left alignment -->
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container bootstrap snippet">

    <div class="row">
        <div class="col-sm-10"><h1>${sessionScope.acc.name}</h1></div>

    </div>
    <div class="row">
        <div class="col-sm-3"><!--left col-->


            <div class="text-center">
                <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">


            </div></hr><br>


        </div><!--/col-3-->
        <div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li><a data-toggle="tab" href="Profile.jsp">Home</a></li>

            </ul>


            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <h3>${requestScope.err}</h3>
                    <form class="form" action="change" method="post" id="registrationForm">
                        <div class="form-group">
                            <input type="hidden" value="${sessionScope.acc.id}" name="id">
                            <div class="col-xs-6">
                                <label for="first_name"><h4>Name</h4></label>
                                <input type="text" class="form-control" name="name" placeholder="Name" value="${sessionScope.acc.name}" required="">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="phone"><h4>Phone</h4></label>
                                <input type="text" class="form-control" name="phone" value="${sessionScope.acc.phone}" placeholder="Phone" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="address"><h4>Address</h4></label>
                                <input type="text" class="form-control" name="address" value="${sessionScope.acc.address}" placeholder="Address" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="email"><h4>Email</h4></label>
                                <input type="email" class="form-control" name="email" value="${sessionScope.acc.email}" placeholder="Email" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="password"><h4>Password</h4></label>
                                <input type="password" class="form-control" name="password" value="${sessionScope.acc.password}" placeholder="Password" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                <button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Save</button>
                                <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i> Reset</button>
                            </div>
                        </div>
                    </form>

                    <hr>

                </div><!--/tab-pane-->

            </div><!--/tab-pane-->
        </div><!--/tab-content-->

    </div><!--/col-9-->
</div><!--/row-->
