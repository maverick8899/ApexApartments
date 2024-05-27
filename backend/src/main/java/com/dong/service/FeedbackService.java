package com.dong.service;

import com.dong.DTO.ReceiptDTO;
import com.dong.pojo.Feedback;
import com.dong.pojo.Receipt;

import java.util.List;
import java.util.Map;

public interface FeedbackService {

//    List<Feedback> getFeedback(Map<String, String> params);
    List<Object> getFeedback(Map<String, String> params);

    Feedback createFeedback(Map<String, String> params);
}
