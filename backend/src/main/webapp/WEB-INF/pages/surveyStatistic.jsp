<%-- 
    Document   : surveyStatistic
    Created on : Jun 11, 2024, 12:22:17 PM
    Author     : MAVERICK
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">THỐNG KÊ KHẢO SÁT</h1>

<h4>${statistics}</h4>

<table class="table table-hover ">
    <thead>
        <tr>
            <th>Tên khách hàng</th> 
            <th>ý kiến</th>
            <th>date</th>  
        </tr>
    </thead>
    <tbody>
        <c:if test="${personalOpinions != null}">
            <c:forEach items="${personalOpinions}" var="r">
                <tr>
                    <td>${r.customer_name}</td>
                    <td>${r.personal_opinion}</td> 
                    <td>${r.date}</td>
                </tr>
            </c:forEach>
        </c:if> 
    </tbody>
</table>