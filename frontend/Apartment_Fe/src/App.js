import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Home from './components/Home';
import Footer from './layout/Footer';
import Header from './layout/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from 'react-bootstrap';
import Relativeparkcard from './components/Relativeparkcard';
import MerchandiseCabinet from './components/merchandisecabinet';
import Service from './components/Service';
import Notifications from './components/Notifications';
import ReceiptList from './components/Receipt/ReceiptList';
import Login from './components/Login';
import UserContext from './contexts/UserContext';
import UserReducer from './reducers/UserReducer';
import Cookies from 'js-cookie';
import { useReducer } from 'react';
import Feedback from './components/Feedback';
import Survey from './components/Survey';
import ChatApp from './components/ChatApp/ChatApp';

const App = () => {
    let currentUser = null;
    if (Cookies.get('user')) currentUser = JSON.parse(Cookies.get('user'));

    const [user, dispatch] = useReducer(UserReducer, currentUser);
    
    return (
        <>
            <UserContext.Provider value={[user, dispatch]}>
                <BrowserRouter>
                    {user && <Header />} {/* Hiển thị header nếu đã đăng nhập */}
                    <Container>
                        <Routes>
                            <Route path="/login" element={<Login />} />
                            <Route path="/" element={user ? <Home /> : <Navigate to="/login" />} />
                            <Route path="/Relativeparkcard" element={user ? <Relativeparkcard /> : <Navigate to="/login" />} />
                            <Route path="/Notifications" element={user ? <Notifications /> : <Navigate to="/login" />} />
                            <Route path="/MerchandiseCabinet" element={user ? <MerchandiseCabinet /> : <Navigate to="/login" />} />
                            <Route path="/Service" element={user ? <Service /> : <Navigate to="/login" />} />
                            <Route path="/ReceiptList" element={user ? <ReceiptList /> : <Navigate to="/login" />} />
                            <Route path="/ChatApp" element={user ? <ChatApp /> : <Navigate to="/login" />} />
                            <Route path="/Feedback" element={user ? <Feedback /> : <Navigate to="/login" />} />
                            <Route path="/Survey" element={user ? <Survey /> : <Navigate to="/login" />} />
                        </Routes>
                    </Container>
                    <Footer />
                </BrowserRouter>
            </UserContext.Provider>
        </>
    );
};

export default App;
