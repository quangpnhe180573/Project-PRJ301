<%-- 
    Document   : index
    Created on : Oct 20, 2024, 5:18:55 PM
    Author     : admin1711
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.Account"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                background-color: #f4f4f4;
            }
            .sidebar {
                width: 250px;
                background-color: #333;
                color: #fff;
                height: 100vh;
                position: fixed;
                padding: 20px 0;
            }
            .sidebar h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .sidebar a {
                color: #fff;
                text-decoration: none;
                padding: 15px 20px;
                display: block;
                transition: background 0.3s;
            }
            .sidebar a:hover {
                background-color: #575757;
            }
            .main {
                margin-left: 250px;
                padding: 20px;
                flex-grow: 1;
            }
            .header {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                position: sticky;
                top: 0;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .content {
                margin-top: 20px;
            }
            .metrics {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }
            .metric {
                flex: 1;
                margin: 0 10px; /* Space between boxes */
                background: white;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                padding: 20px;
                text-align: center;
                min-width: 150px; /* Minimum width for smaller screens */
            }
            .charts {
                display: flex;
                justify-content: space-between;
                gap: 20px;
            }
            .chart-container {
                flex: 1;
                background: white;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                padding: 20px;
            }
            canvas {
                max-width: 100%;
            }
            @media (max-width: 768px) {
                .sidebar {
                    width: 100%;
                    height: auto;
                    position: relative;
                }
                .main {
                    margin-left: 0;
                }
                .metrics {
                    flex-direction: column; /* Stack vertically on small screens */
                }
                .metric {
                    margin: 10px 0; /* Space between stacked boxes */
                    flex: 1 1 100%; /* Full width on smaller screens */
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
        <div class="sidebar">
            <h2>Admin DashBoard</h2>
            <a href="#">Dashboard</a>
            <a href="manageaccount">Manage Account</a>
            <a href="manageproduct">Manage Product</a>
            <a href="orderlist">Order Details</a>
            <a href="home">Home</a>
            <a href="logout">Logout</a>
        </div>
        <div class="main">
            <div class="header">
                <h1>Welcome to the Admin Dashboard</h1>
            </div>
            <div class="content">
                <h2>Overview</h2>
                <p>This is where you can manage everything.</p>

                <div class="metrics">
                    <div class="metric" style="background-color: #ff9999;">
                        <h3>Total Orders</h3>
                        <p>${requestScope.totalOrder}</p>
                    </div>
                    <div class="metric" style="background-color: #99ccff;">
                        <h3>Total Revenue</h3>
                        <p> <fmt:formatNumber pattern="##.00" value="${requestScope.totalRevenue}"/><strong>$</strong></p>
                    </div>

                    <div class="metric" style="background-color: #ffcc99;">
                        <h3>Average Orders/Month</h3>

                        <p><fmt:formatNumber pattern="##.00" value="${requestScope.orderPerMonth}"/></p>
                    </div>
                    <div class="metric" style="background-color: #ffccff;">
                        <h3>Average Revenue/Month</h3>

                        <p><fmt:formatNumber pattern="##.00" value="${requestScope.revenuePerMonth}"/><strong>$</strong></p>
                    </div>
                    <div class="metric" style="background-color: #ffccff;">
                        <h3>Highest Revenue Month: ${requestScope.mS.month}</h3>
                        <p>${requestScope.mS.totalRevenue} <strong>$</strong> </p>
                    </div>
                    <div class="metric" style="background-color: #ffcc99;">
                        <h3>Highest Orders Month: ${requestScope.mF.month}</h3>
                        <p>${requestScope.mF.numOfOrders} Orders</p>
                    </div>
                </div>

                <div class="charts">
                    <div class="chart-container">
                        <h3>Revenue 2024</h3>
                        <canvas id="revenueChart"></canvas>
                    </div>

                    <div class="chart-container">
                        <h3>Monthly Orders</h3>
                        <canvas id="orderChart"></canvas>
                    </div>
                </div>
                <div class="metrics" style="margin-top: 20px;">
                    <div class="metric" style="background-color: #ffcc99;">
                        <h3>Cost</h3>
                        <p>${requestScope.totalCost}<strong>$</strong></p> <!-- Replace with actual cost value -->
                    </div>
                    <div class="metric" style="background-color: #ffccff;">
                        <h3>Profit</h3>
                        <p><fmt:formatNumber pattern="##.00" value="${requestScope.profit}"/><strong>$</strong></p> <!-- This can be calculated as Total Revenue - Cost -->

                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.min.js"></script>
        <script>
            const ctxRevenue = document.getElementById('revenueChart').getContext('2d');
            const revenueChart = new Chart(ctxRevenue, {
                type: 'line',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                    datasets: [{
                            label: 'Revenue',
                            data: [${requestScope.rev1}, ${requestScope.rev2}, ${requestScope.rev3},
            ${requestScope.rev4}, ${requestScope.rev5}, ${requestScope.rev6}, ${requestScope.rev7},
            ${requestScope.rev8}, ${requestScope.rev9}, ${requestScope.rev10}, ${requestScope.rev11}, ${requestScope.rev12}],
                            borderColor: 'rgba(75, 192, 192, 1)',
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderWidth: 2,
                            fill: true,
                        }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });

            const ctxOrder = document.getElementById('orderChart').getContext('2d');
            const orderChart = new Chart(ctxOrder, {
                type: 'line',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                    datasets: [{
                            label: 'Number of Orders',
                            data: [
            ${requestScope.m1}, ${requestScope.m2}, ${requestScope.m3},
            ${requestScope.m4}, ${requestScope.m5}, ${requestScope.m6},
            ${requestScope.m7}, ${requestScope.m8}, ${requestScope.m9},
            ${requestScope.m10}, ${requestScope.m11}, ${requestScope.m12}
                            ],
                            borderColor: 'rgba(255, 99, 132, 1)',
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderWidth: 2,
                            fill: true,
                        }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </body>
</html>