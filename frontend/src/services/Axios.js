import axios from 'axios';
import cookie from 'cookie';

const axiosInstance = axios.create({
    baseURL: '/api/v1/',
});

axiosInstance.interceptors.request.use(
    (config) => {
        const cookies = cookie.parse(document.cookie);
        // console.log('axios', cookies);

        config.headers['authorization'] = `Bearer ${cookies.accessToken}`; //* Thêm token vào header
        return config;
    },
    (error) => {
        return Promise.reject(error);
    },
);
export default axiosInstance;
