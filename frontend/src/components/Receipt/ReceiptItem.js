// src/components/ReceiptItem.js
import React from 'react';

const ReceiptItem = ({ receipt, onPay, onViewDetails }) => {
  return (
    <div style={{ border: '1px solid #ccc', marginBottom: '10px', padding: '10px' }}>
      <p>Mã hóa đơn: {receipt.code}</p>
      <p>Chi tiết hóa đơn: {receipt.details}</p>
      <p>Tổng tiền thanh toán: {receipt.total}</p>
      <p>Trạng thái thanh toán: {receipt.isPaid ? 'Đã thanh toán' : 'Chưa thanh toán'}</p>
      {receipt.isPaid ? (
        <button onClick={() => onViewDetails(receipt.id)}>Xem chi tiết</button>
      ) : (
        <button onClick={() => onPay(receipt.id)}>Thanh toán</button>
      )}
    </div>
  );
};

export default ReceiptItem;
