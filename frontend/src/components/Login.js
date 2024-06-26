import { useContext, useState } from 'react';
import { Button, Card, Col, Container, Form, Row } from 'react-bootstrap';
import { Link, Navigate, useSearchParams } from 'react-router-dom';
import Apis, { endpoints } from '../configs/Apis';
import UserContext from '../contexts/UserContext';
import Cookies from 'js-cookie';
import { FirebaseAuth } from '../configs/Firebase';
import { signInWithCustomToken } from 'firebase/auth';

const Login = () => {
    const [user, dispatch] = useContext(UserContext);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [q] = useSearchParams();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await Apis.post(endpoints['login'], { username, password });
            // await signInWithCustomToken(FirebaseAuth, res.data.firebaseToken);

            dispatch({
                type: 'login',
                payload: res.data,
            });
            Cookies.remove('token');
            Cookies.remove('user');

            Cookies.set('token', res.data.token);
            Cookies.set('user', JSON.stringify(res.data));
        } catch (e) {
            console.error(e);
        }
    };

    if (user) {
        let next = q.get('next') || '/';
        return <Navigate to={next} />;
    }

    return (
        <Container className="mt-5">
            <Row className="justify-content-md-center">
                <Col md={4}>
                    <Card>
                        <Card.Body>
                            <Card.Title className="text-center">Đăng nhập</Card.Title>
                            <Form onSubmit={handleSubmit}>
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>Tên đăng nhập</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Tên đăng nhập"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                        required
                                    />
                                </Form.Group>

                                <Form.Group controlId="formBasicPassword">
                                    <Form.Label>Mật khẩu</Form.Label>
                                    <Form.Control
                                        type="password"
                                        placeholder="Mật khẩu"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        required
                                    />
                                </Form.Group>

                                <Button variant="primary" type="submit" className="w-100 mt-3">
                                    Đăng nhập
                                </Button>
                            </Form>
                        </Card.Body>
                    </Card>
                    <div className="mt-2 text-center"></div>
                </Col>
            </Row>
        </Container>
    );
};

export default Login;
