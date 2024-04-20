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
<h1 class="text-center text-info mt-1">QUẢN TRỊ KHÁCH HÀNG</h1>

<section class="container">
    <a href="<c:url value="/customers" />" class="btn btn-info">Thêm sản phẩm</a>
    <table class="table table-hover">
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
                    <a href="<c:url value="/customers/${c.id}" />" class="btn btn-success">Cập nhật</a>
                    <button class="btn btn-danger" onclick="delPro('${apiDel}', ${c.id})">Xóa</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js" />"></script>