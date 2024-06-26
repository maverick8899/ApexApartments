import React, { useState, useEffect, useContext } from 'react';
import Apis, { endpoints } from '../../configs/Apis';
import './ReceiptList.css';
import MyPagination from '../MyPagination';
import UserContext from '../../contexts/UserContext';
import { useNavigate } from 'react-router-dom';

const ReceiptList = () => {
    const [receipt_paid, setReceiptsPaid] = useState([]);
    const [receipt_unpaid, setReceiptsUnPaid] = useState([]);
    const [user] = useContext(UserContext);
    const [page, setPage] = useState(1);
    const [paginate, setPaginate] = useState();
    const navigate = useNavigate();
    console.log({ paginate });
    useEffect(() => {
        const fetchReceipts = async () => {
            try {
                const response = await Apis.get(endpoints.receipt_paid(user.id, page));
                setReceiptsPaid(response.data[1]);
            } catch (error) {
                console.error('Error fetching the receipts:', error);
            }
        };

        fetchReceipts();
    }, [user.id, page]);

    useEffect(() => {
        (async () => {
            try {
                const response = await Apis.get(endpoints.receipt_unpaid(user.id, page));
                setReceiptsUnPaid(response.data[1]);
                console.log(response.data[0]);
            } catch (error) {
                console.error('Error fetching the receipts:', error);
            }
        })();
    }, [page]);

    // useEffect(() => {
    //     window.location.href = url;
    // }, [url]);

    useEffect(() => {
        const fetchReceipts = async () => {
            try {
                const response = await Apis.get(endpoints.receipt_unpaid(user.id, page));
                localStorage.setItem('paginate.receipt', JSON.stringify(response.data[0]));
                setPaginate(response.data[0]);
            } catch (error) {
                console.error('Error fetching the receipts:', error);
            }
        };

        fetchReceipts();
    }, [user.id, page]);

    const handlePayment = async (receiptId, amount, month) => {
        try {
            const response = await Apis.get(endpoints.payment(amount, receiptId, month));
            console.log('Payment response:', response);
            // await Apis.post(endpoints.receipt_payment(receiptId));
            const paymentUrl = response.data.url;
            if (paymentUrl) {
                // navigate(paymentUrl);
                window.location.href = paymentUrl;
            } else {
                console.error('Payment URL not found in the response');
            }
        } catch (error) {
            console.error('Error processing payment:', error);
        }
    };

    const handleViewDetails = (receiptId) => {
        console.log('Xem chi tiết hoá đơn:', receiptId);
    };

    return (
        <div>
            <h1>Receipt List</h1>
            {receipt_paid.length > 0 || receipt_unpaid.length > 0 ? (
                <table className="receipt-table">
                    <thead>
                        <tr>
                            <th>ID hóa đơn</th>
                            <th>Ngày</th>
                            <th>Tên khách hàng</th>
                            <th>số lần sử dụng</th>
                            <th>Tháng</th>
                            <th>Năm</th>
                            <th>Chi tiết biên nhận</th>
                            <th>Tổng cộng</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {receipt_paid.map((receipt) => (
                            <tr key={receipt.receiptId}>
                                <td>{receipt.receiptId}</td>
                                <td>{new Date(receipt.useServiceDate).toLocaleDateString()}</td>
                                <td>{receipt.customerName}</td>
                                <td>{receipt.receiptDetailQuantity}</td>
                                <td>{new Date(receipt.receiptDate).getMonth() + 1}</td>
                                <td>{new Date(receipt.receiptDate).getFullYear()}</td>
                                <td>{receipt.receiptDetailCost}</td>
                                <td>{receipt.receiptTotal}</td>
                                <td>
                                    <button
                                        className="button details"
                                        onClick={() => handleViewDetails(receipt.receiptId)}
                                    >
                                        Xem chi tiết
                                    </button>
                                </td>
                            </tr>
                        ))}
                        {receipt_unpaid.map((receipt) => (
                            <tr key={receipt.receiptId}>
                                <td>{receipt.receiptId}</td>
                                <td>{new Date(receipt.useServiceDate).toLocaleDateString()}</td>
                                <td>{receipt.customerName}</td>
                                <td>{receipt.receiptDetailQuantity}</td>
                                <td>{new Date(receipt.receiptDate).getMonth() + 1}</td>
                                <td>{new Date(receipt.receiptDate).getFullYear()}</td>
                                <td>{receipt.receiptDetailCost}</td>
                                <td>{receipt.receiptTotal}</td>
                                <td>
                                    <button
                                        className="button payment"
                                        onClick={() =>
                                            handlePayment(
                                                receipt.receiptId,
                                                receipt.receiptTotal,
                                                new Date(receipt.receiptDate).getMonth() + 1,
                                            )
                                        }
                                    >
                                        Thanh toán
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p>No receipts found</p>
            )}
            <div className="d-flex justify-content-center">
                <MyPagination setPage={setPage} />
            </div>
        </div>
    );
};

export default ReceiptList;
