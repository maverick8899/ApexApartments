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
<!--//-->
<section class="container">
    <c:url value="/addReceipt" var="action" />

    <form action="${action}" method="post" class="search-form">
        <table class="table table-hover">
            <thead>
                <tr>
                    <!--<th>Mã HĐ</th>-->
                    <th>Mã KH</th>
                    <th>Tên Khách hàng</th>
                    <th>email</th>
                </tr>
            </thead>
            <tbody> 
                <c:if test="${useServices != null}"> 
                    <tr>
                        <!--<td>${useServices[0].receiptId}</td>-->
                <input type="text" hidden="true" name="customer_id" value="${useServices[0].customer_id}">
                <td>${useServices[0].customer_id}</td>
                <td>${useServices[0].customer_name}</td>
                <td>${useServices[0].customer_email}</td>
                <td>
                    <c:url value="${action}/${useServices[0].customer_id}" var="apiDel" />
                    <!--<a href="<c:url value="/receipt/${useServices[0].receiptId}" />" class="btn btn-success">Cập nhật</a>-->
                    <!--<button class="btn btn-danger" onclick=delReceipt('api/receipt/${r.receiptId}')">Xóa</button>-->
                </td>
                </tr> 
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

                <c:if test="${useServices != null}">
                    <%-- Khai báo và khởi tạo biến đếm --%>
                    <c:set var="counter" value="0" />

                    <c:forEach items="${useServices}" var="r">
                        <tr>
                            <td><input type="text"  name="services[${counter}].service_id" value="${r.service_id}"></td>

                            <td>${r.service_name}</td>
                            <td>${r.service_description}</td> 
                            <td>
                                <input type="text" name="services[${counter}].detail_receipt_quantity">
                            </td>  
                            <td>${r.service_unit}</td>
                            <td>${r.useService_date}</td> 
                            <c:choose>
                                <c:when test="${r.receiptPay != true}"> 
                                    <td>
                                        <input type="date" name="services[${counter}].date">
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${r.receiptDate}</td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${r.receiptPay != true}"> 
                                    <td>
                                        <input type="text" name="services[${counter}].detail_receipt_cost">
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${r.receiptDetailCost}</td>
                                </c:otherwise>
                            </c:choose>

                            <td>
                                <c:url value="${action}/${r.receiptId}" var="apiDel" />
                                <!--<a href="<c:url value="/receipt/${r.receiptId}" />" class="btn btn-success">Cập nhật</a>-->
                                <!--<button class="btn btn-danger" onclick=delReceipt('api/receipt/${r.receiptId}')">Xóa</button>-->
                            </td>
                        </tr>

                        <%-- Tăng giá trị của biến đếm sau mỗi lần lặp --%>
                        <c:set var="counter" value="${counter + 1}" />
                    </c:forEach>

                </c:if>

                <%--<form:hidden path="id" />--%>
            </tbody>
        </table>

        <table class="table table-hover ">
            <thead>
                <tr>
                    <th>Mã Thẻ Xe</th>     
                    <th>Mô Tả</th> 
                    <th>Ngày dùng đầu</th>
                    <th>Ngày dùng cuối</th>
                    <th>Tổng</th>
                </tr>
            </thead>
            <tbody>

                <c:if test="${parkCards != null}">
                    <%-- Khai báo và khởi tạo biến đếm --%>
                    <c:set var="counter" value="0" />

                    <c:forEach items="${parkCards}" var="r">
                        <tr>
                            <td>${r.id}</td>
                            <input type="text" hidden="true" name="parkCards[${counter}].id" value="${r.id}">
                            <td>${r.description}</td> 
                            <td>${r.dateCreate}</td>
                            <td>${r.expiry}</td> 
                            <c:choose>
                                <c:when test="${r.cost == null}">
                                    <td><input type="text" name="parkCards[${counter}].cost"></td>
                                    </c:when>
                                    <c:otherwise>
                                    <td>${r.cost}</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <%-- Tăng giá trị của biến đếm sau mỗi lần lặp --%>
                        <c:set var="counter" value="${counter + 1}" />
                    </c:forEach>

                </c:if>

                <%--<form:hidden path="id" />--%>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary">Submit</button> 
    </form>
</section>
<script src="<c:url value="/js/main.js" />"></script>