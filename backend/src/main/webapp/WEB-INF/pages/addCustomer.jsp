<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/customers" var="action" />
<%--@elvariable id="customer" type=""--%>
<form:form method="post" action="${action}" modelAttribute="customer"  enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="name" id="name" placeholder="Tên khách hàng" />
        <label for="name">Tên khách hàng</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="address" id="address" placeholder="Địa chỉ" />
        <label for="address">Địa chỉ</label>
        <form:errors path="address" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="phoneNumber" id="phoneNumber" placeholder="Số điện thoại" />
        <label for="phoneNumber">Số điện thoại</label>
        <form:errors path="phoneNumber" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control"
                    path="email" id="email" placeholder="Email" />
        <label for="email">Email</label>
        <form:errors path="email" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="Date" class="form-control"
                    path="birthday" id="birthday" placeholder="Ngày sinh" />
        <label for="birthday">Ngày sinh</label>
        <form:errors path="birthday" element="div" cssClass="text-danger" />
    </div>
    <div class="mb-3 mt-3">
        <label >Giới tính</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" id="male" name="gender" value="male" checked>
            <label class="form-check-label" for="male">Nam</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" id="female" name="gender" value="female">
            <label class="form-check-label" for="female">Nữ</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" id="other" name="gender" value="other">
            <label class="form-check-label" for="female">other</label>
        </div>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="room" name="room" path="roomId">
            <c:forEach items="${room}" var="r">
                <c:choose>
                    <c:when test="${r.id == customer.roomId.id}">
                        <option value="${r.id}" selected>${r.code}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${r.id}">${r.code}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
        <label for="room" class="form-label">Phòng:</label>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Ten dich vu</th>
            <th>Cost</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${service}" var="s">
            <tr>
                <td>${s.name}</td>
                <td>${s.cost}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="form-floating mb-3 mt-3">
        <form:hidden path="accountId.id" />
        <form:input type="text" class="form-control"
                    path="accountId.username" id="accountId.username" placeholder="username" />
        <label for="name">username</label>

    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="accountId.password" id="accountId.password"  placeholder="password" />
        <label for="name">password </label>

    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="accountId.role" id="accountId.role" placeholder="Vai trò" />
        <label for="name">Vai trò</label>

    </div>



    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control"
                    path="merchandiseCabinetId.name" id="merchandiseCabinetId.code" placeholder="name" />
        <label for="name">name tủ đồ </label>
    </div>




<%--    </table>--%>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${customer.id == null}">Thêm khách hàng</c:when>
                <c:otherwise>Cập nhật khách hàng</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>