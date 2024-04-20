<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/addroom" var="action" />
<%--@elvariable id="room" type=""--%>
<form:form method="post" action="${action}" modelAttribute="room" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="code" id="code" placeholder="Mã phòng" />
        <label for="code">Mã phòng</label>
        <form:errors path="code" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="cost" id="cost" placeholder="Giá phòng" />
        <label for="cost">Giá phòng</label>
        <form:errors path="cost" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${room.id == null}">Thêm phòng</c:when>
                <c:otherwise>Cập nhật phòng</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>