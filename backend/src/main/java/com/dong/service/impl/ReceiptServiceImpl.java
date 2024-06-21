package com.dong.service.impl;

import com.dong.DTO.ReceiptDTO;
import com.dong.pojo.Receipt;
import com.dong.repository.ReceiptRepository;
import com.dong.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepo;

    @Override
    public List<ReceiptDTO> getReceipt(Map<String, String> params) {
        return this.receiptRepo.getReceipt(params);
    }

    @Override
    public boolean deleteReceipt(int id) {
        return this.receiptRepo.deleteReceipt(id);
    }

    @Override
    public Receipt getReceiptById(int id) {
        return this.receiptRepo.getReceiptById(id);
    }

    @Override
    public boolean addOrUpdateReceipt(Receipt r) {
        return this.receiptRepo.addOrUpdateReceipt(r);
    }

//    @Override
//    public List<ReceiptDTO> getReceiptDetail(Map<String, String> params) {
//        return this.receiptRepo.getReceiptDetail(params);
//    }
    @Override
    public boolean payment(String receiptId) {
        return this.receiptRepo.payment(receiptId);
    }

}
