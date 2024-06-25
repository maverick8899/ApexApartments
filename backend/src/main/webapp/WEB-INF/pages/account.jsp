<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<a href="<c:url value="/accounts"/>" class="btn btn-info">Thêm tài khoản </a>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Id</th>
        <th>username</th>
<%--        <th>password</th>--%>
        <th>Vai trò</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${account}" var="c">
        <tr>

            <td>${c.id}</td>
            <td>${c.username}</td>
<%--            <td>${c.password}</td>--%>
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