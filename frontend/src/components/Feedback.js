import React, { useState, useEffect } from 'react';
import Apis, { authApi, endpoints } from '../configs/Apis';
import { Form, Button } from 'react-bootstrap';
import styled from 'styled-components';
import { inputValidate } from '../helper/inputValidate'; 
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

const Feedback = () => {
    const [fetch, setFetch] = useState(false);
    const [content, setContent] = useState([]);
    const [title, setTitle] = useState([]);

    const handleSubmit = async (e) => {
        e.preventDefault(); 
        try {
            await Apis.post(endpoints.add_feedback, {
                feedback_content: content,
                feedback_title: title,
                customer_id: 1,
            });
            setContent('');
            setTitle('');
        } catch (error) {
            console.error('Error creating Feedback:', error);
        }
    };
    const handleInputContent = (e) => {
        setContent(e.target.value);
        const { error } = inputValidate(content);
        // if (error) {
        //     return toast({
        //         title: error.details[0].message,
        //         status: 'error',
        //         duration: 3000,
        //         position: 'top',
        //     });
        // }
    };
    const handleInputTitle = (e) => {
        setTitle(e.target.value);
        const { error } = inputValidate(title);
        // if (error) {
        //     return toast({
        //         title: error.details[0].message,
        //         status: 'error',
        //         duration: 3000,
        //         position: 'top',
        //     });
        // }
    };
    // useEffect(() => {
    // const fetchData = async () => {
    //     try {
    //         const response = await Apis.get(endpoints.relativeparkcard);
    //         setParkCards(response.data);
    //     } catch (error) {
    //         console.error('Error fetching data:', error);
    //     }
    // };
    // fetchData();
    // }, [fetch]);

    return (
        <Container>
            <Card>
                <CardHeader>Create Feedback</CardHeader>
                <CardBody>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-3">
                            <FormLabel>Title</FormLabel>
                            <FormControl
                                type="text"
                                name="title"
                                value={title}
                                onChange={handleInputTitle}
                                placeholder="Enter description"
                                required
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <FormLabel>Content</FormLabel>
                            <FormControl
                                type="text"
                                name="content"
                                value={content}
                                placeholder="Enter content feedback"
                                onChange={handleInputContent}
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

export default Feedback;
