 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN TRỊ HÓA ĐƠN</h1>

<section class="container">
    <c:url value="/receipt" var="action" />
    <div class="container">
        <div class="col-md-4 col-md-offset-3 d-flex flex-row mb-3 gap-2">
            <form action="${action}" method="get" class="search-form w-100"   >
                <select class="form-select" aria-label="Default select example" name="type">
                    <option value="0" selected>Kiểu</option>
                    <option value="1">Id khách hàng</option>
                    <option value="2">Tên khách hàng</option>
                    <option value="3">Id hóa đơn</option>
                    <option value="4">Ngày</option>                   
                    <option value="5">Tháng</option>
                    <option value="6">Năm</option>
                </select>
                <select class="form-select" aria-label="Default select example" name="isPay">
                    <option value="0" selected>Thanh Toán</option>
                    <option value="1">Đã Thanh Toán</option> 
                    <option value="2">Chưa Thanh Toán</option> 
                </select>
                <div class="form-group has-feedback mb-2">
                    <input type="text" class="form-control" name="kw" id="search" placeholder="search">
                    <span class="glyphicon glyphicon-search form-control-feedback"></span>
                </div>
                <button type="submit" class="btn btn-info"> Tìm</button>
                <a href="<c:url value="/addReceipt" />" class="btn btn-info">Thêm Hóa Đơn</a>                    
            </form>
        </div>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th>Mã HĐ</th>
                <th>Mã KH</th>
                <th>Tên Khách hàng</th>
                <th>email</th>
                <th>Tổng HĐ</th>
                <th>ngày</th>

            </tr>
        </thead>
        <tbody>

            <c:if test="${receipts != null}">
                <c:forEach items="${receipts}" var="r">
                    <tr>
                        <td>${r.receiptId}</td>
                        <td>${r.customerId}</td>
                        <td>${r.customerName}</td>
                        <td>${r.customerEmail}</td>
                        <td>${r.receiptTotal} VNĐ</td>
                        <td>${r.receiptDate}</td>
                        <td>
                            <c:url value="${action}/${r.receiptId}" var="apiDel" />
                            <a href="<c:url value="/receiptDetail/?type=3&kw=${r.receiptId}" />" class="btn btn-info">Chi Tiết</a>                            
                            <a href="<c:url value="/receipt/${r.receiptId}" />" class="btn btn-success">Cập nhật</a>
                            <button class="btn btn-danger" onclick="delReceipt('api/receipt/${r.receiptId}')">Xóa</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>


        </tbody>
    </table>
</section>

<script >
    async function delReceipt(path) {
        if (confirm("Bạn chắc chắn muốn xóa không?")) {
            try {
                const response = await fetch(path, {
                    method: "DELETE"
                });
                console.log(response);
                if (response.ok) {
                    location.reload(); // Tải lại trang sau khi xóa thành công
                } else {
                    throw new Error("Có lỗi xảy ra khi xóa.");
                }
            } catch (error) {
                console.error("Error:", error);
                alert("Có lỗi xảy ra khi xóa.");
            }
        }
    }
</script>