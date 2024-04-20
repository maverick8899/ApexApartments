/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.controllers;

import com.dong.pojo.Service;
import com.dong.service.CustomerService;
import com.dong.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexController {
    @Autowired
    private CustomerService cusService;
    @Autowired
    private ServiceService  se;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("customer",this.cusService.getCustomers(null));
        return "index";
    }

    @RequestMapping("/test")
    public String test()
    {
//        List <Service> service = se.getServicesByIdCustomer();
        return "123";
    }


}
