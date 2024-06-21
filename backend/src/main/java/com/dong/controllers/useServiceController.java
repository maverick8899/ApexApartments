package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.pojo.DetailReceipt;
import com.dong.pojo.Receipt;
import com.dong.pojo.Service;
import com.dong.repository.FeedbackRepository;
import com.dong.repository.impl.ReceiptRepositoryImpl;
import com.dong.service.AccountsService;
import com.dong.service.CustomerService;
import com.dong.service.DetailReceiptService;
import com.dong.service.FeedbackService;
import com.dong.service.ReceiptService;
import com.dong.service.RoomService;
import com.dong.service.ServiceService;
import com.dong.service.UseServiceService;
import com.dong.service.impl.ReceiptServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.Map;
import org.springframework.context.annotation.PropertySource;

@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class useServiceController {
    @Autowired
    private UseServiceService useServiceService;

    @GetMapping("/useService")
    public String getReceipts(Model model, @RequestParam Map<String, String> params) {
//        model.addAttribute("receipt", new Receipt());
        model.addAttribute("useServices", this.useServiceService.getUseServices(params));
        return "useService";
    }
    

}
