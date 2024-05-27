import { ViewIcon } from '@chakra-ui/icons';
import {
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalFooter,
    ModalBody,
    ModalCloseButton,
    Button,
    useDisclosure,
    FormControl,
    Input,
    useToast,
    Box,
    IconButton,
    Spinner,
} from '@chakra-ui/react';
import { useEffect, useRef, useState } from 'react';
import store from '../../store';
import axiosService from '../../services/Axios';
import UserBadgeItem from './User/UserBadgeList';
import UserList from './User/UserList';
import useDebounce from '../../hook/useDebounce';

const UpdateGroupChatModal = ({ fetchMessages, fetchAgain, setFetchAgain }) => {
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [groupChatName, setGroupChatName] = useState('');
    const [search, setSearch] = useState('');
    const [searchResult, setSearchResult] = useState([]);
    const [loading, setLoading] = useState(false);
    const [renameLoading, setRenameLoading] = useState(false);
    const toast = useToast();
    const inputRef = useRef(null);
    const [isValid, setIsValid] = useState(true);
    const debounceValue = useDebounce(search, 500);

    const { selectedChat, user } = store((state) => state.data);
    const { setSelectedChat } = store((state) => state);

    //
    const handleAddUser = async (userAdd) => {
        if (selectedChat.users.find((user) => user._id === userAdd._id)) {
            return toast({
                title: 'User Already in group!',
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }

        if (selectedChat.groupAdmin._id !== user._id) {
            return toast({
                title: 'Only admins can add someone!',
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }

        try {
            setLoading(true);
            const { data } = await axiosService.put(`chat/group_add`, {
                chatId: selectedChat._id,
                userId: userAdd._id,
            });
            console.log(data);

            setSelectedChat(data);
            setFetchAgain(!fetchAgain);
            fetchMessages();
            setGroupChatName('');
        } catch (error) {
            toast({
                title: 'Error Occurred!',
                description: error.response.data.message,
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }
        setLoading(false);
    };
    const handleRename = async () => {
        if (!groupChatName) {
            setIsValid(false);
            inputRef.current.focus();
            return toast({
                title: 'Enter Name of Group!',
                status: 'warning',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }

        try {
            setRenameLoading(true);
            const { data } = await axiosService.put(`chat/group_rename`, {
                chatId: selectedChat._id,
                chatName: groupChatName,
            });
            onClose();
            console.log(data);
            // setSelectedChat("");
            setSelectedChat(data);
            setFetchAgain(!fetchAgain);
            setGroupChatName('');
        } catch (error) {
            toast({
                title: 'Error Occurred!',
                description: error.response.data.message,
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }
        setRenameLoading(false);
    };
    const handleRemove = async (userRemove) => {
        //mình không phải admin mà xóa user không phải mình -> return
        //đổi lại mình có thể Leave vì không bị dính condition
        if (selectedChat.groupAdmin._id !== user._id && userRemove._id !== user._id) {
            return toast({
                title: 'Only admin can remove someone!',
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }
        try {
            setLoading(true);
            const { data } = await axiosService.put(`chat/group_remove`, {
                chatId: selectedChat._id,
                userId: userRemove._id,
            });
            console.log('remove', data);

            //leave Chat
            userRemove._id === user._id ? setSelectedChat() : setSelectedChat(data);
            setGroupChatName('');
            setFetchAgain(!fetchAgain);
            // fetchMessages();
        } catch (error) {
            toast({
                title: 'Error Occurred!',
                description: error.response.data.message,
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom',
            });
        }
        setLoading(false);
    };
    const handleSearch = async () => {
        try {
            setLoading(true);
            const { data } = await axiosService.get(`chat/search`, {
                params: {
                    keyword: debounceValue,
                },
            });
            setSearchResult(data);
            console.log(data);
        } catch (error) {
            return toast({
                title: 'Error Occurred!',
                description: 'Failed to Load the Search Results',
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom-left',
            });
        }
        // Các câu lệnh sau đó vẫn được thực thi dù return trong catch
        setLoading(false);
    };
    useEffect(() => {
        if (!debounceValue?.trim()) {
            return setSearchResult([]);
        }
        handleSearch();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [debounceValue]);
    //
    return (
        <>
            <IconButton
                display={{ base: 'flex' }}
                icon={<ViewIcon />}
                onClick={() => {
                    onOpen();
                    setIsValid(true);
                }}
            />

            <Modal onClose={onClose} isOpen={isOpen} isCentered>
                <ModalOverlay />
                <ModalContent>
                    <ModalHeader fontSize="35px" display="flex" justifyContent="center">
                        {selectedChat.chatName}
                    </ModalHeader>
                    <ModalCloseButton />
                    <ModalBody display="flex" flexDirection="column" alignItems="center">
                        <Box w="100%" display="flex" flexWrap="wrap" pb={3}>
                            <UserBadgeItem
                                users={selectedChat.users}
                                admin={selectedChat.groupAdmin}
                                handleFunction={handleRemove}
                            />
                        </Box>
                        <FormControl display="flex" gap={2}>
                            <Input
                                placeholder="Chat Name"
                                mb={3}
                                value={groupChatName}
                                onChange={(e) => setGroupChatName(e.target.value)}
                                ref={inputRef}
                                autoFocus
                                focusBorderColor={isValid ? '' : 'pink.400'}
                            />
                            <Button
                                variant="solid"
                                colorScheme="teal"
                                ml={1}
                                isLoading={renameLoading}
                                onClick={handleRename}
                            >
                                Update
                            </Button>
                        </FormControl>
                        <FormControl>
                            <Input
                                placeholder="Add User to group"
                                mb={1}
                                onChange={(e) => setSearch(e.target.value)}
                            />
                        </FormControl>
                        {loading ? (
                            <Spinner size="lg" />
                        ) : (
                            <UserList users={searchResult} handleFunc={handleAddUser} />
                        )}
                    </ModalBody>
                    <ModalFooter>
                        <Button onClick={() => handleRemove(user)} colorScheme="red">
                            Leave Group
                        </Button>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </>
    );
};

export default UpdateGroupChatModal;
