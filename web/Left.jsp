

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>.sidebar {
        position: -webkit-sticky; /* Hỗ trợ Safari */
        position: sticky; /* Giữ sidebar dính */
        top: 60px; /* Đặt khoảng cách từ trên cùng (60px là chiều cao của menu) */
        height: calc(100vh - 60px); /* Đặt chiều cao sidebar bằng chiều cao viewport trừ chiều cao menu */
        overflow-y: auto; /* Thêm cuộn nếu nội dung vượt quá chiều cao */
        z-index: 1000; /* Đảm bảo nó nằm trên các phần tử khác */
    }

    input[type="number"] {
        width: calc(100% - 16px); /* Đặt chiều rộng ô nhập liệu với padding */
        box-sizing: border-box; /* Đảm bảo padding không làm tăng chiều rộng */
        padding: 8px; /* Padding bên trong ô nhập liệu */
        border: 1px solid #ccc; /* Viền cho ô nhập liệu */
        border-radius: 4px; /* Bo tròn các góc */
    }

    table {
        width: 100%; /* Đặt chiều rộng của bảng là 100% */
        margin-top: 10px; /* Khoảng cách giữa bảng và các phần khác */
        border-collapse: collapse; /* Kết hợp các viền ô */
    }

    td {
        padding: 5px; /* Khoảng cách giữa các ô */
        vertical-align: middle; /* Căn giữa các ô theo chiều dọc */
    }
</style>

<div class="col-sm-3 sidebar">
    <div class="card bg-light mb-3 ">
        <div class="card-header bg-primary text-white text-uppercase active"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${sessionScope.listC}" var="o">
                <li class="list-group-item text-white ${tag.equals(String.valueOf(o.cid))?"active":""}">
                    <a href="filter?cid=${o.cid == null?0:o.cid}&bid=${param.bid == null?0:param.bid}&fromprice=${param.fromprice == null?0:param.fromprice}&toprice=${param.toprice == null?0:param.toprice}&fconfirm=0">${o.cname}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="card bg-light mb-3 ">
        <div class="card-header bg-primary text-white text-uppercase active"><i class="fa fa-list"></i> Brand</div>
        <ul class="list-group category_block">
            <c:forEach items="${sessionScope.listB}" var="b">
                <li class="list-group-item text-white ${tag2.equals(String.valueOf(b.bid))?"active":""}">
                    <a href="filter?cid=${param.cid==null?0:param.cid}&bid=${b.bid == null?0:b.bid}&fromprice=${param.fromprice == null?0:param.fromprice}&toprice=${param.toprice == null?0:param.toprice}&fconfirm=0">${b.bname}</a>
                </li>
            </c:forEach>
        </ul>
    </div>


    <form id="f1" action="filter" method="get">
        <table>
            <tr>
            <input type="hidden" name="cid" value="${tag == null ? 0 : tag}">
            </tr>
            <tr>
            <input type="hidden" name="bid" value="${tag2 == null ? 0 : tag2}">
            </tr>
            <tr>
            <input type="hidden" name="fconfirm" value="${tag3 == null ? 0 : tag3}">
            </tr>
            <tr>
            <input type="hidden" name="toprice" value="${param.toprice == null ? 0: param.toprice}">
            </tr>
            <tr>
            <input type="hidden" name="fromprice" value="${param.fromprice == null ? 0: param.fromprice}">
            </tr>
            <tr>
                <td>Sắp xếp:</td>
                <td>
                    <select name="order" onchange="document.getElementById('f1').submit()">
                        <option value="0" ${order == 0 ? 'selected' : ''}>Tất cả</option>
                        <option value="1" ${order == 1 ? 'selected' : ''}>Tăng dần</option>
                        <option value="2" ${order == 2 ? 'selected' : ''}>Giảm dần</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>

    <form id="f1" action="filter" method="get"> <!-- Ensure the action is correct -->
        <table>
            <tr><input type="hidden" name="cid" value="${tag == null?0:tag}"></tr>
            <tr><input type="hidden" name="bid" value="${tag2 == null?0:tag2}"></tr>
            <tr><input type="hidden" name="fconfirm" value="${tag3 == null?0:tag3}"></tr>
            <tr>
                <td>Từ:</td>
                <td><input type="number" name="fromprice" value="${param.fromprice==null?0:param.fromprice}" placeholder="$"></td>
            </tr>
            <tr>
                <td>Đến:</td>
                <td><input type="number" name="toprice" value="${param.toprice==null?0:param.toprice}" placeholder="$"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Lọc"><!-- comment --></td>
            </tr>
        </table>

    </form>


    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Best-seller</div>
        <c:forEach items="${sessionScope.listBestSeller}" var="p" >
            <div class="card-body">
                <a href="detail?id=${p.id}"><img class="img-fluid" src="${p.image}"/></a>
                <h5 class="card-title"> <a href="detail?id=${p.id}">${p.title}</a></h5>
                <p class="bloc_left_price"><fmt:formatNumber pattern="##.000" value="${p.price*1.1}"/> <strong>$</strong></p>
            </div>
        </c:forEach>
    </div>
</div>