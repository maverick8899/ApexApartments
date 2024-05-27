<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:hidden path="id" value="${id}"/>
<a href="<c:url value="/merchandise?id=${id}" />" class="btn btn-info">Thêm Hàng</a>

<table class="table table-hover">
    <thead>
    <tr>
        <th>Ten Cabinet </th>
        <th>note</th>
        <th>unit</th>
        <th>date</th>
        <th>active</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cabinetdetails}" var="c">
        <tr>
            <td>${c.merchandiseId.name}</td>
            <td>${c.merchandiseId.note}</td>
            <td>${c.merchandiseId.unit}</td>
            <td>${c.merchandiseId.date}</td>
            <td>${c.merchandiseId.active}</td>
            <td>
                <a href="<c:url value="/merchandise/${c.merchandiseId.id}" />" class="btn btn-success">Cập nhật</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="<c:url value="/js/main.js" />"></script>
