<%-- 
    Document   : Cart
    Created on : Oct 31, 2020, 9:42:21 PM
    Author     : trinh
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>

    <body>
        <jsp:include page="Menu2.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <h3>${requestScope.err}</h3>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase">Product</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Price</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Quantity</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Total</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Delete</div>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="o" value="${sessionScope.cart}"></c:set>
                                            <c:set var="t" value="0"></c:set>
                                            <c:forEach items="${o.items}" var="i">
                                                <c:set var="t" value="${t+1}"/>
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="${i.product.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${i.product.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>
                                                            <c:set var="price" value="${i.price}" />
                                                            <fmt:formatNumber value="${price}" pattern="##.##" /> <strong>$</strong>
                                                        </strong></td>

                                                    <td class="align-middle">
                                                        <a href="process?id=${i.product.id}&num=-1"><button class="btnSub">-</button></a> 
                                                        <strong>${i.quantity}</strong>
                                                        <a href="process?id=${i.product.id}&num=1"><button class="btnAdd">+</button></a>
                                                    </td>
                                                    <td class="align-middle"><strong>
                                                            <c:set var="total" value="${price * i.quantity}"/>
                                                            <fmt:formatNumber value="${total}" type="number" pattern="##.##"   /> <strong>$</strong>
                                                        </strong></td>
                                                    <td class="align-middle">
                                                        <form class="text-dark" method="post" action="process">
                                                            <input class="btn btn-danger" type="hidden" value="${i.product.id}" name="id"/>
                                                            <input class="btn btn-danger" type="submit" value="Delete" />
                                                        </form>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Checkout</div>
                                <div class="p-4">
                                    <form method="post" action="checkout">
                                        <div class="form-group">
                                            <label for="name">Name:</label>
                                            <input type="text" class="form-control" value="${sessionScope.acc.name != null ? sessionScope.acc.name : ""}" id="name" name="name" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="address">Address:</label>
                                            <input type="text" class="form-control" value="${sessionScope.acc.address != null ? sessionScope.acc.address : ""}" id="address" name="address" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="phone">Phone:</label>
                                            <input type="tel" class="form-control" value="${sessionScope.acc.phone != null ? sessionScope.acc.phone : ""}" id="phone" name="phone" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email:</label>
                                            <input type="email" class="form-control" value="${sessionScope.acc.email != null ? sessionScope.acc.email : ""}" id="email" name="email" required>
                                        </div>
                                        <button type="submit" class="btn btn-dark rounded-pill py-2 btn-block">Check out</button>
                                    </form>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Bill</div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total price</strong><strong><fmt:formatNumber value="${sessionScope.cart.totalMoney}" pattern="##.00" /> <strong>$</strong></strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Ship</strong><strong> <c:if test="${sessionScope.size != 0}">5 <sup>$</sup></c:if></strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong><fmt:formatNumber value="${sessionScope.cart.totalMoney * 0.1 }" pattern="##.00" /> <strong>$</strong></strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total pay</strong>
                                            <h5 class="font-weight-bold"><fmt:formatNumber value="${sessionScope.cart.totalMoney * 1.1 +5}" pattern="##.00" /> <strong>$</strong></h5>
                                        </li>
                                    </ul>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
</html>
