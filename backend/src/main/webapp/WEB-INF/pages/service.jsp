<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<a href="<c:url value="/addservice"/>" class="btn btn-info">Thêm Dich vu </a>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Id</th>
        <th>Tên Dich vu</th>
        <th>Gia</th>
        <th></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${service}" var="c">
        <tr>

            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.cost}</td>
            <td>
                <c:url value="/api/service/${c.id}" var="apiDel" />
                <a href="<c:url value="/addservice/${c.id}" />" class="btn btn-success">Cập nhật</a>
                <button class="btn btn-danger" onclick="delPro('${apiDel}', ${c.id})">Xóa</button>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="<c:url value="/js/main.js" />"></script>