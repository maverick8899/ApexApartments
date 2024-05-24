import { produce } from 'immer';
import { create } from 'zustand';
import { devtools, persist, subscribeWithSelector } from 'zustand/middleware';
import cookie from 'cookie';

const typeOf = (value) => Object.prototype.toString.call(value).slice(8, -1);
const store = (set) => ({
    setSelectedChat: (selectedChat) =>
        set(
            produce((state) => {
                state.data.selectedChat = selectedChat;
            }),
        ),
    setUser: (user) =>
        set(
            produce((state) => {
                state.data.user = user;
            }),
        ),
    setNotification: (notification) =>
        set(
            produce((state) => {
                state.data.notification = notification;
            }),
        ),
    setChats: (chats) => {
        if (typeOf(chats) === 'Array') {
            set(
                produce((state) => {
                    state.data.chats = chats;
                }),
            );
        }
    },
    setOtp: (otp) =>
        set(
            produce((state) => {
                state.data.otp = otp;
            }),
        ),

    data: {
        selectedChat: null, //
        cookies: cookie.parse(document.cookie),
        user: null,
        notification: [],
        chats: null, //list Chat
        otp: '',
    },
});

export default create(persist(store, { name: 'userInfo' }));
