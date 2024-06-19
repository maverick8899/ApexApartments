package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.pojo.Service;
import com.dong.service.AccountsService;
import com.dong.service.CustomerService;
import com.dong.service.RoomService;
import com.dong.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService cusService;
    @Autowired
    private RoomService roomService;
    @Autowired
    ServiceService se;
    @Autowired
    private AccountsService accSer;

    @GetMapping("/customers")
    public String list(Model model, @RequestParam Map<String, String> params) {
//        model.addAttribute("customer", new Customer());
        model.addAttribute("customer", this.cusService.getCustomers(params));
        return "customer";
    }

    @GetMapping("/addCustomer")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("room", this.roomService.getRoom()
        );
    }

    @PostMapping("/customers")
    public String add(@ModelAttribute(value = "customer") @Valid Customer c,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (cusService.addOrUpdateCustomer(c) == true) {
                return "redirect:/";
            }
        }

        return "customers";
    }

    @GetMapping("/addCustomer/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
//        model.addAttribute("customer", this.cusService.getCustomerById(id));
        model.addAttribute("service", this.se.getServicesByIdCustomer(id));
        model.addAttribute("customer", this.accSer.getAccountsByIdCustomer(id));
        return "addCustomer";
    }
}
