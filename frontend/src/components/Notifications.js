import React, { useEffect, useState } from 'react';
import webSocketService from '../service/WebSocketService';
import { ToastContainer, toast } from 'react-toastify';


const Notifications = () => {
    const [messages, setMessages] =useState([]);
    const showNotification = (message) => {
        toast(message, {
            position: "bottom-left", // Vị trí hiển thị thông báo
            autoClose: 5000, // Thời gian tự động đóng thông báo (ms)
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
        });
    };
    useEffect(() => {
        let unSubscribe = webSocketService.connect(message => {
            setMessages(prevMessages => [...prevMessages, message]);
            showNotification(message); // Hiển thị thông báo đẩy
        });

        return () => {
            if (unSubscribe) unSubscribe(); // Ngắt kết nối WebSocket khi component bị unmount
        };
    }, []);

    return (
        <div>
            <h2>Notifications</h2>
            <ul>
                {messages.map((message, index) => (
                    <li key={index}>{message}</li>
                ))}
            </ul>
            <ToastContainer /> {/* Container để hiển thị các thông báo */}
        </div>
    );
};

export default Notifications;