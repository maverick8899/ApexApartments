 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">TẠO KHẢO SÁT</h1>

<c:url value="/addSurvey" var="action" />

<form action="${action}" method="post" class="search-form "   >
    <div id="hygiene" class="mb-3">
        <label for="questionInput" class="form-label">Tình hình vệ sinh</label>
        <input type="text" class="form-control" name="hygiene" placeholder="Enter your question"> 
    </div>
    <div id="infrastructure" class="mb-3">
        <label for="questionInput" class="form-label">Cơ sở hạ tầng</label>
        <input type="text" class="form-control" name="infrastructure" placeholder="Enter your question"> 
    </div>
    <div id="service" class="mb-3">
        <label for="questionInput" class="form-label">Dịch vụ</label>
        <input type="text" class="form-control" name="service" placeholder="Enter your question"> 
    </div>  
    <button type="submit" class="btn btn-success">Submit</button>
</form> 