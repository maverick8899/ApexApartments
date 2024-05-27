//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.pojo.RelativeParkCard;
import com.dong.service.CustomerService;
import com.dong.service.RelativeParkCardService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/relativeparkcard"})
public class ApiRelativeParkCardController {
    @Autowired
    private RelativeParkCardService relativeParkCardService;
    @Autowired
    private CustomerService cusService;
    @Autowired
    private SimpleDateFormat dateFormat;

    public ApiRelativeParkCardController() {
    }

    @RequestMapping
    @CrossOrigin
    public ResponseEntity<List<RelativeParkCard>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity(this.relativeParkCardService.getRelativeParkCard(params), HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Map<String, Object> requestBody) throws ParseException {
        RelativeParkCard r = new RelativeParkCard();
        if (requestBody.containsKey("description")) {
            r.setDescription((String)requestBody.get("description"));
        }

        if (requestBody.containsKey("dateCreate")) {
            r.setDateCreate(this.dateFormat.parse((String)requestBody.get("dateCreate")));
        }

        if (requestBody.containsKey("expiry")) {
            r.setExpiry(this.dateFormat.parse((String)requestBody.get("expiry")));
        }

        if (requestBody.containsKey("active")) {
            r.setActive((Boolean)requestBody.get("active"));
        }

        if (requestBody.containsKey("cost")) {
            r.setCost(new BigDecimal(String.valueOf(requestBody.get("cost"))));
        }

        r.setCustomerId(new Customer(1));
        this.relativeParkCardService.addRelativeParkCard(r);
    }
}
