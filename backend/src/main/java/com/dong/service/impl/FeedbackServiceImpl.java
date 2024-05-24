package com.dong.service.impl;

import com.dong.DTO.ReceiptDTO;
import com.dong.pojo.Feedback;
import com.dong.pojo.Receipt;
import com.dong.repository.FeedbackRepository;
import com.dong.repository.ReceiptRepository;
import com.dong.service.FeedbackService;
import com.dong.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Override
    public List<Object> getFeedback(Map<String, String> params) {
        return this.feedbackRepo.getFeedback(params);
    }

    @Override
    public Feedback createReceipt(Map<String, String> params) {
        return this.feedbackRepo.createReceipt(params);
    }

}
