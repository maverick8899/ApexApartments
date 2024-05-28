import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

class WebSocketService {
    constructor() {
        this.socket = new SockJS('http://localhost:8080/apartment_manager/ws');
        this.stompClient = Stomp.over(this.socket);
    }

    connect(onMessageReceived) {
        let subscription = null;
        this.stompClient.connect({}, frame => {
            console.log('Connected: ' + frame);
            subscription = this.stompClient.subscribe('/topic/notifications', message => {
                onMessageReceived(message.body);
            });
        });
        return () => {
            if (subscription) subscription.unsubscribe();
        };
    }
    
    disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }
}

const webSocketService = new WebSocketService();
export default webSocketService;
