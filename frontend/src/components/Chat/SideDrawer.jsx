import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell, faSearch, faTicket, faWarning } from '@fortawesome/free-solid-svg-icons';
import { Box, Text } from '@chakra-ui/layout';
import { Tooltip } from '@chakra-ui/tooltip';
import { Button } from '@chakra-ui/button';
import { Menu, MenuButton, MenuDivider, MenuItem, MenuList } from '@chakra-ui/menu';
import { BellIcon, ChevronDownIcon } from '@chakra-ui/icons';
import { Avatar } from '@chakra-ui/avatar';
import { useNavigate } from 'react-router-dom';
import Profile from './Profile';
import axios from 'axios';
import axiosService from '../../services/Axios';
import { useCookies } from 'react-cookie';
import { useEffect, useState } from 'react';
import NotificationBadge from 'react-notification-badge';
import {
    Drawer,
    DrawerBody,
    DrawerFooter,
    DrawerHeader,
    DrawerOverlay,
    DrawerContent,
    DrawerCloseButton,
    useDisclosure,
    Input,
    useToast,
    Spinner,
} from '@chakra-ui/react';
import ChatLoading from './ChatLoading';
import UserList from './User/UserList';
import store from '../../store';
import useDebounce from '../../hook/useDebounce';
import { getSender } from '../../services/Chat.service';
import { Effect } from 'react-notification-badge';

//
const SideDrawer = () => {
    //
    // eslint-disable-next-line no-unused-vars
    const [cookies, setCookie, removeCookie] = useCookies(['accessToken', 'refreshToken']);
    const [search, setSearch] = useState('');
    const [searchResult, setSearchResult] = useState([]);
    const [loading, setLoading] = useState(false);
    const [loadingChat, setLoadingChat] = useState(false);
    const navigate = useNavigate();
    const { isOpen, onOpen, onClose } = useDisclosure();
    const { user, notification, selectedChat, chats } = store((state) => state.data);
    const { setNotification } = store((state) => state);
    const { setChats, setSelectedChat } = store((state) => state);
    const debounceValue = useDebounce(search, 500);
    const toast = useToast();
    /** Handle */
    const handleLogout = async () => {
        console.log(cookies.refreshToken);
        try {
            const res = (
                await axios.post('http://localhost:8080/api/v1/auth/logout', {
                    refreshToken: cookies.refreshToken,
                })
            ).data;
            removeCookie('accessToken');
            removeCookie('refreshToken');
            localStorage.removeItem('userInfo');
            console.log('res_logout', res);
            return navigate('/');
        } catch (error) {
            console.log({ error });
        }
    };
    const handleSearch = async () => {
        try {
            setLoading(true);
            const data = (
                await axiosService.get(`chat/search`, {
                    params: {
                        keyword: debounceValue,
                    },
                })
            ).data;
            setSearchResult(data);
            console.log({ data });
            setLoading(false);
        } catch (error) {
            toast({
                title: 'Error Occurred!',
                description: 'Failed to Load the Search Results',
                status: 'error',
                duration: 5000,
                isClosable: true,
                position: 'bottom-left',
            });
            setLoading(false);
        }
    };
    //
    const handleFunc = async (userId = user._id) => {
        setLoadingChat(true);
        try {
            const { data } = await axiosService.post(`chat`, { userChatId: userId });
            console.log(data);

            //nếu click vào card chưa cso trong lis chat thì push
            if (!chats.find((c) => c._id === data._id)) {
                setChats(chats);
            }

            setSelectedChat(data);

            onClose();
            setLoadingChat(false);
        } catch (error) {
            toast({
                title: 'Error fetching the chat',
                description: error.message,
                status: 'error',
                duration: 5000,
                isClosable: true,
                position: 'bottom-left',
            });
            setLoadingChat(false);
        }
    };
    useEffect(() => {
        if (!debounceValue.trim()) {
            setSearchResult([]);
            return;
        }
        handleSearch();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [debounceValue]);
    /** JSX */
    return (
        <>
            <Box
                display={'flex'}
                justifyContent={'space-between'}
                alignItems="center"
                bg="white"
                w="100%"
                p="5px 10px 5px 10px"
                borderWidth="5px"
            >
                <Tooltip label={'Search User'}>
                    <Button variant={'ghost'} onClick={onOpen}>
                        <FontAwesomeIcon icon={faSearch} />
                        <Text d={{ base: 'none', md: 'flex' }} px={4}>
                            Search User
                        </Text>
                    </Button>
                </Tooltip>
                <Text fontSize={'2xl'}>Chat App</Text>
                <div style={{ display: 'flex', justifyContent: 'center' }}>
                    <Menu>
                        <MenuButton p={1} me={2} fontSize={'20px'}>
                            <NotificationBadge count={notification.length} effect={Effect.SCALE} />
                            <FontAwesomeIcon icon={faBell} />
                        </MenuButton>
                        <MenuList pl={2} height={'200px'} overflowY={'auto'}>
                            {!notification.length
                                ? 'No New Messages'
                                : notification?.map((notify) => (
                                      <MenuItem
                                          key={notify._id}
                                          onClick={() => {
                                              setSelectedChat(notify.chat);
                                              setNotification(
                                                  notification.filter((n) => n !== notify),
                                              );
                                          }}
                                      >
                                          {notify.chat.isGroupChat
                                              ? `New Message in ${notify.chat.chatName}`
                                              : `New Message from ${getSender(
                                                    user,
                                                    notify.chat.users,
                                                )}`}
                                      </MenuItem>
                                  ))}
                        </MenuList>
                    </Menu>
                    <Menu>
                        <MenuButton as={Button} rightIcon={<ChevronDownIcon />}>
                            <BellIcon fontSize={1} m={1} />
                            <Avatar
                                size={'sm'}
                                cursor={'pointer'}
                                name={user.name}
                                src={user.picture}
                            />
                        </MenuButton>
                        <MenuList>
                            <Profile user={user}>
                                <MenuItem>My Profile</MenuItem>
                            </Profile>
                            <MenuDivider />
                            <MenuItem onClick={handleLogout}>Logout</MenuItem>
                        </MenuList>
                    </Menu>
                </div>
            </Box>
            <Drawer isOpen={isOpen} placement="left" onClose={onClose}>
                <DrawerOverlay />
                <DrawerContent>
                    <DrawerCloseButton />
                    <DrawerHeader display={'flex'}>Search user</DrawerHeader>

                    <DrawerBody>
                        <Box display={'flex'} gap={2} pb={2}>
                            <Input
                                placeholder="Type here..."
                                autoFocus
                                onChange={(e) => setSearch(e.target.value)}
                            />
                            <Button colorScheme="blue" onClick={handleSearch} isLoading={loading}>
                                Go
                            </Button>
                        </Box>
                        {loading ? (
                            <ChatLoading />
                        ) : (
                            <UserList users={searchResult} handleFunc={handleFunc} />
                        )}
                        {loadingChat && <Spinner marginLeft={'90%'} />}
                    </DrawerBody>

                    <DrawerFooter>
                        <Button variant="outline" mr={3} onClick={onClose}>
                            Cancel
                        </Button>
                    </DrawerFooter>
                </DrawerContent>
            </Drawer>
        </>
    );
};

export default SideDrawer;
