import { useState } from 'react';
import axios from 'axios';
import {
    VStack,
    FormControl,
    FormLabel,
    Input,
    InputGroup,
    InputRightElement,
    Button,
    Avatar,
    useToast,
    PinInput,
    PinInputField,
    HStack,
} from '@chakra-ui/react';
import OtpInput from 'react-otp-input';

import Upload from '../../../public/upload';
import { userValidate } from '../../helpers/validate';
import axiosService from '../../services/Axios';

//
const SignUp = () => {
    const [name, setName] = useState();
    const [otp, setOtp] = useState('');
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [confirmPassword, setConfirmPassword] = useState();
    const [picture, setPicture] = useState();
    const [show, setShow] = useState(false);
    const [show1, setShow1] = useState(false);
    const [loading, setLoading] = useState(false);
    const [isOTP, setIsOTP] = useState(false);
    const toast = useToast();
    //
    const handleClick = () => setShow(!show);
    const handleClick1 = () => setShow1(!show1);
    const postPicture = async (pic) => {
        //
        setLoading(true);
        //
        if (pic.type === 'image/jpeg' || pic.type === 'image/png' || pic.type === 'image/jpg') {
            const formData = new FormData();
            formData.append('file', pic);
            formData.append('upload_preset', 'ChatAPP');

            try {
                const res = await (
                    await axios.post(
                        'https://api.cloudinary.com/v1_1/dgiozc0lj/image/upload',
                        formData,
                    )
                ).data;
                // console.log(res);
                setPicture(`${res.url}`);
                setLoading(false);
            } catch (error) {
                setLoading(false);
                console.log(error);
            }
        } else {
            toast({
                title: 'Please select an image',
                status: 'warning',
                duration: 3000,
                position: 'top',
            });
        }
    };
    const handleChangePassword = (e) => {
        password === '' && setShow(false);
        setPassword(e.target.value);
    };
    const handleChangePassword1 = (e) => {
        password === '' && setShow1(false);
        setConfirmPassword(e.target.value);
    };
    const submitHandler = async () => {
        setLoading(true);
        if (!name || !email || !password) {
            setLoading(false);
            return toast({
                title: 'Please Fill all the fields',
                status: 'warning',
                duration: 3000,
                position: 'top',
            });
        } else if (password !== confirmPassword) {
            setLoading(false);
            return toast({
                title: 'Password Do not match',
                status: 'warning',
                duration: 3000,
                position: 'top',
            });
        }
        try {
            const { error } = userValidate({ name, email, password, picture });
            if (error) {
                setLoading(false);
                return toast({
                    title: error.details[0].message,
                    status: 'error',
                    duration: 3000,
                    position: 'top',
                });
            }
            const data = await (
                await axiosService.post('auth/register', {
                    name,
                    email,
                    password,
                    picture,
                })
            ).data;

            console.table({ data });

            if (data.code === 200) {
                setLoading(false);
                setIsOTP(true);
                toast({
                    title: 'Your information is stored successfully, Let confirm it by OTP',
                    status: 'success',
                    duration: 3000,
                    position: 'top',
                });
            } else if (data.message) {
                setLoading(false);
                return toast({
                    title: data.message,
                    status: 'warning',
                    duration: 3000,
                    position: 'top',
                });
            }
        } catch (error) {
            console.log({ error });
            toast({
                title: 'Error in server',
                status: 'error',
                duration: 3000,
                position: 'top',
            });
            setLoading(false);
        }
    };
    async function confirmOtp() {
        setLoading(true);
        const { code, message } = await (
            await axiosService.post('auth/verifyOTP', {
                email,
                otp,
            })
        ).data;
        if (code === 401) {
            toast({
                title: message,
                status: 'error',
                duration: 3000,
                position: 'top',
            });
            setLoading(false);
        } else if (code === 200) {
            setLoading(false);
            setIsOTP(false);
            setPicture();
            toast({
                title: 'Register is Successful',
                status: 'success',
                duration: 5000,
                position: 'top',
            });
        }
    }

    //
    return (
        <>
            {isOTP ? (
                <>
                    <OtpInput
                        value={otp}
                        onChange={setOtp}
                        numInputs={6}
                        containerStyle={{
                            display: 'flex',
                            justifyContent: 'center',
                            alignItems: 'center',
                        }}
                        inputStyle={{
                            width: '40px',
                            border: 'none',
                            borderBottom: '3px solid #41C9E2',
                            margin: '0 10px',
                            textAlign: 'center',
                            fontSize: '36px',
                            backgroundColor: '#6AD4DD',
                        }}
                        renderSeparator={<span>-</span>}
                        renderInput={(props) => <input {...props} />}
                    />
                    <Button
                        width={'100%'}
                        style={{ marginTop: '15px' }}
                        onClick={confirmOtp}
                        isLoading={loading}
                    >
                        Confirm
                    </Button>
                </>
            ) : (
                <VStack spacing={'5px'}>
                    <FormControl id="name" isRequired>
                        <FormLabel>Name</FormLabel>
                        <Input
                            placeholder="Enter your name"
                            onChange={(e) => setName(e.target.value)}
                        />
                    </FormControl>
                    <FormControl id="email-SignUp" isRequired>
                        <FormLabel>Email</FormLabel>
                        <Input
                            placeholder="Enter your email"
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </FormControl>
                    <FormControl id="password-SignUp" isRequired>
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
                    <FormControl id="confirm-password" isRequired>
                        <FormLabel>Confirm Password</FormLabel>
                        <InputGroup>
                            <Input
                                type={show1 ? 'text' : 'password'}
                                placeholder="Enter your Confirm Password"
                                onChange={(e) => {
                                    handleChangePassword1(e);
                                }}
                            />
                            <InputRightElement width={'4.5rem'}>
                                {confirmPassword && (
                                    <Button h="1.75rem" size="sm" onClick={handleClick1}>
                                        {show1 ? 'Hide' : 'Show'}
                                    </Button>
                                )}
                            </InputRightElement>
                        </InputGroup>
                    </FormControl>
                    <FormControl>
                        <FormLabel
                            cursor={'pointer'}
                            display={'flex'}
                            alignItems={'center'}
                            gap={'10px'}
                        >
                            {picture ? (
                                <Avatar src={picture} ignoreFallback={true} />
                            ) : (
                                <Avatar background={'#47A992'}>
                                    <Upload />
                                </Avatar>
                            )}
                            Upload Image
                        </FormLabel>
                        <Input
                            type="file"
                            hidden
                            p={2}
                            accept="image/*"
                            onChange={(e) => postPicture(e.target.files[0])}
                        />
                    </FormControl>
                    <Button
                        width={'100%'}
                        style={{ marginTop: '15px' }}
                        onClick={submitHandler}
                        isLoading={loading}
                    >
                        Sign Up
                    </Button>
                </VStack>
            )}
        </>
    );
};

export default SignUp;
//id ở FormControl là duy nhất, nó hỗ trợ cho htmlFor & id input
//InputGroup Nhóm Input và components khác trên 1 input, InputRightElement components nằm ở right input
