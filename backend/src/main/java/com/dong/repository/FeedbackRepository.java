package com.dong.repository;

import com.dong.pojo.Feedback;
import java.util.List;
import java.util.Map;

public interface FeedbackRepository {

    List<Object> getFeedback(Map<String, String> params);

    Feedback createReceipt(Map<String, String> params);
}
