package com.dong.service.impl;

import com.dong.DTO.ReceiptDTO;
import com.dong.DTO.SurveyDTO;
import com.dong.pojo.Receipt;
import com.dong.pojo.Survey;
import com.dong.repository.ReceiptRepository;
import com.dong.repository.SurveyRepository;
import com.dong.service.ReceiptService;
import com.dong.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepo;

    @Override
    public List<Object> getSurvey(Map<String, String> params) {
        return this.surveyRepo.getSurvey(params);
    }

    @Override
    public boolean createSurvey(Map<String, String> params) {
        return this.surveyRepo.createSurvey(params);
    }

    @Override
    public Survey answerSurvey(SurveyDTO params) {
        return this.surveyRepo.answerSurvey(params);
    }

//    @Override
//    public Receipt getReceiptById(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public List<Object> getPersonalOpinion(Map<String, String> params) {
  return this.surveyRepo.getPersonalOpinion(params);    }

}
