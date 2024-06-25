import { useContext, useState } from 'react';
import { Button, Card, Col, Container, Form, Row, Alert } from 'react-bootstrap';
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
  const [error, setError] = useState('');
  const [q] = useSearchParams();
  const [showUploadAvatar, setShowUploadAvatar] = useState(false);
  const [avatar, setAvatar] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const avatarRes = await Apis.get(endpoints['check_avatar'], { params: { username } });
      if (!avatarRes.data) {
        setShowUploadAvatar(true);
        return;
      }
      // Nếu đã có avatar thì tiếp tục đăng nhập
      await handleLogin();
    } catch(e) {
      console.error(e);
      setError("Invalid username or password");
    }
  };

  const handleLogin = async () => {
    try {
      const res = await Apis.post(endpoints['login'], { username, password });
      await signInWithCustomToken(FirebaseAuth, res.data.firebaseToken);

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
      setError("Invalid username or password");
    }
  };

  const handleUploadAvatar = async () => {
    try {
      const formData = new FormData();
      formData.append('file', avatar);
      formData.append('username', username);

      await Apis.post(endpoints['upload_avatar'], formData);
      await handleLogin();
    } catch (e) {
      console.error(e);
      setError("Failed to upload avatar");
    }
  };

  if (user) {
    let next = q.get('next') || '/';
    return <Navigate to={next} />;
  }

  return (
    <Container className='mt-5'>
      <Row className='justify-content-md-center'>
        <Col md={4}>
          <Card>
            <Card.Body>
              <Card.Title className='text-center'>Đăng nhập</Card.Title>
              {error && <Alert variant='danger'>{error}</Alert>}
              {!showUploadAvatar ? (
                <Form onSubmit={handleSubmit}>
                  <Form.Group controlId='formBasicEmail'>
                    <Form.Label>Tên đăng nhập</Form.Label>
                    <Form.Control
                      type='text'
                      placeholder='Tên đăng nhập'
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Form.Group controlId='formBasicPassword'>
                    <Form.Label>Mật khẩu</Form.Label>
                    <Form.Control
                      type='password'
                      placeholder='Mật khẩu'
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Button variant='primary' type='submit' className='w-100 mt-3'>
                    Đăng nhập
                  </Button>
                </Form>
              ) : (
                <Form>
                  <Form.Group controlId='formAvatar'>
                    <Form.Label>Upload Avatar</Form.Label>
                    <Form.Control
                      type='file'
                      onChange={(e) => setAvatar(e.target.files[0])}
                      required
                    />
                  </Form.Group>
                  <Button variant='primary' className='w-100 mt-3' onClick={handleUploadAvatar}>
                    Upload Avatar
                  </Button>
                </Form>
              )}
            </Card.Body>
          </Card>
          <div className='mt-2 text-center'></div>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
