<%-- 
    Document   : Detail
    Created on : Dec 29, 2020, 5:43:04 PM
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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }
            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }
            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img {
                width: 100% !important;
                height: auto !important;
            }
            .gallery-wrap {
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .img-big-wrap {
                display: flex;
                justify-content: center;
            }
            .related-products {
                margin-top: 30px;
                width: 100%; /* Ensure full width */
            }
            .related-card {
                height: 350px; /* Fixed height for uniformity */
                margin-bottom: 30px; /* Margin for space between cards and footer */
                display: flex;
                flex-direction: column; /* Arrange child elements in a column */
            }
            .card-img-top {
                height: 150px; /* Fixed height for images */
                object-fit: cover; /* Cover to crop excess */
            }
            .card-body {
                display: flex;
                flex-direction: column;
                justify-content: space-between; /* Ensures space between elements */
                flex-grow: 1; /* Allows the card body to take remaining space */
            }
            .btn-view {
                margin-top: auto; /* Push the button to the bottom */
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <aside class="col-sm-5 border-right">
                        <article class="gallery-wrap"> 
                            <div class="img-big-wrap">
                                <div> <a href="#"><img src="${p.image}"></a></div>
                        </div>
                        <div class="img-small-wrap"></div>
                    </article>
                </aside>
                <aside class="col-sm-7">
                    <form action="buy" method="post">
                        <input type="hidden" value="${requestScope.p.id}" name="id">
                        <article class="card-body p-5">
                            <h3 class="title mb-3">${requestScope.p.name}</h3>
                            <p class="price-detail-wrap"> 
                                <span class="price h3 text-warning"> 
                                    <c:set var="price" value="${p.price * 1.1}" />                                             
                                    <span class="num"> <fmt:formatNumber value="${price}" pattern="##.00" /></span>&nbsp;<span class="currency"> <strong>$</strong></span>
                                </span> 
                            </p>
                            <dl class="item-property">
                                <dt>Description</dt>
                                <dd><p>${p.description}</p></dd>
                            </dl>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5">
                                    <dl class="param param-inline">
                                        <dt>Quantity: </dt>
                                        <dd>
                                            <select name="quantity" class="form-control form-control-sm" style="width:70px;">
                                                <c:forEach begin="1" end="${p.stock}" var="i">
                                                    <option value="${i}">${i}</option>
                                                </c:forEach>
                                            </select>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                            <hr>
                            <c:choose>
                                <c:when test="${p.stock ==0}">
                                    <span class="btn btn-lg btn-primary text-uppercase">Sold out</span>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-lg btn-primary text-uppercase">Add to cart</button> 
                                </c:otherwise>
                            </c:choose>
                        </article>
                    </form>
                </aside>
            </div>

            <!-- Related Products Section -->
            <div class="related-products">
                <h4>Related Products</h4>
                <div class="row">
                    <c:forEach items="${requestScope.relatedProducts}" var="relatedProduct" varStatus="status">
                        <c:if test="${status.index < 6}"> <!-- Limit to 5 products -->
                            <div class="col-md-2 col-sm-4">
                                <div class="card related-card">
                                    <img class="card-img-top" src="${relatedProduct.image}" alt="${relatedProduct.name}">
                                    <div class="card-body">
                                        <h5 class="card-title">${relatedProduct.name}</h5>
                                        <p class="card-text">
                                            <c:set var="relatedPrice" value="${relatedProduct.price * 1.1}" />
                                            <fmt:formatNumber value="${relatedPrice}" pattern="##.00" /> <strong>$</strong>
                                        </p>
                                        <a href="detail?id=${relatedProduct.id}" class="btn btn-primary btn-view">View</a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>