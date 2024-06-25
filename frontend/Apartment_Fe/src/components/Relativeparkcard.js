import React, { useState, useEffect, useContext } from 'react';
import Apis, { authApi, endpoints } from "../configs/Apis";
import { Form, Button } from 'react-bootstrap';
import styled from 'styled-components';
import UserContext from '../contexts/UserContext';


const RelativeParkCard = () => {
  const [user] = useContext(UserContext); 
  const [parkCards, setParkCards] = useState([]);

  const [formData, setFormData] = useState({
    description: '',
    expiry: '',
    active: true,
    cost: '200.00'
  });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = { ...formData };
      await authApi.post(endpoints.relativeparkcard(user.id), formData);
      fetchData();
        } catch (error) {
      console.error('Error creating RelativeParkCard:', error);
    }
  };
  const fetchData = async () => {
    try {
      const response = await authApi.get(endpoints.relativeparkcard(user.id));
      setParkCards(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);
  return (
    <Container>
      <Card>
        <CardHeader>Tạo thẻ giữ xe mới</CardHeader>
        <CardBody>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
              <FormLabel>Mô tả</FormLabel>
              <FormControl
                type="text"
                name="description"
                value={formData.description}
                onChange={handleChange}
                placeholder="Enter description"
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <FormLabel>Hạn sử dụng</FormLabel>
              <FormControl
                type="date"
                name="expiry"
                value={formData.expiry}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <SubmitButton variant="primary" type="submit">
              Create
            </SubmitButton>
          </Form>
        </CardBody>
      </Card>

     <Card>
     <CardBody>
     <table className="receipt-table">
        <thead>
          <tr>
            <th>Mô tả</th>
            <th>Ngày tạo</th>
            <th>Hạn sử dụng</th>
          </tr>
        </thead>
        <tbody>
          {parkCards.map(parkCard => (
            <tr key={parkCard.id}>
              <td>{parkCard.description}</td>
              <td>{new Date(parkCard.dateCreate).toLocaleDateString()}</td>
              <td>{new Date(parkCard.expiry).toLocaleDateString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
      </CardBody>

     </Card>
    </Container>
  );
};

export default RelativeParkCard;

const Container = styled.div`
  margin-top: 20px;
  display: flex;
  justify-content: center;
`;

const Card = styled.div`
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  width: 100%;
  max-width: 500px;
`;

const CardHeader = styled.div`
  background-color: #007bff;
  color: white;
  text-align: center;
  font-weight: bold;
  padding: 10px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
`;

const CardBody = styled.div`
  padding: 20px;
`;

const FormLabel = styled(Form.Label)`
  font-weight: bold;
`;

const FormControl = styled(Form.Control)`
  border-radius: 5px;
`;

const SubmitButton = styled(Button)`
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
`;
