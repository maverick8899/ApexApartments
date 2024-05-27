package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.pojo.Service;
import com.dong.service.CustomerService;
import com.dong.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class ServiceController {

    @Autowired
    private ServiceService Seser;


    @RequestMapping("/service")
    public String list(Model model) {
        model.addAttribute("service", this.Seser.getServices());
        return "service";
    }

    @GetMapping("/addservice")
    public String list1(Model model) {
        model.addAttribute("service", new Service());
        return "addservice";
    }
    @GetMapping("/addservice/{id}")
    public String update(Model model, @PathVariable(value = "id") int id)  {
        model.addAttribute("service", this.Seser.getServiceById(id));
        return "addservice";
    }
    @PostMapping("/addservice")
    public String add(@ModelAttribute(value = "service") @Valid Service c,
                      BindingResult rs) {
        if (!rs.hasErrors())
            if (Seser.addOrUpdateService(c) == true)
                return "redirect:/service";

        return "addservice";
    }

}
