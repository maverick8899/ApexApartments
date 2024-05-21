package com.dong.service;

import com.dong.pojo.DetailReceipt;

import java.util.List;
import java.util.Map;

public interface DetailReceiptService {

    public List<DetailReceipt> getDetailReceipt();

    public boolean addOrUpdateDetailReceipt(Map<String, String> params);

    boolean deleteDetailReceipt(int id);

    DetailReceipt getDetailReceiptById(int id);
}
