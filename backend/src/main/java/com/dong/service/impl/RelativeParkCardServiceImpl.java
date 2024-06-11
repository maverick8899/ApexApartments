package com.dong.service.impl;

import com.dong.pojo.RelativeParkCard;
import com.dong.repository.ReceiptRepository;
import com.dong.repository.RelativeParkCardRepository;
import com.dong.service.RelativeParkCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class RelativeParkCardServiceImpl implements RelativeParkCardService {
    @Autowired
    private RelativeParkCardRepository receiptRepo;
    @Override
    public List<RelativeParkCard> getRelativeParkCard(Map<String, String> var1) {
        return this.receiptRepo.getRelativeParkCard(var1);
    }

    @Override
    public RelativeParkCard getRelativeParkCardById(int var1) {
        return this.receiptRepo.getRelativeParkCardById(var1);
    }

    @Override
    public boolean deleteRelativeParkCard(int var1) {
        return this.receiptRepo.deleteRelativeParkCard(var1);
    }

    @Override
    public RelativeParkCard addRelativeParkCard(RelativeParkCard var1) {
        return this.receiptRepo.addRelativeParkCard(var1);
    }

    @Override
    public boolean updateRelativeParkCard(Map<String, String> params) {
      return this.receiptRepo.updateRelativeParkCard(params);}
}
