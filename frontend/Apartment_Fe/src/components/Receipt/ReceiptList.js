import React, { useState, useEffect } from 'react';
import Apis, { endpoints } from '../../configs/Apis';
import './ReceiptList.css';  // Import file CSS

const ReceiptList = () => {
    const [receipt_paid, setReceiptsPaid] = useState([]);
    const [receipt_unpaid, setReceiptsUnPaid] = useState([]);

    useEffect(() => {
        const fetchReceipts = async () => {
            try {
                const response = await Apis.get(endpoints.receipt_paid(46));
                setReceiptsPaid(response.data);
            } catch (error) {
                console.error('Error fetching the receipts:', error);
            }
        };

        fetchReceipts();
    }, []);

    useEffect(() => {
        const fetchReceipts = async () => {
            try {
                const response = await Apis.get(endpoints.receipt_unpaid(46));
                setReceiptsUnPaid(response.data);
            } catch (error) {
                console.error('Error fetching the receipts:', error);
            }
        };

        fetchReceipts();
    }, []);

    const handlePayment = async (receiptId, amount,month) => {
        try {
            const response = await Apis.get(endpoints.payment(amount, receiptId,month));
            // Kiểm tra nếu API trả về URL
            if (response.request.responseURL) {
              window.location.href = response.request.responseURL;
          } else {
              console.error('Payment URL not found in the response');
          }
        } catch (error) {
            console.error('Error processing payment:', error);
        }
    };
    const handleViewDetails = (receiptId) => {
        // Thêm logic xem chi tiết ở đây
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
                        {receipt_paid.map(receipt => (
                            <tr key={receipt.receiptId}>
                                <td>{receipt.receiptId}</td>
                                <td>{new Date(receipt.useServiceDate).toLocaleDateString()}</td>
                                <td>{receipt.customerId}</td>
                                <td>{receipt.customerName}</td>
                                <td>{receipt.receiptDetailQuantity}</td>
                                <td>{receipt.serviceId}</td>
                                <td>{receipt.serviceName}</td>
                                <td>{new Date(receipt.receiptDate).getMonth()+1}</td>
                                <td>{new Date(receipt.receiptDate).getFullYear()}</td>                                
                                <td>{receipt.receiptDetailCost}</td>
                                <td>{receipt.receiptTotal}</td>
                                <td>
                                    <button className="button details" onClick={() => handleViewDetails(receipt.receiptId)}>Xem chi tiết</button>
                                </td>
                            </tr>
                        ))}
                        {receipt_unpaid.map(receipt => (
                            <tr key={receipt.receiptId}>
                                <td>{receipt.receiptId}</td>
                                <td>{new Date(receipt.useServiceDate).toLocaleDateString()}</td>
                                <td>{receipt.customerId}</td>
                                <td>{receipt.customerName}</td>
                                <td>{receipt.receiptDetailQuantity}</td>
                                <td>{receipt.serviceId}</td>
                                <td>{receipt.serviceName}</td>
                                <td>{new Date(receipt.receiptDate).getMonth()+1}</td>
                                <td>{new Date(receipt.receiptDate).getFullYear()}</td>   

                                <td>{receipt.receiptDetailCost}</td>
                                <td>{receipt.receiptTotal}</td>
                                <td>
                                    <button
                                        className="button payment"
                                        onClick={() => handlePayment(receipt.receiptId, receipt.receiptTotal, new Date(receipt.receiptDate).getMonth() + 1)}
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
        </div>
    );
};

export default ReceiptList;
