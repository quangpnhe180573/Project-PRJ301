<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  import="model.Account"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage Product</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img {
                width: 200px;
                height: 120px;
            }
        </style>

        <script type="text/javascript">
            $(document).ready(function () {
                // Khi nhấp vào nút edit
                $('.edit').on('click', function () {
                    var id = $(this).data('id');
                    var name = $(this).data('name');
                    var image = $(this).data('image');
                    var price = $(this).data('price');
                    var title = $(this).data('title');
                    var stock = $(this).data('stock');
                    var description = $(this).data('description');
                    var category = $(this).data('category');
                    var brand = $(this).data('brand');

                    // Cập nhật thông tin vào modal
                    $('#editProductId').val(id);
                    $('#editProductName').val(name);
                    $('#editProductImage').val(image);
                    $('#editProductPrice').val(price);
                    $('#editProductTitle').val(title);
                    $('#editProductStock').val(stock);
                    $('#editProductDescription').val(description);
                    $('#editProductCategory').val(category);
                    $('#editProductBrand').val(brand);
                });

                // Khi nhấp vào nút delete
                $('.delete').on('click', function () {
                    var id = $(this).data('id');

                    // Cập nhật thông tin vào modal
                    $('#deleteProductId').text(id);

                    // Cập nhật giá trị cho input ẩn
                    $('#productId').val(id);

                });

                // Ngăn chặn reload trang khi submit form
                $('#addProductForm').on('submit', function (e) {
                    e.preventDefault(); // Ngăn chặn reload trang
                    // Logic submit form tùy chỉnh ở đây
                    this.submit(); // Gọi submit form khi bạn đã xử lý xong logic tùy chỉnh
                });
            });
        </script>
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
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2><a href="manageproduct" style="color: white">Manage <b>Product</b></a></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
                                <i class="material-icons">&#xE147;</i> <span>Add New Product</span>
                            </a>
                        </div>
                    </div>
                </div>                               

                <table class="table table-striped table-hover">
                    <caption> <h3>${requestScope.err}</h3></caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Brand</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listP}" var="p">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.name}</td>
                                <td>
                                    <img src="${p.image}">
                                </td>
                                <td>${p.price} <strong>$</strong></td>
                                <td>${p.stock}</td>
                                <td>
                                    <c:forEach items="${requestScope.listC}" var="c">
                                        ${p.cID == c.cid ? c.cname : ""}
                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="#editEmployeeModal" class="edit" data-toggle="modal"
                                       data-id="${p.id}" data-name="${p.name}" data-image="${p.image}" 
                                       data-price="${p.price}" data-title="${p.title}" 
                                       data-stock="${p.stock}" data-description="${p.description}"
                                       data-category="${p.cID}" data-brand="${p.bID}">
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                    </a>
                                    <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"
                                       data-id="${p.id}">
                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${endPage}" var="i">
                            <li class="page-item ${page==i?"active":""}">
                                <a href="manageproduct?page=${i}" class="page-link">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div>
                <a href="home"><button type="button" class="btn btn-primary">Back to home</button></a>
                <a href="manageproduct?page=1"><button type="button" class="btn btn-primary">Back to first page</button></a>
            </div>

            <!-- Add Modal HTML --> 
            <!-- Add Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form id="addProductForm" action="add" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" required>
                                    <div class="text-danger">${requestScope.nameError}</div> <!-- Error message -->
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input name="image" type="text" class="form-control" required>
                                    <div class="text-danger">${requestScope.imageError}</div> <!-- Error message -->
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="text" class="form-control" required>
                                    <div class="text-danger">${requestScope.err}</div> <!-- Error message -->
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <textarea name="title" class="form-control" required></textarea>
                                    <div class="text-danger">${requestScope.titleError}</div> <!-- Error message -->
                                </div>
                                <div class="form-group">
                                    <label>Stock</label>
                                    <input min="1" type="number" name="stock" class="form-control" required>
                                    <div class="text-danger">${requestScope.stockError}</div> <!-- Error message -->
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" required></textarea>
                                    <div class="text-danger">${requestScope.descriptionError}</div> <!-- Error message -->
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${requestScope.listC}" var="c">
                                            <option value="${c.cid}">${c.cname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Brand:</label>
                                    <select name="brand" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${requestScope.listB}" var="b">
                                            <option value="${b.bid}">${b.bname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Edit Modal HTML --> 
            <div id="editEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="update" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="id" id="editProductId">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" id="editProductName" type="text" class="form-control" required>
                                </div>

                                <div class="form-group">
                                    <label>Image</label>
                                    <input name="image" id="editProductImage" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" id="editProductPrice" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <textarea name="title" id="editProductTitle" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Stock</label>
                                    <input min="1" type="number" name="stock" id="editProductStock" class="form-control" required></input>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" id="editProductDescription" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" id="editProductCategory" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${requestScope.listC}" var="c">
                                            <option value="${c.cid}">${c.cname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Brand:</label>
                                    <select name="brand" id="editProductBrand" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${requestScope.listB}" var="b">
                                            <option value="${b.bid}">${b.bname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-warning" value="Save">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Delete Modal HTML -->
            <div id="deleteEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="delete" method="post">
                            <input type="hidden" id="productId" name="id">
                            <div class="modal-header">						
                                <h4 class="modal-title">Delete Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p>Bạn có chắc chắn muốn xóa sản phẩm này?</p>
                                <p class="text-warning"><small>ID: <span id="deleteProductId"></span></small></p>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-danger" value="Delete">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>