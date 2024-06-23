import axios from "axios";


const SERVER_CONTEXT = "/apartment_manager";
const SERVER = "http://localhost:8080";  //@ dev
// const SERVER = "http://backend:8080";  //@ prod



export const endpoints = {
    "customer": `${SERVER_CONTEXT}/api/customers/`,
    "relativeparkcard": `${SERVER_CONTEXT}/api/relativeparkcard/`,
    "merchandisecabinet":(customerId)=> `${SERVER_CONTEXT}/api/merchandisecabinet/${customerId}`,
    "service": `${SERVER_CONTEXT}/api/service/`,
    "customer_service": `${SERVER_CONTEXT}/api/service/customers`,
    "add_use_service": `${SERVER_CONTEXT}/api/use-services`,
    "add_feedback": `${SERVER_CONTEXT}/api/addFeedback`,

    'service_detail': (customerId) => `${SERVER_CONTEXT}/api/service/customers/${customerId}`,
    'use_service_by_customer_and_service': (customerId, serviceId) => 
        `${SERVER_CONTEXT}/api/use-services?customerId=${customerId}&serviceId=${serviceId}`,
    'use_service': (id) => `${SERVER_CONTEXT}/api/use_service/${id}`,
    'receipt':(customerId)=> `${SERVER_CONTEXT}/api/receipts?type=1&kw=${customerId}`,
    'receipt_paid':(customerId)=> `${SERVER_CONTEXT}/api/receipts?type=1&isPay=1&kw=${customerId}`,
    'receipt_unpaid':(customerId)=> `${SERVER_CONTEXT}/api/receipts?type=1&isPay=2&kw=${customerId}`,

    // http://localhost:8080/apartment_manager/api/vnpay/create_payment?amount=300000
    'payment': (amount,receiptId,month) => `${SERVER_CONTEXT}/api/vnpay/create_payment?amount=${amount}&receiptId=${receiptId}&month=${month}`,
    
    "survey_questions": `${SERVER_CONTEXT}/api/survey/questions`,
    "survey_answer": `${SERVER_CONTEXT}/api/survey/answers`,

}
export const authApi = () => {
    return axios.create({
        baseURL: SERVER
      
    })
}

export default axios.create({
    get: (url, config = {}) => axios.get(url, { ...config, maxRedirects: 0 }),
    baseURL: SERVER
})