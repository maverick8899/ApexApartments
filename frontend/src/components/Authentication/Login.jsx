import { useState } from 'react';
import {
    VStack,
    FormControl,
    FormLabel,
    Input,
    InputGroup,
    InputRightElement,
    Button,
    useToast,
} from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';
import { userValidate } from '../../helpers/validate';
import { useCookies } from 'react-cookie';
import store from '../../store';
import axiosService from '../../services/Axios';

//
const Login = () => {
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [show, setShow] = useState(false);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const toast = useToast();
    const [cookies, setCookie, removeCookie] = useCookies(['accessToken', 'refreshToken']);
    const setUser = store((state) => state.setUser);
    //
    const handleClick = () => setShow(!show);
    const enterSubmit = (event) => {
        if (event.key === 'Enter') {
            submitHandler();
        }
    };
    const submitHandler = async () => {
        setLoading(true);
        if (!email || !password) {
            setLoading(false);
            return toast({
                title: 'Enter All fields,please',
                status: 'warning',
                duration: 3000,
                position: 'top',
            });
        }
        const { error } = userValidate({ email, password });
        if (error) {
            setLoading(false);
            return toast({
                title: error.details[0].message,
                status: 'error',
                duration: 3000,
                position: 'top',
            });
        }
        try {
            const data = await (
                await axiosService.post('auth/login', {
                    email,
                    password,
                })
            ).data;
            console.log({ data });

            setUser({
                _id: data.elements.user._id,
                email: data.elements.user.email,
                name: data.elements.user.name,
                picture: data.elements.user.picture,
            });

            if (data.message) {
                setLoading(false);
                return toast({
                    title: data.message,
                    status: 'warning',
                    duration: 3000,
                    position: 'top',
                });
            } else {
                setLoading(false);
                toast({
                    title: 'Login is Successful',
                    status: 'success',
                    duration: 1000,
                    position: 'top',
                });
                setCookie('accessToken', data.elements.accessToken, {
                    // domain: 'localhost', //? choose exact domain
                    sameSite: 'Strict',
                    // httpOnly: true,  You cannot get or set httpOnly cookies from the browser, only the server.
                    maxAge: 3600, //đơn vị s
                });
                setCookie('refreshToken', data.elements.refreshToken, {
                    // domain: 'localhost',
                    sameSite: 'Strict',
                    maxAge: 3_600,
                });
                navigate('/chat');
            }
        } catch (error) {
            console.log(error);
            toast({
                title: 'Error in server',
                status: 'error',
                duration: 3000,
                position: 'top',
            });
            setLoading(false);
        }
    };
    const handleChangePassword = (e) => {
        password === '' && setShow(false);
        setPassword(e.target.value);
    };
    //
    return (
        <VStack spacing={'5px'} onKeyDown={enterSubmit}>
            <FormControl id="email" isRequired>
                <FormLabel>Email</FormLabel>
                <Input placeholder="Enter your email" onChange={(e) => setEmail(e.target.value)} />
            </FormControl>
            <FormControl id="password" isRequired>
                <FormLabel>Password</FormLabel>
                <InputGroup>
                    <Input
                        type={show ? 'text' : 'password'}
                        placeholder="Enter your Password"
                        onChange={(e) => {
                            handleChangePassword(e);
                        }}
                    />
                    <InputRightElement width={'4.5rem'}>
                        {password && (
                            <Button h="1.75rem" size="sm" onClick={handleClick}>
                                {show ? 'Hide' : 'Show'}
                            </Button>
                        )}
                    </InputRightElement>
                </InputGroup>
            </FormControl>

            <Button
                width={'100%'}
                style={{ marginTop: '15px' }}
                onClick={submitHandler}
                isLoading={loading}
            >
                Login
            </Button>
        </VStack>
    );
};

export default Login;
//InputGroup Nhóm Input và components khác trên 1 input, InputRightElement components nằm ở right input
