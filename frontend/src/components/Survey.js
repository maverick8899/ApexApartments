import React, { useState, useEffect } from 'react';
import Apis, { authApi, endpoints } from '../configs/Apis';
import { Form, Button } from 'react-bootstrap';
import styled from 'styled-components';
import { inputValidate } from '../helper/inputValidate';
import { Bounce, ToastContainer, toast } from 'react-toastify';

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

const Survey = () => {
    const [survey, setSurvey] = useState([]);
    const [answers, setAnswers] = useState([]);
    const [personalOpinion, setPersonalOpinion] = useState('');
    const notify = (msg) =>
        toast.warn(`${msg}`, {
            position: 'top-center',
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: 'light',
            transition: Bounce,
        });

    const handleInputAnswers = (e, id) => {
        const { value } = e.target;
        setAnswers((prevAnswers) => {
            const updatedAnswers = prevAnswers.filter((answer) => answer.question_id !== id);
            return [
                ...updatedAnswers,
                {
                    answer: value,
                    question_id: id,
                },
            ];
        });
        console.log({
            survey_id: 1,
            customer_id: 1,
            personal_opinion: personalOpinion,
            answers,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const { error } = inputValidate(personalOpinion);
        if (error) {
            notify(error.details[0].message);
            return;
        }
        try {
            const res = await Apis.post(endpoints.survey_answer, {
                survey_id: survey[1]?.survey_id,
                customer_id: 1,
                personal_opinion: personalOpinion,
                answers,
            }); 
            toast.success(`Gửi khảo sát thành công`, {
                position: 'top-center',
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: 'light',
                transition: Bounce,
            });
            console.log({ res });
        } catch (error) {
            console.error('Error creating Survey:', error);
        }
    };

    useEffect(() => {
        (async () => {
            try {
                const response = await Apis.get(endpoints.survey_questions);
                setSurvey(response.data);
                console.log(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        })();
    }, []);

    return (
        <>
            <h1 className="text-center">
                Survey: {survey[2]?.date && survey[2].date.split(' ')[0]}
            </h1>
            <Container className="d-flex flex-column align-items-stretch">
                {survey[0]?.questions &&
                    survey[0].questions.map((s, index) => (
                        <Form.Group className="mb-3" key={index}>
                            <Form.Label>{s.question_content}</Form.Label>
                            <Form.Select
                                name="answer"
                                id={s.question_id}
                                onChange={(e) => handleInputAnswers(e, s.question_id)}
                            >
                                <option value="0" defaultChecked>
                                    Đánh giá
                                </option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </Form.Select>
                        </Form.Group>
                    ))}
                <Form.Group className="mb-3">
                    <FormLabel>Ý kiến cá nhân</FormLabel>
                    <FormControl
                        type="text"
                        name="personal_opinion"
                        value={personalOpinion}
                        onChange={(e) => setPersonalOpinion(e.target.value)}
                        placeholder="Enter Personal Opinion"
                        required
                    />
                </Form.Group>
                <SubmitButton variant="primary" type="submit" onClick={handleSubmit}>
                    Hoàn thành khảo sát
                </SubmitButton>
            </Container>
            <ToastContainer />
        </>
    );
};

export default Survey;
