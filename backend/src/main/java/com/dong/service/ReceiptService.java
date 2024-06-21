package com.dong.service;

import com.dong.DTO.ReceiptDTO;
import com.dong.pojo.Receipt;

import java.util.List;
import java.util.Map;

public interface ReceiptService {

    List<ReceiptDTO> getReceipt(Map<String, String> params);

    boolean deleteReceipt(int id);

    Receipt getReceiptById(int id);

    public boolean addOrUpdateReceipt(Receipt r);

    boolean payment(String receiptId) ;//    List<ReceiptDTO> getReceiptDetail(Map<String, String> params);

}
