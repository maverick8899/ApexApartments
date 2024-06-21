package com.dong.repository;

import com.dong.DTO.ReceiptDTO;
import com.dong.pojo.DetailReceipt;

import java.util.List;
import java.util.Map;

public interface DetailReceiptRepository {

    public List<DetailReceipt> getDetailReceipt(Map<String, String> params);

    boolean addOrUpdateDetailReceipt(Map<String, String> params);

    boolean deleteDetailReceipt(int id);
    DetailReceipt getDetailReceiptById(int id);
}
