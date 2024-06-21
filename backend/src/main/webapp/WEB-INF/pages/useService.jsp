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
<%@page import="java.util.List"%>
<%
    String pageParam = request.getParameter("page");
    String pageSizeParam = request.getParameter("pageSize");

    int currentPage = pageParam != null ? Integer.parseInt(pageParam) : 1;
    int pageSize = pageSizeParam != null ? Integer.parseInt(pageSizeParam) : 10;

    List<?> feedbacks = (List<?>) request.getAttribute("feedbacks");
    int totalItems = feedbacks != null ? feedbacks.size() : 0;
    int totalPages = (int) Math.ceil((double) totalItems / pageSize);

//    out.println("<p>currentPage: " + currentPage + "</p>");
//    out.println("<p>pageSize: " + pageSize + "</p>");
//    out.println("<p>totalItems: " + totalItems + "</p>");
//    out.println("<p>totalPages: " + totalPages + "</p>");
    int j = 0;
%>
<div class="d-flex justify-content-center">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item <%= currentPage == 1 ? "disabled" : ""%>">
                <a class="page-link" href="/apartment_manager/feedback?page=<%= currentPage - 1%>&pageSize=<%= pageSize%>">Previous</a>
            </li>

            <% for (int i = 1; i <= totalPages; i++) {%>
            <li class="page-item <%= currentPage == i ? "active" : ""%>">
                <a class="page-link" href="/apartment_manager/feedback?page=<%= i%>&pageSize=<%= pageSize%>"><%= i%></a>
            </li>
            <% }%>

            <li class="page-item <%= currentPage == totalPages ? "disabled" : ""%>">
                <a class="page-link" href="/apartment_manager/feedback?page=<%= currentPage + 1%>&pageSize=<%= pageSize%>">Next</a>
            </li>
        </ul>
    </nav>
</div>

<script>
    function navigatePage(page, pageSize, totalPages) {
        if (page < 1 || page > totalPages) {
            return;
        }
        const urlParams = new URLSearchParams(window.location.search);
        urlParams.set('page', page);
        urlParams.set('pageSize', pageSize);
        window.location.search = urlParams.toString();
    }
</script>
<script src="<c:url value="/js/main.js" />"></script>