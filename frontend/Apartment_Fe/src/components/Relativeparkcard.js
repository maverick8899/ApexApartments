import React, { useState, useEffect } from 'react';
import Apis, { authApi, endpoints } from "../configs/Apis";
import { Form, Button } from 'react-bootstrap';
import styled from 'styled-components';

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

const RelativeParkCard = () => {
  const [parkCards, setParkCards] = useState([]);

  const [formData, setFormData] = useState({
    description: '',
    // dateCreate: '',
    expiry: '',
    active: true,
    cost: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(123);
    try {
      await Apis.post(endpoints.relativeparkcard, formData);
    } catch (error) {
      console.error('Error creating RelativeParkCard:', error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await Apis.get(endpoints.relativeparkcard);
        setParkCards(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);

  return (
    <Container>
      <Card>
        <CardHeader>Create New Relative Park Card</CardHeader>
        <CardBody>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
              <FormLabel>Description</FormLabel>
              <FormControl
                type="text"
                name="description"
                value={formData.description}
                onChange={handleChange}
                placeholder="Enter description"
                required
              />
            </Form.Group>
            {/* <Form.Group className="mb-3">
              <FormLabel>Date Create</FormLabel>
              <FormControl
                type="date"
                name="dateCreate"
                value={formData.dateCreate}
                onChange={handleChange}
                required
              />
            </Form.Group> */}
            <Form.Group className="mb-3">
              <FormLabel>Expiry Date</FormLabel>
              <FormControl
                type="date"
                name="expiry"
                value={formData.expiry}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <FormLabel>Cost</FormLabel>
              <FormControl
                type="number"
                name="cost"
                value={formData.cost}
                onChange={handleChange}
                placeholder="Enter cost"
                required
              />
            </Form.Group>
            <SubmitButton variant="primary" type="submit">
              Create
            </SubmitButton>
          </Form>
        </CardBody>
      </Card>
    </Container>
  );
};

export default RelativeParkCard;
