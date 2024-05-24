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
} from '@chakra-ui/react';
import axiosService from '../../services/Axios';
import { useEffect, useRef, useState } from 'react';
import useDebounce from '../../hook/useDebounce';
import UserList from './User/UserList';
import UserBadgeList from './User/UserBadgeList';
import store from '../../store';

const GroupChatModal = ({ children }) => {
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [groupChatName, setGroupChatName] = useState();
    const [selectedUsers, setSelectedUsers] = useState([]);
    const [search, setSearch] = useState('');
    const [searchResult, setSearchResult] = useState([]);
    const [loading, setLoading] = useState(false);
    const toast = useToast();
    const debounceValue = useDebounce(search, 500);
    const { setChats } = store((state) => state);
    const { chats } = store((state) => state.data);
    const [isValid, setIsValid] = useState(true);
    const inputRef = useRef(null);
    //
    const handleGroup = (userToAdd) => {
        if (selectedUsers.includes(userToAdd)) {
            return toast({
                title: 'User already added',
                status: 'warning',
                duration: 1000,
                isClosable: true,
                position: 'top',
            });
        }

        setSelectedUsers([...selectedUsers, userToAdd]);
    };

    const handleDelete = (delUser) => {
        setSelectedUsers(selectedUsers.filter((sel) => sel._id !== delUser._id));
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
            setLoading(false);
        } catch (error) {
            toast({
                title: 'Error Occurred!',
                description: 'Failed to Load the Search Results',
                status: 'error',
                duration: 3000,
                isClosable: true,
                position: 'bottom-left',
            });
            setLoading(false);
        }
    };
    const handleSubmit = async () => {
        if (groupChatName.length < 6) {
            setIsValid(false);
            inputRef.current.focus();
            return toast({
                title: 'Group Chat Name must be least 6 characters long',
                status: 'warning',
                duration: 2000,
                isClosable: true,
                position: 'top',
            });
        }
        if (selectedUsers < 2) {
            return toast({
                title: 'More than 2 users are required to form a group chat',
                status: 'warning',
                duration: 2000,
                isClosable: true,
                position: 'top',
            });
        }

        try {
            const { data } = await axiosService.post(`chat/group_create`, {
                nameGroup: groupChatName,
                users: selectedUsers.map((u) => u._id),
            });
            console.log({ data });
            const { createdAt } = data;
            if (createdAt) {
                setChats([data, ...chats]);
            }
            onClose();
            setGroupChatName('');
            setSelectedUsers([]);
            return toast({
                title: 'New Group Chat Created!',
                status: 'success',
                duration: 2000,
                isClosable: true,
                position: 'bottom',
            });
        } catch (error) {
            console.log({ error });
            toast({
                title: 'Failed to Create the Chat!',
                description: error.response.data,
                status: 'error',
                duration: 5000,
                isClosable: true,
                position: 'bottom',
            });
        }
    };
    useEffect(() => {
        if (!debounceValue?.trim()) {
            setSearchResult([]);
            return;
        }
        handleSearch();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [debounceValue, chats]);
    //
    return (
        <>
            <Box onClick={onOpen}>{children}</Box>

            <Modal onClose={onClose} isOpen={isOpen}>
                <ModalOverlay />
                <ModalContent>
                    <ModalHeader
                        fontSize="35px"
                        fontFamily="Work sans"
                        d="flex"
                        justifyContent="center"
                    >
                        Create Group Chat
                    </ModalHeader>
                    <ModalCloseButton />
                    <ModalBody d="flex" flexDir="column" alignItems="center">
                        <FormControl>
                            <Input
                                placeholder="Chat Name"
                                mb={3}
                                onChange={(e) => setGroupChatName(e.target.value)}
                                autoFocus
                                fontSize={'xl'}
                                focusBorderColor={isValid ? '' : 'pink.400'}
                                ref={inputRef}
                            />
                        </FormControl>
                        <FormControl>
                            <Input
                                placeholder="Add Users eg: John, Pixyish, Jane"
                                mb={1}
                                onChange={(e) => setSearch(e.target.value)}
                                fontSize={'xl'}
                            />
                        </FormControl>
                        <Box w="100%" d="flex" flexWrap="wrap">
                            <UserBadgeList users={selectedUsers} handleFunction={handleDelete} />
                        </Box>
                        {loading ? (
                            // <ChatLoading />
                            <div>Loading...</div>
                        ) : (
                            <UserList users={searchResult} handleFunc={handleGroup} />
                        )}
                    </ModalBody>
                    <ModalFooter>
                        <Button onClick={handleSubmit} colorScheme="blue">
                            Create Chat
                        </Button>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </>
    );
};

export default GroupChatModal;
