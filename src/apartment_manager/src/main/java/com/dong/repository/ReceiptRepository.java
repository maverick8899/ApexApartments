package com.dong.repository;

import com.dong.pojo.Receipt;

import java.util.List;

public interface ReceiptRepository {
    List<Receipt> getReceipt();
    Receipt getReceiptById(int id);


}
