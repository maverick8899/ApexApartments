import React, { useEffect } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

const NotificationComponent = () => {
    useEffect(() => {
        const socket = new SockJS('http://localhost:8080/ws');
        const stompClient = Stomp.over(socket);

        stompClient.connect({}, (frame) => {
            console.log('Connected: ' + frame);

            stompClient.subscribe('/topic/notifications', (message) => {
                alert(message.body);
            });
        });

        return () => {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
        };
    }, []);

    return <div>Notification Component</div>;
};

export default NotificationComponent;
