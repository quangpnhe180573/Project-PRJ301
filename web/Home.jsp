<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script>
            const price = 123456789; // Giả sử đây là giá của bạn
            const formattedPrice = price.toLocaleString('vi-VN')
        </script>

        <style>
            .product-list .card {
                height: 95%; /* Đảm bảo mỗi thẻ card có chiều cao bằng nhau */
                margin-bottom: 5px;
            }

            .product-list .card-body {
                display: flex;
                flex-direction: column; /* Đặt chiều dọc cho nội dung */
                justify-content: space-between; /* Đảm bảo khoảng cách đều giữa các phần tử */
            }

            .product-list .card-text {
                flex-grow: 1; /* Cho phép mô tả chiếm không gian còn lại */
            }

            .product-list .card-img-top {
                width: 100%; /* Đảm bảo ảnh chiếm toàn bộ chiều rộng của card */
                height: 200px; /* Đặt chiều cao cố định cho ảnh */
                object-fit: cover; /* Cắt bỏ phần thừa và giữ tỉ lệ */
            }
            


        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="home">Home</a>
                                </li>
                                <li class="breadcrumb-item"><a href="#">Category</a></li>
                                <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>

                    <div class="col-sm-9 product-list"">
                        <div class="row">
                        <c:forEach items="${requestScope.list}" var="p">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${p.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?id=${p.id}" title="${p.title}">${p.name}</a></h4>
                                        <p class="card-text show_txt">${p.description}
                                        </p>
                                        <div class="row">
                                            <div class="col">
                                                <c:set var="price" value="${p.price * 1.1}" />

                                                <p class="btn btn-danger btn-block" style="display: block"> 
                                                    <fmt:formatNumber value="${price}" pattern="##.000" /> <strong>$</strong>
                                                </p>
                                            </div>
                                            <div class="col">
                                                <form action="buy" method="post">  
                                                    <c:choose>
                                                        <c:when test="${p.stock <=0}">
                                                            <span class="btn btn-success btn-block" style="display: block">Sold out !!!</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="btn btn-success btn-block" type="submit" value="Add to cart"><!-- comment -->
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <input type="hidden" value="${p.id}" name="id">
                                                    <input type="hidden" value="1" name="quantity">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${page}</b> out of <b>${count}</b> entries</div>
                        <ul class="pagination">
                            <c:forEach begin="1" end="${endPage}" var="i">

                                <c:choose >
                                    <c:when test="${tag3 == null}">
                                        <li class="page-item ${page==i?"active":""}"><a href="home?page=${i}" class="page-link">${i}</a></li>
                                        </c:when>

                                    <c:otherwise>
                                        <li class="page-item ${page==i?"active":""}"><a href="filter?cid=${param.cid==null?0:param.cid}&bid=${param.bid == null?0:param.bid}&fromprice=${param.fromprice == null?0:param.fromprice}&toprice=${param.toprice == null?0:param.toprice}&fconfirm=0&order=${param.order == null?0:param.order}&page=${i}" class="page-link">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose> 
                                </c:forEach>
                        </ul>
                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

<!--3-->