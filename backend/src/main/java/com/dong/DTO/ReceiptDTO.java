/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.DTO;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.cloudinary.json.JSONObject;

/**
 *
 * @author MAVERICK
 */
@Data
public class ReceiptDTO {

    private Object receiptId;
    private Object receiptDate;
    private Object receiptTotal;
    private Object receiptPay;
    private Object customerId;
    private Object customerName;
    private Object customerEmail;
    private Object receiptDetailQuantity; // Thay đổi từ float sang Double
    private Object serviceId;
    private Object serviceName;
    private Object serviceDescription;
    private Object serviceUnit;
    private Object useServiceDate;
    private Object receiptDetailCost;

    public ReceiptDTO(
            Object receiptId,
            Object receiptTotal,
            Object receiptDate,
            Object receiptPay,
            Object customerId,
            Object customerName,
            Object customerEmail,
            Object receiptDetailQuantity,
            Object serviceId,
            Object serviceName,
            Object serviceDescription,
            Object serviceUnit,
            Object useServiceDate,
            Object receiptDetailCost
    ) {
        this.receiptId = receiptId;
        this.receiptDate = receiptDate;
        this.receiptTotal = receiptTotal;
        this.receiptPay = receiptPay;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.receiptDetailQuantity = receiptDetailQuantity;
        this.receiptDetailCost = receiptDetailCost;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceUnit = serviceUnit;
        this.useServiceDate = useServiceDate;
    }
}
