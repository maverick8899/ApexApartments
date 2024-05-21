<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 13-Apr-24
  Time: 03:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Quản lý chung cư </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/" />">Trang chủ </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/customers" />">Quản lý khách hàng</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/account" />">Quản lý tài khoản</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Quản lý tủ đồ</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/service" />">Quản lý dịch vụ</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Tạo khảo sát</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/room" />">Quản lý phòng</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/receipt" />">Quản lý hóa đơn</a>
            </li>
        </ul>
    </div>

</nav>
