<%-- 
    Document   : EditAccount
    Created on : Oct 19, 2024, 2:58:20 PM
    Author     : admin1711
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.Account"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Account</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            .container {
                max-width: 600px;
                margin: auto;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }
            input[type="text"],
            input[type="password"],
            input[type="tel"],
            input[type="email"],
            textarea,
            select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            button {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 10px;
            }
            .orange-button {
                background-color: #ff9800;
                color: white;
            }
            .orange-button:hover {
                background-color: white;
                color: #ff9800;
            }
            .back-button {
                background-color: #ff9800;
                color: white;
            }
            .back-button:hover {
                background-color: white;
                color: #ff9800;
            }
            @media (max-width: 600px) {
                .container {
                    padding: 10px;
                }
                button {
                    font-size: 14px;
                }
            }
        </style>
    </head>
    <body>
        <%
  Account a = (Account) session.getAttribute("acc");
  if (a == null ) {
      response.sendRedirect("Login.jsp");
      return; // Ensure no further code is executed
  }
  

  if (a.getRole() != 1 ) {
      response.sendRedirect("err.jsp");
      return; // Ensure no further code is executed
  }
        %>
        <div class="container">
            <h2>Add Account</h2>
            <form action="addaccount" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="text" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" name="address" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="role">Role</label>
                    <select id="role" name="role" required>
                        <option value="0">User</option>
                        <option value="1">Admin</option>
                    </select>
                </div>
                <button type="submit" class="orange-button">Add</button>
            </form>
            <button onclick="window.location.href = 'ManageAccount.jsp'" class="back-button">Back to Manage Account</button>
            <button onclick="window.location.href = 'home'" class="back-button">Back to Home</button>
            <h3 style="font-size: 20px; text-align: center; color: red" >${requestScope.err}</h3>

        </div>

    </body>
</html>