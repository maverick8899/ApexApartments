

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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{customerId}")
        @CrossOrigin
        public ResponseEntity<List<RelativeParkCard>> list(@PathVariable Integer customerId) {
        List<RelativeParkCard> relativeParkCards = this.relativeParkCardService.getRelativeParkCardByCustomerId(customerId);
        return new ResponseEntity<>(relativeParkCards, HttpStatus.OK);
        }

    @PostMapping("/{customerId}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@PathVariable Integer customerId, @RequestBody Map<String, Object> requestBody) throws ParseException {
        RelativeParkCard r = new RelativeParkCard();

        if (requestBody.containsKey("description")) {
            r.setDescription((String) requestBody.get("description"));
        }
        if (requestBody.containsKey("cost")) {
            r.setCost(new BigDecimal(String.valueOf(requestBody.get("cost"))));
        }

        if (requestBody.containsKey("dateCreate")) {
            r.setDateCreate(this.dateFormat.parse((String) requestBody.get("dateCreate")));
        }

        if (requestBody.containsKey("expiry")) {
            r.setExpiry(this.dateFormat.parse((String) requestBody.get("expiry")));
        }

        if (requestBody.containsKey("active")) {
            r.setActive((Boolean) requestBody.get("active"));
        }

        r.setCustomerId(new Customer(customerId));

        this.relativeParkCardService.addRelativeParkCard(r);
    }
}

