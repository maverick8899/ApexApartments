package com.dong.controllers;

import com.dong.DTO.ReceiptDTO;
import com.dong.DTO.SurveyDTO;
import com.dong.pojo.Customer;
import com.dong.pojo.CustomerSurvey;
import com.dong.pojo.Feedback;
import com.dong.pojo.Receipt;
import com.dong.pojo.Survey;
import com.dong.repository.impl.FeedbackRepositoryImpl;
import com.dong.repository.impl.ReceiptRepositoryImpl;
import com.dong.service.CustomerService;
import com.dong.service.DetailReceiptService;
import com.dong.service.FeedbackService;
import com.dong.service.ReceiptService;
import com.dong.service.RoomService;
import com.dong.service.SurveyService;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ApiSurveyController {

    @Autowired
    private SurveyService surveyService; 
    
    @PostMapping(
            path = "/survey/answers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    @CrossOrigin
    public ResponseEntity<Boolean> list(@RequestBody SurveyDTO params) {
        return new ResponseEntity<>(this.surveyService.answerSurvey(params), HttpStatus.OK);

    }
    
    
    @RequestMapping(
            path = "/survey/questions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    @CrossOrigin
    public ResponseEntity<List<Object>> list2(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.surveyService.getQuestionsBySurvey(params), HttpStatus.OK);

    }
    
    
}
