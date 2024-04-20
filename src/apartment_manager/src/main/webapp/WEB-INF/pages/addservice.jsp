<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/addservice" var="action" />
<%--@elvariable id="service" type=""--%>
<form:form method="post" action="${action}" modelAttribute="service" enctype="multipart/form-data">
<form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="name" id="name" placeholder="Tên dịch vụ" />
        <label for="name">Tên dịch vụ</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="cost" id="cost" placeholder="Giá dịch vụ" />
        <label for="cost">Giá dịch vụ</label>
        <form:errors path="cost" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${service.id == null}">Thêm dich vu</c:when>
                <c:otherwise>Cập nhật dich vu</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>