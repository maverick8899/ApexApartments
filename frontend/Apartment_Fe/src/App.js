import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Home from './components/Home'
import Footer from './layout/Footer'
import Header from './layout/Header'
import 'bootstrap/dist/css/bootstrap.min.css'
import { Container } from 'react-bootstrap'
import Relativeparkcard from './components/Relativeparkcard'
import MerchandiseCabinet from './components/merchandisecabinet'
import Service from './components/Service'
import Notifications from './components/Notifications'
import ReceiptList from './components/Receipt/ReceiptList'
import ChatApp from './components/ChatApp/ChatApp'
import Login from './components/Login'
import UserContext from './contexts/UserContext'
import UserReducer from './reducers/UserReducer'
import Cookies from 'js-cookie'
import { useReducer } from 'react'

const App = () => {
  let currentUser = null
  if (Cookies.get('user')) currentUser = JSON.parse(Cookies.get('user'))

  const [user, dispatch] = useReducer(UserReducer, currentUser)
  return (
    <>
      <UserContext.Provider value={[user, dispatch]}>
        <BrowserRouter>
          <Header />
          <Container>
            <Routes>
              <Route path='/login' element={<Login />} />
              <Route path='/' element={<Home />} />
              <Route path='/Relativeparkcard' element={<Relativeparkcard />} />
              <Route path='/Notifications' element={<Notifications />} />

              <Route
                path='/MerchandiseCabinet'
                element={<MerchandiseCabinet />}
              />
              <Route path='/Service' element={<Service />} />
              <Route path='/ReceiptList' element={<ReceiptList />} />
              <Route path='/ChatApp' element={<ChatApp />} />
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
      </UserContext.Provider>
    </>
  )
}

export default App
