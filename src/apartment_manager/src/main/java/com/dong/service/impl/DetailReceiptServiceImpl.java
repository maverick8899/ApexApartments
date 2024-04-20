package com.dong.service.impl;

import com.dong.pojo.DetailReceipt;
import com.dong.repository.DetailReceiptRepository;
import com.dong.service.DetailReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetailReceiptServiceImpl implements DetailReceiptService {
    @Autowired
    private DetailReceiptRepository detailReceiptRepository;
    @Override
    public List<DetailReceipt> getDetailReceipt() {
        return this.detailReceiptRepository.getDetailReceipt();
    }
}
