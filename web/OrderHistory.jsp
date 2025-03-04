<%@page contentType="text/html" pageEncoding="UTF-8" import="model.Account"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order List</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #eef2f3;
            }
            .container {
                max-width: 900px;
                margin: auto;
                background: white;
                border-radius: 12px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                padding: 20px;
            }
            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            .order-card {
                background: #f9f9f9;
                border-radius: 12px;
                border: 2px solid #ff9800; /* Orange border */
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                padding: 15px;
                margin: 15px 0; /* Khoảng cách giữa các đơn hàng */
                transition: transform 0.2s;
            }
            .order-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                border-bottom: 2px solid #ff9800; /* Orange line */
                padding-bottom: 10px;
                margin-bottom: 10px;
            }
            .order-info {
                margin-top: 10px;
                font-size: 14px;
            }
            .product-list {
                margin-top: 10px;
            }
            .product-item {
                display: flex;
                align-items: center;
                border-bottom: 1px solid #ddd;
                padding: 10px 0;
            }
            .product-item img {
                width: 50px; /* Kích thước hình ảnh */
                height: 50px;
                margin-right: 10px;
                border-radius: 5px;
            }
            .product-item span {
                flex: 1;
            }
            .total {
                font-weight: bold;
                margin-top: 10px;
                font-size: 18px;
                color: #ff9800; /* Orange for total */
            }
            hr {
                border: 1px solid #ff9800; /* Orange line for hr */
                margin: 15px 0;
            }
            .summary-item {
                display: flex;
                align-items: center;
                padding: 10px 0;
                border-bottom: 1px solid #ddd; /* Đường viền cho các mục tóm tắt */
            }
            /* CSS cho các nút */
            .button-container {
                text-align: center;
                margin-bottom: 20px;
            }
            .btn {
                background-color: #ff9800; /* Màu nền của nút */
                color: white; /* Màu chữ */
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                margin: 0 10px; /* Khoảng cách giữa các nút */
                text-decoration: none; /* Bỏ gạch chân */
                display: inline-block; /* Để nút nằm ngang */
            }
            .btn:hover {
                background-color: #e68a00; /* Màu nền khi di chuột */
            }
            .pagination {
                display: flex;
                justify-content: center;
                list-style-type: none;
                padding: 0;
                margin: 20px 0;
            }

            .page-item {
                margin: 0 5px;
            }

            .page-link {
                display: inline-block;
                padding: 10px 15px;
                background-color: #ff9800; /* Orange background */
                color: white; /* White text */
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            .page-link:hover {
                background-color: #e68a00; /* Darker orange on hover */
            }

            .page-item.active .page-link {
                background-color: #e68a00; /* Active page color */
                cursor: default; /* Disable pointer for active page */
            }
            .search-form {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }

            .search-form input,
            .search-form select {
                margin-right: 10px;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 14px;
            }

            .search-form button {
                background-color: #ff9800;
                color: white;
                border: none;
                border-radius: 5px;
                padding: 10px 15px;
                cursor: pointer;
                font-size: 14px;
            }

            .search-form button:hover {
                background-color: #e68a00;
            }
        </style>
    </head>
    <body>



        <div class="container">

            <h1>Order List</h1>
           

            <div class="button-container">
                <a href="home" class="btn">Back to Home</a>
                <a href="dashboard" class="btn">Back to Dashboard</a>
            </div>

            <c:forEach items="${requestScope.orderhistorylist}" var="o">
                <div class="order-card">
                    <div class="order-header">
                        <h3>Order ID: ${o.orders.id}</h3>
                        <span>Order Date: ${o.orders.date}</span>
                    </div>
                    <div class="order-info">
                        <p><strong>Customer Name:</strong> ${o.name}</p>
                        <p><strong>Address:</strong> ${o.address}</p>
                        <p><strong>Phone Number:</strong> ${o.phone}</p>
                    </div>
                    <hr>
                    <div class="product-list">
                        <h4>Products:</h4>
                        <c:forEach items="${o.orderDetail}" var="od">
                            <div class="product-item">
                                <img src="${od.image}" alt="${od.pname}">
                                <span>${od.pname}</span>
                                <span><strong>${od.price} $</strong> x ${od.quantity}</span>
                            </div>
                        </c:forEach>

                        <div class="summary-item">
                            <span style="flex: 0.58;"><strong>VAT (10%):</strong></span>
                            <span><strong><fmt:formatNumber pattern="##.00" value="${o.orders.totalMoney2*0.1}"/> $</strong></span>
                        </div>
                        <div class="summary-item">
                            <span style="flex: 0.58;"><strong>Shipping Fee:</strong></span>
                            <span><strong>$5.00</strong></span>
                        </div>
                    </div>
                    <p class="total">Total Amount Due: 
                        <fmt:formatNumber pattern="##.00" value="${o.orders.totalMoney}"/> <strong>$</strong>
                    </p>
                </div>
            </c:forEach>
            <ul class="pagination">
                <c:forEach begin="1" end="${endPage}" var="i">
                    <li class="page-item ${page==i?"active":""}"><a href="orderhistory?page=${i}" class="page-link">${i}</a></li>
                    </c:forEach>
            </ul>
        </div>

    </body>
</html>