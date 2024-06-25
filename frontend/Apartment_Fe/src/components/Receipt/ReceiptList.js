import React, { useState, useEffect, useContext } from 'react';
import Apis, { endpoints } from '../../configs/Apis';
import './ReceiptList.css'; 
import MyPagination from '../MyPagination';
import UserContext from '../../contexts/UserContext';

const ReceiptList = () => {
    const [receipt_paid, setReceiptsPaid] = useState([]);
    const [receipt_unpaid, setReceiptsUnPaid] = useState([]);
    const [user] = useContext(UserContext); 

    useEffect(() => {
        const fetchReceipts = async () => {
            try {
                const response = await Apis.get(endpoints.receipt_paid(user.id));
                setReceiptsPaid(response.data);
            } catch (error) {
                console.error('Error fetching the receipts:', error);
            }
        };

        fetchReceipts();
    }, [user.id]);

    useEffect(() => {
        const fetchReceipts = async () => {
            try {
                const response = await Apis.get(endpoints.receipt_unpaid(user.id));
                setReceiptsUnPaid(response.data);
            } catch (error) {
                console.error('Error fetching the receipts:', error);
            }
        };

        fetchReceipts();
    }, [user.id]);

    const handlePayment = async (receiptId, amount, month) => {
        try {
            const response = await Apis.get(endpoints.payment(amount, receiptId, month));
            console.log('Payment response:', response);
            const paymentUrl = response.data.url;
            if (paymentUrl) {
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
                            <th>ID</th>
                            <th>Date</th>
                            <th>Customer ID</th>
                            <th>Customer Name</th>
                            <th>Receipt Quantity</th>
                            <th>Service ID</th>
                            <th>Service Name</th>
                            <th>Tháng</th>
                            <th>Năm</th>
                            <th>Receipt Detail Cost</th>
                            <th>Total</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {receipt_paid.map((receipt) => (
                            <tr key={receipt.receiptId}>
                                <td>{receipt.receiptId}</td>
                                <td>{new Date(receipt.useServiceDate).toLocaleDateString()}</td>
                                <td>{receipt.customerId}</td>
                                <td>{receipt.customerName}</td>
                                <td>{receipt.receiptDetailQuantity}</td>
                                <td>{receipt.serviceId}</td>
                                <td>{receipt.serviceName}</td>
                                <td>{new Date(receipt.receiptDate).getMonth() + 1}</td>
                                <td>{new Date(receipt.receiptDate).getFullYear()}</td>
                                <td>{receipt.receiptDetailCost}</td>
                                <td>{receipt.receiptTotal*100000}</td>
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
                                <td>{receipt.customerId}</td>
                                <td>{receipt.customerName}</td>
                                <td>{receipt.receiptDetailQuantity}</td>
                                <td>{receipt.serviceId}</td>
                                <td>{receipt.serviceName}</td>
                                <td>{new Date(receipt.receiptDate).getMonth() + 1}</td>
                                <td>{new Date(receipt.receiptDate).getFullYear()}</td>
                                <td>{receipt.receiptDetailCost}</td>
                                <td>{receipt.receiptTotal*100000}</td>
                                <td>
                                    <button
                                        className="button payment"
                                        onClick={() =>
                                            handlePayment(
                                                receipt.receiptId,
                                                receipt.receiptTotal*100000,
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
           <div className='d-flex justify-content-center'> <MyPagination /></div>
        </div>
    );
};

export default ReceiptList;
