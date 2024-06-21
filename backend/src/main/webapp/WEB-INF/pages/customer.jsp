<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 13-Apr-24
  Time: 03:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/customers" var="action" />
<h1 class="text-center text-info mt-1">QUẢN TRỊ KHÁCH HÀNG</h1>
<section class="container">
    <div class="col-md-4 col-md-offset-3 d-flex flex-row mb-3 gap-2">
        <form action="${action}" method="get" class="search-form w-100">
            <select class="form-select" aria-label="Default select example" name="type">
                <option value="0" selected>Kiểu</option>
                <option value="1">Id khách hàng</option>
                <option value="2">Tên khách hàng</option>
                <option value="3">Số điện thoại</option>
            </select>
            <div class="form-group has-feedback mb-2">
                <input type="text" class="form-control" name="kw" id="search" placeholder="Tìm kiếm">
                <span class="glyphicon glyphicon-search form-control-feedback"></span>
            </div>
            <button type="submit" class="btn btn-info">Tìm</button>
            <a href="<c:url value="/addCustomer" />" class="btn btn-info">Thêm người dân</a>
        </form>
    </div>    <table class="table table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>Tên Khách hàng</th>
            <th>Adress</th>
            <th>email</th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customer}" var="c">
            <tr>

                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>${c.address}</td>
                <td>${c.email}</td>
                <td></td>
                <td>
                    <c:url value="/api/customers/${c.id}" var="apiDel" />
                    <a href="<c:url value="/addCustomer/${c.id}" />" class="btn btn-success">Cập nhật</a>
                    <button class="btn btn-danger" onclick="delPro('${apiDel}', ${c.id})">Xóa</button>
                </td>
            </tr>
        </c:forEach>
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
<script src="<c:url value="/js/main.js" />"></script>