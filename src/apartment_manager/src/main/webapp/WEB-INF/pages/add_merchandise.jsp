<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/merchandise" var="action" />
<%--@elvariable id="merchandise" type=""--%>
<h1>${id}</h1>
<form:form id="merchandiseForm" method="post" action="${action}" modelAttribute="merchandise" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên hàng hóa" />
        <label for="name">Tên hàng hóa</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="note" id="note" placeholder="note" />
        <label for="note">note</label>
        <form:errors path="note" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="unit" id="unit" placeholder="unit" />
        <label for="unit">unit</label>
        <form:errors path="unit" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="date" id="date" placeholder="date" />
        <label for="date">date</label>
        <form:errors path="date" element="div" cssClass="text-danger" />
    </div>
    <div class="mb-3 mt-3">
        <label>Trạng thái</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" id="1" name="active" value="1" checked>
            <label class="form-check-label" for="1">Chưa nhận</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" id="0" name="active" value="0">
            <label class="form-check-label" for="0">Đã nhận</label>
        </div>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button id="submitButton" class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${merchandise.id == null}">Thêm Hàng</c:when>
                <c:otherwise>Cập nhật Hàng</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

<script>
    document.getElementById('merchandiseForm').addEventListener('submit', function(event) {
        event.preventDefault();

        let submitButton = document.getElementById('submitButton');

        if (submitButton.textContent.trim() === 'Thêm Hàng') {
            let message = "Bạn có thông báo mới về tủ đồ ";

            fetch('http://localhost:8080/apartment_manager/api/notify', {
                method: 'POST',
                headers: {
                    'Content-Type': 'text/plain'
                },
                body: message,
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.text();
                })
                .then(text => {
                    console.log('Success:', text);
                    document.getElementById('merchandiseForm').submit(); // Tiếp tục gửi form sau khi fetch thành công
                })
                .catch((error) => {
                    console.error('Fetch error:', error);
                });
        } else {
            document.getElementById('merchandiseForm').submit(); // Tiếp tục gửi form đối với cập nhật
        }
    });
</script>
