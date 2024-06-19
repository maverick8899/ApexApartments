import axios from "axios";

const SERVER_CONTEXT = "/apartment_manager";
const SERVER = "http://localhost:8080";



export const endpoints = {
    "customer": `${SERVER_CONTEXT}/api/customers/`,
    "relativeparkcard": `${SERVER_CONTEXT}/api/relativeparkcard/`,
    "merchandisecabinet":(customerId)=> `${SERVER_CONTEXT}/api/merchandisecabinet/${customerId}`,
    "service": `${SERVER_CONTEXT}/api/service/`,
    "add_use_service": `${SERVER_CONTEXT}/api/use-services`,

    'service_detail': (customerId) => `${SERVER_CONTEXT}/api/service/customers/${customerId}`,
    'use_service_by_customer_and_service': (customerId, serviceId) => 
        `${SERVER_CONTEXT}/api/use-services?customerId=${customerId}&serviceId=${serviceId}`,
    'use_service': (id) => `${SERVER_CONTEXT}/api/use_service/${id}`,
    'receipt_paid':(customerId)=> `${SERVER_CONTEXT}/api/receipts?type=1&isPay=2&kw=${customerId}`,
    'receipt_unpaid':(customerId)=> `${SERVER_CONTEXT}/api/receipts?type=1&isPay=1&kw=${customerId}`,
    'payment': (amount,receiptId,month) => `${SERVER_CONTEXT}/api/vnpay/create_payment?amount=${amount}&receiptId=${receiptId}&month=${month}`
    

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