<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/accounts" var="action" />
<%--@elvariable id="account" type=""--%>
<form:form method="post" action="${action}" modelAttribute="account" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="username" id="username" placeholder="username" />
        <label for="username">username</label>
        <form:errors path="username" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="password" id="password" placeholder="password" />
        <label for="password">password</label>
        <form:errors path="password" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="role" id="role" placeholder="Vai trò" />
        <label for="role">Vai trò</label>
        <form:errors path="role" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${account.id == null}">Thêm tài khoản</c:when>
                <c:otherwise>Cập nhật tài khoản</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>