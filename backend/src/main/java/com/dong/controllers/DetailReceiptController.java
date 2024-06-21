package com.dong.controllers;

import com.dong.service.DetailReceiptService;
import com.dong.service.ServiceService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class DetailReceiptController {
    @Autowired
    private DetailReceiptService detailSer;
    @Autowired
    private ServiceService serSer;


    @RequestMapping("/detailreceipt")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("detailreceipt", this.detailSer.getDetailReceipt(params));
        model.addAttribute("service", this.serSer.getServices(params));

        return "detailreceipt";
    }
}
