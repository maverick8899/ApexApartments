import io from 'socket.io-client';
import { FormControl, IconButton, Input, Spinner, useToast } from '@chakra-ui/react';
import { Box, Text } from '@chakra-ui/layout';
import { ArrowBackIcon } from '@chakra-ui/icons';
import Lottie from 'react-lottie';

import animationData from '../../animations/typing.json';
import store from '../../store';
import { getSender, getSenderFull } from '../../services/Chat.service';
import Profile from './Profile';
import UpdateGroupChatModal from './UpdateGroupChat';
import { useEffect, useState } from 'react';
import axiosService from '../../services/Axios';
import ScrollableChat from './ScrollableChat';

//ô chat
const ENDPOINT = 'http://localhost:8080';
let socket, selectedChatCompare;

const SingleChat = ({ fetchAgain, setFetchAgain }) => {
    const { selectedChat, user, notification } = store((state) => state.data);
    const { setSelectedChat, setNotification } = store((state) => state);
    const [messages, setMessages] = useState([]);
    const [loading, setLoading] = useState(false);
    const [newMessage, setNewMessage] = useState('');
    const [socketConnected, setSocketConnected] = useState(false);
    const [typing, setTyping] = useState(false); //Input
    const [isTyping, setIsTyping] = useState(false); //socket
    const toast = useToast();
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: animationData,
        rendererSettings: {
            preserveAspectRatio: 'xMidYMid slice',
        },
    };
    //
    const fetchMessages = async () => {
        if (!selectedChat) return;

        try {
            setLoading(true);

            const { data } = await axiosService.get(`message/${selectedChat._id}`);
            setMessages(data);
            setLoading(false);

            socket.emit('join chat', selectedChat?._id);
        } catch (error) {
            toast({
                title: 'Error Occurred!',
                description: 'Failed to Load the Messages',
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }
    };
    const sendMessage = async (event) => {
        if (event.key === 'Enter' && newMessage) {
            socket.emit('stop typing', selectedChat._id);
            try {
                setNewMessage('');
                const { data } = await axiosService.post('message', {
                    content: newMessage,
                    chatId: selectedChat._id,
                });
                socket.emit('new message', data);
                setMessages([...messages, data]);
            } catch (error) {
                toast({
                    title: 'Error Occurred!',
                    description: 'Failed to send the Message',
                    status: 'error',
                    duration: 3000,
                    isClosable: true,
                    position: 'bottom',
                });
            }
        }
    };
    const typingHandler = (value) => {
        setNewMessage(value);

        if (!socketConnected) return;

        if (!typing) {
            setTyping(true);
            socket.emit('typing', selectedChat._id);
        }
        let lastTypingTime = new Date().getTime();
        var timerLength = 1000;

        setTimeout(() => {
            var timeNow = new Date().getTime();
            var timeDiff = timeNow - lastTypingTime; //
            if (timeDiff >= timerLength && typing) {
                socket.emit('stop typing', selectedChat._id);
                setTyping(false);
            }
        }, timerLength);
        // setTyping(false);
    };
    useEffect(() => {
        fetchMessages();

        selectedChatCompare = selectedChat;
        // eslint-disable-next-line
    }, [selectedChat]);
    // Socket
    useEffect(() => {
        socket = io(ENDPOINT);
        socket.emit('setup', user);
        socket.on('connected', () => setSocketConnected(true));
        socket.on('typing', () => setIsTyping(true));
        socket.on('stop typing', () => setIsTyping(false));

        // eslint-disable-next-line
    }, []);
    useEffect(() => {
        socket.on('message received', (newMessageReceived) => {
            if (
                //scp là selectedChat previous
                !selectedChatCompare || // if chat is not selected or doesn't match current chat
                selectedChatCompare._id !== newMessageReceived.chat._id
            ) {
                if (!notification.includes(newMessageReceived)) {
                    setNotification([newMessageReceived, ...notification]);
                    setFetchAgain(!fetchAgain);
                }
            } else {
                setMessages([...messages, newMessageReceived]);
            }
        });
    });
    //
    return (
        <>
            {selectedChat ? (
                <>
                    <Text
                        fontSize={{ base: '28px', md: '30px' }}
                        pb={3}
                        px={2}
                        w={'100%'}
                        display={'flex'}
                        justifyContent={{ base: 'space-between' }}
                        alignItems={'center'}
                    >
                        <IconButton
                            display={{ base: 'flex', md: 'none' }}
                            icon={<ArrowBackIcon />}
                            onClick={() => setSelectedChat('')}
                        />
                        {selectedChat.isGroupChat ? (
                            <>
                                {selectedChat.chatName}
                                <UpdateGroupChatModal
                                    fetchAgain={fetchAgain}
                                    setFetchAgain={setFetchAgain}
                                    fetchMessages={fetchMessages}
                                />
                            </>
                        ) : (
                            <>
                                {getSender(user, selectedChat.users)}
                                <Profile user={getSenderFull(user, selectedChat.users)} />
                            </>
                        )}
                    </Text>
                    <Box
                        display="flex"
                        flexDirection="column"
                        justifyContent="flex-end"
                        p={3}
                        bg="#E8E8E8"
                        w="100%"
                        h="100%"
                        borderRadius="lg"
                        overflowY="hidden"
                        backgroundColor={'rgba(255, 255, 255, 0.5)'}
                    >
                        {loading ? (
                            <Spinner size="xl" w={20} h={20} alignSelf="center" margin="auto" />
                        ) : (
                            <div
                                className="messages"
                                style={{
                                    display: 'flex',
                                    flexDirection: 'column',
                                    overflowY: 'scroll',
                                    scrollbarWidth: 'none',
                                }}
                            >
                                <ScrollableChat messages={messages} />
                            </div>
                        )}
                        {isTyping ? (
                            <div style={{ display: 'inline-block' }}>
                                <Lottie
                                    options={defaultOptions}
                                    // height={70}
                                    width={50}
                                    style={{
                                        marginTop: '0',
                                        marginBottom: -5,
                                        marginLeft: 15,
                                        display: 'inline-block',
                                    }}
                                />
                            </div>
                        ) : (
                            <></>
                        )}
                        <FormControl onKeyDown={sendMessage} id="first-name" isRequired mt={3}>
                            <Input
                                variant="filled"
                                bg="#CEE6F3"
                                placeholder="Enter a message.."
                                value={newMessage}
                                onChange={(e) => typingHandler(e.target.value)}
                                autoFocus
                            />
                        </FormControl>
                    </Box>
                </>
            ) : (
                <Box display={'flex'} alignItems={'center'} justifyContent={'center'} h={'100%'}>
                    <Text fontSize={'3xl'} pb={3}>
                        Click on a user or group to start chatting
                    </Text>
                </Box>
            )}
        </>
    );
};

export default SingleChat;
