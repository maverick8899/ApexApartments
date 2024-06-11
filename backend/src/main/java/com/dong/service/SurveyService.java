package com.dong.service;

import com.dong.DTO.ReceiptDTO;
import com.dong.DTO.SurveyDTO;
import com.dong.pojo.Receipt;
import com.dong.pojo.Survey;

import java.util.List;
import java.util.Map;

public interface SurveyService {

   List<Object> getSurvey(Map<String, String> params);

    boolean createSurvey(Map<String, String> params);

    Survey answerSurvey(SurveyDTO params);
    List<Object> getPersonalOpinion(Map<String, String> params);

//    public Survey getRecById(int id);
}
