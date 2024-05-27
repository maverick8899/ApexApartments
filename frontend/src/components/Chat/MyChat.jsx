import { AddIcon } from '@chakra-ui/icons';
import { Box, Stack, Text } from '@chakra-ui/layout';
import { useToast } from '@chakra-ui/toast';
import { useEffect } from 'react';
import ChatLoading from './ChatLoading';
import { Avatar, Button } from '@chakra-ui/react';
import axiosService from '../../services/Axios';
import store from '../../store';
import { getPicture, getSender } from '../../services/Chat.service';
import GroupChatModal from './GroupChatModal';

//list chat at sidebar
const MyChat = ({ fetchAgain }) => {
    const { setChats, setSelectedChat } = store((state) => state);
    const { user, chats, selectedChat } = store((state) => state.data);
    const toast = useToast();
    //

    async function fetchChats() {
        try {
            const { data } = await axiosService.get('chat');
            console.log({ user, data, chats });

            if (data != chats) {
                setChats(data);
            }
            return toast({
                title: 'Nice!',
                status: 'success',
                duration: 3000,
                isClosable: true,
                position: 'bottom-left',
            });
        } catch (error) {
            console.log(error);
            toast({
                title: 'Error Occurred!',
                description: 'Failed to Load the chats',
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom-left',
            });
        }
    }

    useEffect(() => {
        fetchChats();
        // eslint-disable-next-line
    }, [fetchAgain]);

    //
    return (
        <Box
            display={{ base: selectedChat ? 'none' : 'flex', md: 'flex' }}
            flexDir="column"
            alignItems="center"
            p={3}
            bg="white"
            w={{ base: '100%', md: '31%' }}
            borderRadius="lg"
            borderWidth="1px"
            backgroundColor={'rgba(255, 255, 255, 0.6)'}
        >
            <Box
                pb={3}
                px={3}
                display="flex"
                w="100%"
                justifyContent="space-between"
                alignItems="center"
                gap={2}
            >
                <Text fontSize={{ base: '30px', md: '20px', lg: '30px' }}> My Chats</Text>
                <GroupChatModal>
                    <Button
                        display="flex"
                        fontSize={{ base: '17px', md: '13px', lg: '17px' }}
                        rightIcon={<AddIcon />}
                    >
                        New Group Chat
                    </Button>
                </GroupChatModal>
            </Box>
            <Box
                display="flex"
                flexDir="column"
                p={3}
                bg="#F8F8F8"
                h="fit-content"
                w={'100%'}
                borderRadius="lg"
            >
                {chats ? (
                    <Stack overflow={'auto'}>
                        {chats.map((chat) => (
                            <Box
                                onClick={() => {
                                    setSelectedChat(chat);
                                    console.log(chat);
                                }}
                                cursor="pointer"
                                bg={selectedChat === chat ? '#38B2AC' : '#E8E8E8'}
                                color={selectedChat === chat ? 'white' : 'black'}
                                px={3}
                                py={2}
                                borderRadius="lg"
                                key={chat?._id}
                                height={'60px'}
                                display={'flex'}
                                alignItems={'center'}
                            >
                                <Box display={'flex'} alignItems={'center'} gap={3}>
                                    <Avatar
                                        size={'sm'}
                                        cursor={'pointer'}
                                        src={
                                            !chat.isGroupChat
                                                ? getPicture(user, chat?.users)
                                                : chat.picture
                                        }
                                    />
                                    <Box>
                                        <Text fontSize={'17px'}>
                                            {!chat.isGroupChat
                                                ? getSender(user, chat?.users)
                                                : chat.chatName}
                                        </Text>
                                        {chat.latestMessage && (
                                            <Text fontSize="xs" color={'#164B60'}>
                                                <b>{chat.latestMessage.sender.name} : </b>
                                                {chat.latestMessage.content.length > 50
                                                    ? chat.latestMessage.content.substring(0, 51) +
                                                      '...'
                                                    : chat.latestMessage.content}
                                            </Text>
                                        )}
                                    </Box>
                                </Box>
                            </Box>
                        ))}
                    </Stack>
                ) : (
                    <ChatLoading />
                )}
            </Box>
        </Box>
    );
};

export default MyChat;
