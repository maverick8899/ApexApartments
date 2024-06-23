import { useEffect, useState } from "react";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "./MySpinner";
import { Badge, Button, Col, Container, Form, Nav, Navbar, NavDropdown, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import styled from 'styled-components';

const StyledNavbar = styled(Navbar)`
  background-color: #343a40 !important;
`;

const StyledNavbarBrand = styled(Navbar.Brand)`
  color: #ffffff !important;
  font-size: 1.5em;
  font-weight: bold;

  &:hover {
    color: #cccccc !important;
  }
`;

const StyledNavLink = styled(Nav.Link)`
  color: #ffffff !important;
  font-size: 1.1em;
  margin-right: 15px;

  &:hover {
    color: #cccccc !important;
  }
`;

const Header = () => {
    // const [customer, setCustomer] = useState(null);
    // const loadCus = async () => {
    //     let res = await Apis.get(endpoints['customer'])
    //     setCustomer(res.data);
    // }

    // useEffect(() => {
    //     loadCus();
    // }, [])

    // if (customer === null)
    //     return <MySpinner />;

    return (
        <StyledNavbar expand="lg">
            <Container>
                <StyledNavbarBrand href="#home">Quản lý chung cư</StyledNavbarBrand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <StyledNavLink href="#home">Trang chủ</StyledNavLink>
                        <StyledNavLink href="/Relativeparkcard">Thêm thẻ giữ xe người thân</StyledNavLink>
                        <StyledNavLink href="/MerchandiseCabinet">Xem tủ đồ</StyledNavLink>
                        <StyledNavLink href="/Service">Dịch vụ</StyledNavLink>
                        <StyledNavLink href="/feedback">Feedback</StyledNavLink>
                        <StyledNavLink href="/login">Đăng nhập</StyledNavLink>
                        <StyledNavLink href="/ReceiptList">Hóa đơn</StyledNavLink>
                        <StyledNavLink href="/Survey">Khảo sát</StyledNavLink>

                    </Nav>
                </Navbar.Collapse>
            </Container>
        </StyledNavbar>
    )
}

export default Header;
