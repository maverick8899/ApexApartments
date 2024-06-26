package com.dong.controllers;

import com.dong.DTO.ReceiptDTO;
import com.dong.pojo.Customer;
import com.dong.pojo.Feedback;
import com.dong.pojo.Receipt;
import com.dong.repository.impl.FeedbackRepositoryImpl;
import com.dong.repository.impl.ReceiptRepositoryImpl;
import com.dong.service.CustomerService;
import com.dong.service.DetailReceiptService;
import com.dong.service.FeedbackService;
import com.dong.service.ReceiptService;
import com.dong.service.RoomService;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ApiFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(
            path = "/addFeedback",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin
    public ResponseEntity<Feedback> list(@RequestBody Map<String, String> params) {
        return new ResponseEntity<>(this.feedbackService.createFeedback(params), HttpStatus.OK);

    }

    @RequestMapping(
            path = "/feedback",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin
    public ResponseEntity<List<Object>> list2(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.feedbackService.getFeedback(params), HttpStatus.OK);

    } 
}
