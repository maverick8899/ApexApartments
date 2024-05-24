//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.MerchandiseCabinetDetail;
import com.dong.service.CustomerService;
import com.dong.service.MerchandiseCabinetService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@PropertySource({"classpath:configs.properties"})
public class MerchandiseCabinetController {
    @Autowired
    private MerchandiseCabinetService MerSer;
    @Autowired
    private CustomerService cusSer;

    public MerchandiseCabinetController() {
    }

    @RequestMapping({"merchandisecabinet"})
    public String index(Model model) {
        model.addAttribute("merchandisecabinet", this.cusSer.getCustomers((Map)null));
        return "merchandisecabinet";
    }

    @GetMapping({"/cabinetdetails"})
    public String list1(Model model) {
        model.addAttribute("cabinetdetails", new MerchandiseCabinetDetail());
        return "cabinetdetails";
    }

    @GetMapping({"/cabinetdetails/{id}"})
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("cabinetdetails", this.MerSer.getMerchandiseByCustomerId(id));
        return "cabinetdetails";
    }
}
