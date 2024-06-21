package com.dong.service.impl;

import com.dong.pojo.DetailReceipt;
import com.dong.repository.DetailReceiptRepository;
import com.dong.service.DetailReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DetailReceiptServiceImpl implements DetailReceiptService {

    @Autowired
    private DetailReceiptRepository detailReceiptRepository;

    @Override
    public List<DetailReceipt> getDetailReceipt(Map<String, String> params) {
        return this.detailReceiptRepository.getDetailReceipt(params);
    }

    @Override
    public boolean addOrUpdateDetailReceipt(Map<String, String> params) {
        return this.detailReceiptRepository.addOrUpdateDetailReceipt(params);
    }

    @Override
    public boolean deleteDetailReceipt(int id) {
        return this.detailReceiptRepository.deleteDetailReceipt(id);
    }

    @Override
    public DetailReceipt getDetailReceiptById(int id) {
        return this.detailReceiptRepository.getDetailReceiptById(id);
    }

}
