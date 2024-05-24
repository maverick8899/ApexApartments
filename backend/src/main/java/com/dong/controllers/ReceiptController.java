package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.pojo.DetailReceipt;
import com.dong.pojo.Receipt;
import com.dong.pojo.Service;
import com.dong.repository.impl.ReceiptRepositoryImpl;
import com.dong.service.AccountsService;
import com.dong.service.CustomerService;
import com.dong.service.DetailReceiptService;
import com.dong.service.ReceiptService;
import com.dong.service.RoomService;
import com.dong.service.ServiceService;
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
public class ReceiptController {
//    @Autowired
//    private CustomerService cusService;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private DetailReceiptService detailReceiptService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/receipt")
    public String getReceipts(Model model, @RequestParam Map<String, String> params) {
//        model.addAttribute("receipt", new Receipt());
        model.addAttribute("receipts", this.receiptService.getReceipt(params));
        return "receipt";
    }

    @GetMapping("/addReceipt")
    public String addReceipts(Model model, @RequestParam Map<String, String> params) {
//        model.addAttribute("receipt", new Receipt());
//        model.addAttribute("receipts",this.receiptService.getReceipt(params));
        model.addAttribute("customers", this.customerService.getCustomers(null));
        model.addAttribute("services", this.serviceService.getServices());
        return "addReceipt";
    }

//? @Valid: This annotation is used validation. It instructs Spring MVC to 
    //validate the Receipt object (c) using any validation annotations present on its properties.    
//? BindingResult rs: This parameter is of type BindingResult. It's used to capture any validation errors 
    //that might occur during the validation process with @Valid.
    @PostMapping("/addReceipt")
    public String addOrUpdateReceipts(Model model, @RequestParam Map<String, String> params) {
        if (this.detailReceiptService.addOrUpdateDetailReceipt(params) == true) {
            return "redirect:/receipt";
        }
        return "addReceipt";
    }

    @GetMapping("/receiptDetail")
    public String getReceiptDetail(@RequestParam Map<String, String> params,
            Model model) {
        model.addAttribute("receipts", this.receiptService.getReceipt(params));
        return "receiptDetail";
    }
     
    
//    @PostMapping("/receipt/{id}")
//    public String getReceiptById(Model model, @RequestParam Map<String, String> params) {
//        model.addAttribute("receipts", this.receiptService.getReceipt(params));
//
//        return "receipt";
//    }

//    @ModelAttribute
//        public void commonAttr(Model model) {
//        model.addAttribute("room", this.roomService.getRoom()
//        );
//    }
//    @PostMapping("/customers")
//    public String add(@ModelAttribute(value = "customer") @Valid Customer c,
//                      BindingResult rs) {
//        if (!rs.hasErrors())
//            if (cusService.addOrUpdateCustomer(c) == true)
//                return "redirect:/";
//
//        return "customers";
//    }
}
