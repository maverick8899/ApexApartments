import axios from 'axios';
import Cookies from 'js-cookie';

const SERVER_CONTEXT = '/apartment_manager';
const SERVER = 'http://localhost:8080'; //? dev
// const SERVER = '/'; //? prod

export const endpoints = {
    customer: `${SERVER_CONTEXT}/api/customers/`,
    relativeparkcard:(customerId)=> `${SERVER_CONTEXT}/api/relativeparkcard/${customerId}`,
    merchandisecabinet: (customerId, page) =>
        `${SERVER_CONTEXT}/api/merchandisecabinet/${customerId}?page=${page}`,
    service: `${SERVER_CONTEXT}/api/service/`,
    customer_service: `${SERVER_CONTEXT}/api/service/customers`,
    add_use_service: `${SERVER_CONTEXT}/api/use-services`,
    add_feedback: `${SERVER_CONTEXT}/api/addFeedback`,
    upload_avatar: `${SERVER_CONTEXT}/api/upload-avatar`,
    check_avatar: `${SERVER_CONTEXT}/api/check-avatar`,
    payment: (amount, receiptId, month) =>
        `${SERVER_CONTEXT}/api/vnpay/create_payment?amount=${amount}&receiptId=${receiptId}&month=${month}`,

    survey_questions: `${SERVER_CONTEXT}/api/survey/questions`,
    survey_answer: `${SERVER_CONTEXT}/api/survey/answers`,

    service_detail: (customerId) => `${SERVER_CONTEXT}/api/service/customers/${customerId}`,
    use_service_by_customer_and_service: (customerId, serviceId) =>
        `${SERVER_CONTEXT}/api/use-services?customerId=${customerId}&serviceId=${serviceId}`,
    use_service: (id) => `${SERVER_CONTEXT}/api/use_service/${id}`,
    receipt_paid: (customerId, page) =>
        `${SERVER_CONTEXT}/api/receipts?type=1&isPay=1&kw=${customerId}&page=${page}`,
    receipt_unpaid: (customerId, page) =>
        `${SERVER_CONTEXT}/api/receipts?type=1&isPay=2&kw=${customerId}&page=${page}`,
    receipt: (customerId, page) =>
        `${SERVER_CONTEXT}/api/receipts?type=1&kw=${customerId}&page=${page}`,
    login: `${SERVER_CONTEXT}/api/login`,
    'get-chat-users': `${SERVER_CONTEXT}/api/users/get-chat-users`,
    receipt_payment: (receiptID) => `${SERVER_CONTEXT}/receipt/payment?receiptID=${receiptID}`,
};
export const authApi = axios.create({
    baseURL: SERVER,
});

authApi.interceptors.request.use(
    (config) => {
        const token = Cookies.get('token');
        if (token) {
            config.headers['authorization'] = token;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    },
);

export default axios.create({
    get: (url, config = {}) => axios.get(url, { ...config, maxRedirects: 0 }),
    baseURL: SERVER,
});
