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
<h1 class="text-center text-info mt-1">QUẢN TRỊ HÓA ĐƠN</h1>

<section class="container">
    <c:url value="/receiptDetail" var="action" />
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Mã KH</th>
                <th>date</th>
                <th>Tên Khách hàng</th>
                <th>email</th>
            </tr>
        </thead>
        <tbody>

            <c:if test="${useServices != null}">
                <c:forEach items="${useServices}" var="r">
                    <tr>
                        <!--<td>${r.useService_id}</td>-->
                        <td>${r.customer_id}</td>
                        <td>${r.useService_date}</td>
                        <td>${r.customer_name}</td>
                        <td>${r.customer_email}</td>
                        <td>
                            <c:url value="${action}/${r.receiptId}" var="apiDel" />
                            <a href="<c:url value="/addReceipt?type=3&kw=${r.customer_id}&cusId=${r.customer_id}" />" class="btn btn-success">Tạo hóa đơn</a>
                            <!--<button class="btn btn-danger" onclick=delReceipt('api/receipt/${r.receiptId}')">Xóa</button>-->
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

            <%--<form:hidden path="id" />--%>
        </tbody>
    </table>


</section>
<script src="<c:url value="/js/main.js" />"></script>