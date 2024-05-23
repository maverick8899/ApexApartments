<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Ten khách hàng</th>
        <th>Ten Cabinet </th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${merchandisecabinet}" var="c">
        <tr>
            <td>${c.name}</td>
            <td>${c.merchandiseCabinetId.name}</td>
                    <td>
                                <a href="<c:url value="/cabinetdetails/${c.id}" />" class="btn btn-success">Cập nhật</a>
                    </td>


        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="<c:url value="/js/main.js" />"></script>