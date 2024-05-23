<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th>receiptId.id</th>
        <th>ten khach hang</th>
        <th>serviceId.Id </th>
        <th>serviceId.name</th>
        <th>cost</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${detailreceipt}" var="c">
        <tr>
            <td>${c.receipt.id}</td>
            <td>${c.receipt.customerId.name}</td>
            <td>${c.service.id}</td>
            <td>${c.service.name}</td>
            <td>${c.cost}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="<c:url value="/js/main.js" />"></script>