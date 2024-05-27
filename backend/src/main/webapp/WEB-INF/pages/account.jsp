<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<a href="<c:url value="/accounts"/>" class="btn btn-info">Thêm tài khoản </a>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Id</th>
        <th>>username</th>
        <th>password</th>
        <th>Vai trò</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${account}" var="c">
        <tr>

            <td>${c.id}</td>
            <td>${c.username}</td>
            <td>${c.password}</td>
            <td>${c.role}</td>
            <td>
                <c:url value="/api/account/${c.id}" var="apiDel" />
                <a href="<c:url value="/accounts/${c.id}" />" class="btn btn-success">Cập nhật</a>
                <button class="btn btn-danger" onclick="delPro('${apiDel}', ${c.id})">Xóa</button>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="<c:url value="/js/main.js" />"></script>