import React, { useState, useEffect } from 'react';
import Apis, { endpoints } from "../configs/Apis";

const tableStyle = {
  width: '100%',
  borderCollapse: 'collapse'
};

const thTdStyle = {
  border: '1px solid #ccc',
  padding: '8px',
  textAlign: 'left'
};

const thStyle = {
  ...thTdStyle,
  backgroundColor: '#f4f4f4'
};

const MerchandiseCabinet = () => {
  const [cabinets, setCabinets] = useState([]);

  useEffect(() => {
    const fetchCabinets = async () => {
      try {
        const response = await Apis.get(endpoints.merchandisecabinet);
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
      <table style={tableStyle}>
        <thead>
          <tr>
            <th style={thStyle}>Cabinet ID</th>
            <th style={thStyle}>Merchandise Name</th>
            <th style={thStyle}>Quantity</th>
            <th style={thStyle}>Date Receive</th>
            <th style={thStyle}>Is Receive</th>
            <th style={thStyle}>Merchandise Cabinet</th>
          </tr>
        </thead>
        <tbody>
          {cabinets.map(cabinet => (
            <tr key={cabinet.id}>
              <td style={thTdStyle}>{cabinet.customerId.merchandiseCabinetId.id}</td>
              <td style={thTdStyle}>{cabinet.merchandiseId.name}</td>
              <td style={thTdStyle}>{cabinet.quantity}</td>
              <td style={thTdStyle}>{new Date(cabinet.dateReceive).toLocaleDateString()}</td>
              <td style={thTdStyle}>{cabinet.isReceive ? 'Yes' : 'No'}</td>
              <td style={thTdStyle}>{cabinet.customerId.merchandiseCabinetId.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MerchandiseCabinet;
