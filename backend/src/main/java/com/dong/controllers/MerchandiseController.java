//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.pojo.Merchandise;
import com.dong.pojo.MerchandiseCabinetDetail;
import com.dong.service.CustomerService;
import com.dong.service.MerchandiseService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@PropertySource({"classpath:configs.properties"})
public class MerchandiseController {
    @Autowired
    private MerchandiseService MerSer;
    @Autowired
    private CustomerService cusService;

    public MerchandiseController() {
    }

    @GetMapping({"/merchandise/{id}"})
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("merchandise", this.MerSer.getMerchandiseById(id));
        return "merchandise";
    }

    @GetMapping("/merchandise")
    public String list1(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("merchandise", new Merchandise());
        model.addAttribute("id", id);
        return "merchandise";
    }

    @PostMapping("/merchandise")
    public String add(@ModelAttribute("merchandise") @Valid Merchandise merchandise,
                      @RequestParam("customerId") Integer customerId,
                      BindingResult rs) {
        if (rs.hasErrors()) {
            return "merchandise";
        }
        boolean isMerchandiseAdded = this.MerSer.addOrUpdateMerchandise(merchandise);

        if (isMerchandiseAdded) {
            Customer customer = this.cusService.getCustomerById(customerId);
            if (customer == null) {
                return "merchandise";
            }
            MerchandiseCabinetDetail detail = new MerchandiseCabinetDetail();
            detail.setMerchandiseId(merchandise);
            detail.setCustomerId(customer);
            detail.setIsReceive(false);
            this.MerSer.addOrUpdateMerchandiseCabinetDetail(detail);
            return "redirect:/merchandisecabinet";
        } else {
            return "merchandise";
        }
    }
}
