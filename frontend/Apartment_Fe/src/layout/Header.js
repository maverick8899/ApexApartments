import { useContext } from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import UserContext from '../contexts/UserContext';
import Cookies from 'js-cookie';

const Header = () => {
    const [user, dispatch] = useContext(UserContext);

    const handleLogout = () => {
        dispatch({ type: 'logout' });
        Cookies.remove('token');
        Cookies.remove('user');
        window.location.href = '/login'; // Chuyển hướng sau khi đăng xuất
    };

    return user ? (
        <Navbar expand="lg" style={{ backgroundColor: '#343a40' }}>
            <Container>
                <Navbar.Brand as={Link} to="/" style={{ color: '#ffffff', fontSize: '1.5em', fontWeight: 'bold' }}>
                    Quản lý chung cư
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="/" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Trang chủ
                        </Nav.Link>
                        <Nav.Link as={Link} to="/Relativeparkcard" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Thêm thẻ giữ xe người thân
                        </Nav.Link>
                        <Nav.Link as={Link} to="/MerchandiseCabinet" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Xem tủ đồ
                        </Nav.Link>
                        <Nav.Link as={Link} to="/Service" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Dịch vụ
                        </Nav.Link>
                        <Nav.Link as={Link} to="/Feedback" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Feedback
                        </Nav.Link>
                        <Nav.Link as={Link} to="/Survey" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Khảo sát
                        </Nav.Link>
                        <Nav.Link as={Link} to="/ReceiptList" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Hóa đơn
                        </Nav.Link>
                        <Nav.Link as={Link} to="/chatapp" style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Chat
                        </Nav.Link>
                        <Nav.Link onClick={handleLogout} style={{ color: '#ffffff', fontSize: '1.1em', marginRight: '15px' }}>
                            Đăng xuất
                        </Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    ) : null; // Không hiển thị header nếu chưa đăng nhập
};

export default Header;
