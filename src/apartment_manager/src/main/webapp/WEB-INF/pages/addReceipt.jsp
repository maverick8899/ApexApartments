<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/addReceipt" var="action" />

<form action="${action}" method="post" class="search-form "   >
    <legend>Hóa đơn</legend>
    <fieldset>
        <div class="mb-3">
            <label for="disabledTextInput" class="form-label">Tên cư dân</label>
            <select class="form-select" aria-label="Default select example"  name="customer_id">
                <option selected>Tên cư dân</option>
                <c:if test="${customers != null}">
                    <c:forEach items="${customers}" var="customer">
                        <option value="${customer.id}">${customer.id} - ${customer.name}</option> 
                    </c:forEach>
                </c:if>
            </select>
        </div>

        <div id="services-container" class="border border-3 border-info-subtle">
            <div class="mb-3 mt-3 d-flex gap-3 w-100% align-items-center service-element" style="height: 50px">
                <div class="mb-2">
                    <select class="form-select" aria-label="Default select example" name="services[0].service_id">
                        <option selected>Tên dịch vụ</option>
                        <c:forEach items="${services}" var="service">
                            <option value="${service.id}">${service.id} - ${service.name}</option> 
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="use_service_date" class="form-label">Ngày sử dụng dịch vụ</label>
                    <input type="date" name="services[0].date">
                </div>
                <div>
                    <label for="detail_receipt_quantity" class="form-label">Số lượng sử dụng</label>
                    <input type="text" name="services[0].detail_receipt_quantity">
                </div>
                <div>
                    <label for="detail_receipt_cost" class="form-label">Tổng chi trả vnd</label>
                    <input type="text" name="services[0].detail_receipt_cost">
                </div>
                <button type="button" class="btn btn-danger remove-service-btn">Xóa</button>
            </div>
        </div>
        <button type="button" class="btn btn-primary add-service-btn">Thêm dịch vụ</button>



        <div class="mb-3">
            <select class="form-select" aria-label="Default select example"  name="receipt_isPay">
                <option selected>Tình trạng thanh toán</option>
                <option value="0">Chưa thanh toán</option> 
                <option value="1">Đã thanh toán</option> 
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </fieldset>
</form>

<script>
    $(document).ready(function() {
    let serviceIndex = 1;
    // Thêm khối dịch vụ mới
    $('.add-service-btn').click(function() {

    let newService = `
            <div class="mb-3 mt-3 d-flex gap-3 w-100% align-items-center service-element" style="height: 50px">
                <div class="mb-2">
                    <select class="form-select" aria-label="Default select example" name="services[` + serviceIndex + `].service_id">
                        <option selected>Tên dịch vụ</option>
    <c:forEach items="${services}" var="service">
                            <option value="${service.id}">${service.id} - ${service.name}</option> 
    </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="use_service_date" class="form-label">Ngày sử dụng dịch vụ</label>
                    <input type="date" name="services[` + serviceIndex + `].date">
                </div>
                <div>
                    <label for="detail_receipt_quantity" class="form-label">Số lượng sử dụng</label>
                    <input type="text" name="services[` + serviceIndex + `].detail_receipt_quantity">
                </div>
                <div>
                    <label for="detail_receipt_cost" class="form-label">Tổng chi trả vnd</label>
                    <input type="text" name='services[` + serviceIndex + `].detail_receipt_cost'>
                </div>
                <button type="button" class="btn btn-danger remove-service-btn">Xóa</button>
            </div>`;
    $('#services-container').append(newService);
    serviceIndex++;
    });
    // Xóa khối dịch vụ
    $('#services-container').on('click', '.remove-service-btn', function() {
    $(this).closest('.service-element').remove();
    });
    });
</script>
