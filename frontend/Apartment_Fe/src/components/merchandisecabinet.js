import React, { useState, useEffect, useContext } from 'react';
import Apis, { endpoints } from "../configs/Apis";
import './Receipt/ReceiptList.css';
import UserContext from '../contexts/UserContext';

const MerchandiseCabinet = () => {
  const [cabinets, setCabinets] = useState([]);
  const [user] = useContext(UserContext); 


  useEffect(() => {
    const fetchCabinets = async () => {
      try {
        const response = await Apis.get(endpoints.merchandisecabinet(user.id));
        setCabinets(response.data);
      } catch (error) {
        console.error('Error fetching cabinets:', error);
      }
    };

    fetchCabinets();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="mb-4">Tủ đựng hàng hóa</h1>
      <table className="receipt-table">
        <thead>
          <tr>
            <th>Tên hàng hóa</th>
            <th>Số lượng</th>
            <th>Ngày nhận</th>
            <th>Được nhận</th>
            {/* <th>Merchandise Cabinet</th> */}
          </tr>
        </thead>
        <tbody>
          {cabinets.map(cabinet => (
            <tr key={cabinet.id}>
              <td>{cabinet.merchandiseId.name}</td>
              <td>{cabinet.quantity}</td>
              <td>{new Date(cabinet.dateReceive).toLocaleDateString()}</td>
              <td>{cabinet.isReceive ? 'Rồi' : 'Chưa'}</td>
              {/* <td>{cabinet.customerId.merchandiseCabinetId.name}</td> */}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MerchandiseCabinet;