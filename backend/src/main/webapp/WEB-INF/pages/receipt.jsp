 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN TRỊ HÓA ĐƠN</h1>

<section class="container">
    <c:url value="/receipt" var="action" />
    <div class="container">
        <div class="col-md-4 col-md-offset-3 d-flex flex-row mb-3 gap-2">
            <form action="${action}" method="get" class="search-form w-100"   >
                <select class="form-select" aria-label="Default select example" name="type">
                    <option value="0" selected>Kiểu</option>
                    <option value="1">Id khách hàng</option>
                    <option value="2">Tên khách hàng</option>
                    <option value="3">Id hóa đơn</option>
                    <option value="4">Ngày</option>                   
                    <option value="5">Tháng</option>
                    <option value="6">Năm</option>
                </select>
                <select class="form-select" aria-label="Default select example" name="isPay">
                    <option value="0" selected>Thanh Toán</option>
                    <option value="1">Đã Thanh Toán</option> 
                    <option value="2">Chưa Thanh Toán</option> 
                </select>
                <div class="form-group has-feedback mb-2">
                    <input type="text" class="form-control" name="kw" id="search" placeholder="search">
                    <span class="glyphicon glyphicon-search form-control-feedback"></span>
                </div>
                <button type="submit" class="btn btn-info"> Tìm</button>
                <a href="<c:url value="/useService" />" class="btn btn-info">Thêm Hóa Đơn</a>                    
            </form>
        </div>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th>Mã HĐ</th>
                <th>Mã KH</th>
                <th>Tên Khách hàng</th>
                <th>email</th>
                <th>Tổng HĐ</th>
                <th>ngày</th>
                <th>thanh toán</th>


            </tr>
        </thead>
        <tbody>

            <c:if test="${receipts != null}">
                <c:forEach items="${receipts}" var="r">
                    <tr>
                        <td>${r.receiptId}</td>
                        <td>${r.customerId}</td>
                        <td>${r.customerName}</td>
                        <td>${r.customerEmail}</td> 
                        <td>${r.receiptTotal} VNĐ</td> 

                        <td>${r.receiptDate}</td>
                        <c:choose>
                            <c:when test="${r.receiptPay == true}">
                                <td>rồi</td>
                            </c:when>
                            <c:otherwise>
                                <td>chưa</td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <c:url value="${action}/${r.receiptId}" var="apiDel" />
                                    <!--<a href="<c:url value="/receipt/${r.receiptId}" />" class="btn btn-success">Cập nhật</a>-->
                            <a href="<c:url value="/receiptDetail/?type=3&kw=${r.receiptId}&cusId=${r.customerId}" />" class="btn btn-success">Chi Tiết</a> 

                            <button class="btn btn-danger" onclick="delReceipt('api/receipt/${r.receiptId}')">Xóa</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>


        </tbody>
    </table>
</section>

<%@page import="java.util.List"%>
<%
    String pageParam = request.getParameter("page");
    String pageSizeParam = request.getParameter("pageSize");

    int currentPage = pageParam != null ? Integer.parseInt(pageParam) : 1;
    int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10;

    List<?> feedbacks = (List<?>) request.getAttribute("feedbacks");
    int totalItems = feedbacks != null ? feedbacks.size() : 0;
    int totalPages = (int) Math.ceil((double) totalItems / pageSize);

//    out.println("<p>currentPage: " + currentPage + "</p>");
//    out.println("<p>pageSize: " + pageSize + "</p>");
//    out.println("<p>totalItems: " + totalItems + "</p>");
//    out.println("<p>totalPages: " + totalPages + "</p>");
    int j = 0;
%>
<div class="d-flex justify-content-center">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item <%= currentPage == 1 ? "disabled" : ""%>">
                <a class="page-link" href="/apartment_manager/feedback?page=<%= currentPage - 1%>&pageSize=<%= pageSize%>">Previous</a>
            </li>

            <% for (int i = 1; i <= totalPages; i++) {%>
            <li class="page-item <%= currentPage == i ? "active" : ""%>">
                <a class="page-link" href="/apartment_manager/feedback?page=<%= i%>&pageSize=<%= pageSize%>"><%= i%></a>
            </li>
            <% }%>

            <li class="page-item <%= currentPage == totalPages ? "disabled" : ""%>">
                <a class="page-link" href="/apartment_manager/feedback?page=<%= currentPage + 1%>&pageSize=<%= pageSize%>">Next</a>
            </li>
        </ul>
    </nav>
</div>

<script>
    function navigatePage(page, pageSize, totalPages) {
        if (page < 1 || page > totalPages) {
            return;
        }
        const urlParams = new URLSearchParams(window.location.search);
        urlParams.set('page', page);
        urlParams.set('pageSize', pageSize);
        window.location.search = urlParams.toString();
    }
</script>
<script >
    async function delReceipt(path) {
        if (confirm("Bạn chắc chắn muốn xóa không?")) {
            try {
                const response = await fetch(path, {
                    method: "DELETE"
                });
                console.log(response);
                if (response.ok) {
                    location.reload(); // Tải lại trang sau khi xóa thành công
                } else {
                    throw new Error("Có lỗi xảy ra khi xóa.");
                }
            } catch (error) {
                console.error("Error:", error);
                alert("Có lỗi xảy ra khi xóa.");
            }
        }
    }
</script>