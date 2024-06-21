import React, { useState, useEffect } from 'react';
import Apis, { endpoints } from "../configs/Apis";
import './Receipt/ReceiptList.css';


const MerchandiseCabinet = () => {
  const [cabinets, setCabinets] = useState([]);

  useEffect(() => {
    const fetchCabinets = async () => {
      try {
        const response = await Apis.get(endpoints.merchandisecabinet(1));
        setCabinets(response.data);
      } catch (error) {
        console.error('Error fetching cabinets:', error);
      }
    };

    fetchCabinets();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="mb-4">Merchandise Cabinets</h1>
      <table className="receipt-table">
        <thead>
          <tr>
            <th>Cabinet ID</th>
            <th>Merchandise Name</th>
            <th>Quantity</th>
            <th>Date Receive</th>
            <th>Is Receive</th>
            <th>Merchandise Cabinet</th>
          </tr>
        </thead>
        <tbody>
          {cabinets.map(cabinet => (
            <tr key={cabinet.id}>
              <td>{cabinet.customerId.merchandiseCabinetId.id}</td>
              <td>{cabinet.merchandiseId.name}</td>
              <td>{cabinet.quantity}</td>
              <td>{new Date(cabinet.dateReceive).toLocaleDateString()}</td>
              <td>{cabinet.isReceive ? 'Yes' : 'No'}</td>
              <td>{cabinet.customerId.merchandiseCabinetId.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MerchandiseCabinet;