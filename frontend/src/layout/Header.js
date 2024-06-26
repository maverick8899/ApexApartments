import { useContext, useEffect, useState } from 'react';
import Apis, { endpoints } from '../configs/Apis';
import MySpinner from './MySpinner';
import {
    Badge,
    Button,
    Col,
    Container,
    Form,
    Nav,
    Navbar,
    NavDropdown,
    Row,
} from 'react-bootstrap';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import UserContext from '../contexts/UserContext';
import Cookies from 'js-cookie';
import { useReducer } from 'react';
// import UserReducer from './reducers/UserReducer';
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
    let currentUser = null;
    if (Cookies.get('user')) currentUser = JSON.parse(Cookies.get('user'));

    const [user, dispatch] = useReducer(useReducer, currentUser);
    // const [user, dispatch] = useContext(UserContext)
    const handleLogout = () => {
        dispatch({ type: 'logout' });
        Cookies.remove('token');
        Cookies.remove('user');
    };

    return (
        <StyledNavbar expand="lg">
            <Container>
                <StyledNavbarBrand href="/">Quản lý chung cư</StyledNavbarBrand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <StyledNavLink href="/home">Trang chủ</StyledNavLink>
                        <StyledNavLink href="/Relativeparkcard"> Thêm thẻ giữ xe</StyledNavLink>
                        <StyledNavLink href="/MerchandiseCabinet">Xem tủ đồ</StyledNavLink>
                        <StyledNavLink href="/Service">Dịch vụ</StyledNavLink>
                        <StyledNavLink href="/Feedback">Feedback</StyledNavLink>
                        <StyledNavLink href="/Survey">Khảo sát</StyledNavLink>
                        <StyledNavLink href="ReceiptList">Hóa đơn</StyledNavLink>
                        {/* <StyledNavLink href='/login'>Đăng nhập</StyledNavLink> */}
                        <StyledNavLink onClick={handleLogout}>Đăng xuất</StyledNavLink>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </StyledNavbar>
    );
};

export default Header;
