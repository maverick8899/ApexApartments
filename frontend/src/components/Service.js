import React, { useState, useEffect, useContext } from 'react';
import Apis, { endpoints } from '../configs/Apis';
import { Button, Form, Card, Container, Row, Col } from 'react-bootstrap';
import UserContext from '../contexts/UserContext';

const Service = () => {
    const [serviceDetail, setServiceDetail] = useState([]);
    const [services, setServices] = useState([]);
    const [formData, setFormData] = useState({
        serviceId: '',
        active: true,
    });
    const [error, setError] = useState(null);

    const [user] = useContext(UserContext);

    const fetchServiceDetail = async () => {
        try {
            if (user) {
                const response = await Apis.get(endpoints.service_detail(user.id)); // Sử dụng user.id thay cho giá trị gán cứng
                setServiceDetail(response.data);
            }
        } catch (error) {
            console.error('Error fetching Service:', error);
        }
    };

    useEffect(() => {
        fetchServiceDetail();
    }, [user]);

    const fetchServices = async () => {
        try {
            const response = await Apis.get(endpoints.service);
            setServices(response.data);
        } catch (error) {
            console.error('Error fetching Services:', error);
        }
    };
    useEffect(() => {
        fetchServices();
    }, []);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!formData.serviceId) {
            setError('Please fill in all fields.');
            return;
        }

        console.log('Endpoint URL:', endpoints.add_use_service);
        console.log('Payload:', formData);

        try {
            const response = await Apis.post(endpoints.add_use_service, {
                ...formData,
                customerId: user.id, // Sử dụng user.id cho customerId
                serviceId: parseInt(formData.serviceId),
            });
            fetchServiceDetail()
            console.log('Service created successfully:', response.data);
            setError(null);
        } catch (error) {
            console.error('Error creating Service:', error);
            setError('Failed to create service. Please try again.');
        }
    };

    const handleDelete = async (serviceId) => {
        try {
            if (user) {
                const useServiceResponse = await Apis.get(
                    endpoints.use_service_by_customer_and_service(user.id, serviceId), // Sử dụng user.id thay cho giá trị gán cứng
                );
                const useService = useServiceResponse.data;
                if (useService) {
                    await Apis.delete(endpoints.use_service(useService.id));
                    console.log('Service deleted successfully');
                    // Reload the service detail after deleting a service
                    fetchServiceDetail();
                } else {
                    console.error('UseService not found for the given customerId and serviceId');
                }
            }
        } catch (error) {
            console.error('Error deleting Service:', error);
        }
    };

    return (
        <Container className="mt-5">
            <h1 className="mb-4 text-center">Service</h1>
            <Row className="mb-4">
                {serviceDetail.map((s) => (
                    <Col md={6} lg={4} key={s.id} className="mb-3">
                        <Card>
                            <Card.Body>
                                <Card.Title>{s.name}</Card.Title>
                                <Card.Text>{s.description}</Card.Text>
                                <Button variant="danger" onClick={() => handleDelete(s.id)}>
                                    Delete
                                </Button>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
            <Card className="p-4">
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="formServiceSelect" className="mt-3">
                        <Form.Label>Select a Service</Form.Label>
                        <Form.Control
                            as="select"
                            name="serviceId"
                            value={formData.serviceId}
                            onChange={handleChange}
                        >
                            <option value="">Choose...</option>
                            {services.map((service) => (
                                <option key={service.id} value={service.id}>
                                    {service.name}
                                </option>
                            ))}
                        </Form.Control>
                    </Form.Group>
                    {error && <div className="text-danger mt-3">{error}</div>}
                    <Button variant="primary" type="submit" className="mt-3">
                        Submit
                    </Button>
                </Form>
            </Card>
        </Container>
    );
};

export default Service;
