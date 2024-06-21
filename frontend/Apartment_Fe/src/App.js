import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Footer from "./layout/Footer";
import Header from "./layout/Header";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from "react-bootstrap";
import Relativeparkcard from "./components/Relativeparkcard";
import MerchandiseCabinet from "./components/merchandisecabinet";
import Service from "./components/Service";
import Notifications from "./components/Notifications";
import ReceiptList from "./components/Receipt/ReceiptList";
import ChatApp from "./components/ChatApp/ChatApp";

const App = () => {
  return (
    <>
      <BrowserRouter>
        <Header />
        <Container>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/Relativeparkcard" element={<Relativeparkcard />} />
            <Route path="/Notifications" element={<Notifications />} />

            <Route path="/MerchandiseCabinet" element={<MerchandiseCabinet />} />
            <Route path="/Service" element={<Service/>} />
            <Route path="/ReceiptList" element={<ReceiptList/>} />
            <Route path="/ChatApp" element={<ChatApp/>} />



          </Routes>
        </Container>
        <Footer />
      </BrowserRouter>
    </>
  )
}

export default App;