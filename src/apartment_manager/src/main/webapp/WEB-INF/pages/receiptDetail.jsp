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
                <th>Mã HĐ</th>
                <th>Mã KH</th>
                <th>Tên Khách hàng</th>
                <th>email</th>
            </tr>
        </thead>
        <tbody>

            <c:if test="${receipts != null}">
                <%--<c:forEach items="${receipts}" var="r">--%>
                    <tr>
                        <td>${receipts[0].receiptId}</td>
                        <td>${receipts[0].customerId}</td>
                        <td>${receipts[0].customerName}</td>
                        <td>${receipts[0].customerEmail}</td>
                        <td>
                            <c:url value="${action}/${receipts[0].receiptId}" var="apiDel" />
                            <!--<a href="<c:url value="/receipt/${receipts[0].receiptId}" />" class="btn btn-success">Cập nhật</a>-->
                            <!--<button class="btn btn-danger" onclick=delReceipt('api/receipt/${r.receiptId}')">Xóa</button>-->
                        </td>
                    </tr>
                <%--</c:forEach>--%>
            </c:if>

            <%--<form:hidden path="id" />--%>
        </tbody>
    </table>

    <table class="table table-hover ">
        <thead>
            <tr>
                <th>Mã Dịch Vụ</th>
                <th>Tên Dịch Vụ</th>
                <th>Mô Tả</th>
                <th>Số Lượng</th>
                <th>Đơn vị</th>
                <th>Ngày dùng đầu</th>
                <th>Ngày dùng cuối</th>
                <th>Tổng</th>
            </tr>
        </thead>
        <tbody>

            <c:if test="${receipts != null}">
                <c:forEach items="${receipts}" var="r">
                    <tr>
                        <td>${r.serviceId}</td>
                        <td>${r.serviceName}</td>
                        <td>${r.serviceDescription}</td>
                        <td>${r.receiptDetailQuantity}</td>
                        <td>${r.serviceUnit} </td>
                        <td>${r.useServiceDate}</td>
                        <td>${r.receiptDate}</td>
                        <td>${r.receiptDetailCost} VNĐ</td>
                        <td>
                            <c:url value="${action}/${r.receiptId}" var="apiDel" />
                            <!--<a href="<c:url value="/receipt/${r.receiptId}" />" class="btn btn-success">Cập nhật</a>-->
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