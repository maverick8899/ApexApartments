import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.scss';
import { ChakraProvider } from '@chakra-ui/react';
import { CookiesProvider } from 'react-cookie';
import {
    createBrowserRouter,
    Route,
    createRoutesFromElements,
    RouterProvider,
} from 'react-router-dom';
import Home from './pages/Home.Page.jsx';
import Chat from './pages/Chat.Page.jsx';

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route
            path="/"
            element={
                <ChakraProvider>
                    <CookiesProvider>
                        <App />
                    </CookiesProvider>
                </ChakraProvider>
            }
        >
            <Route index element={<Home />} />
            <Route path="/chat" element={<Chat />} />
        </Route>,
    ),
);

ReactDOM.createRoot(document.getElementById('root')).render(
    <>
        <RouterProvider router={router} />
    </>,
);
