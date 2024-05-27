<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<a href="<c:url value="/addroom"/>" class="btn btn-info">Thêm Phòng </a>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Id</th>
        <th>>Mã Phòng</th>
        <th>Gia</th>
        <th></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${room}" var="c">
        <tr>

            <td>${c.id}</td>
            <td>${c.code}</td>
            <td>${c.cost}</td>
            <td>
                <c:url value="/api/room/${c.id}" var="apiDel" />
                <a href="<c:url value="/addroom/${c.id}" />" class="btn btn-success">Cập nhật</a>
                <button class="btn btn-danger" onclick="delPro('${apiDel}', ${c.id})">Xóa</button>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="<c:url value="/js/main.js" />"></script>