//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.Merchandise;
import com.dong.service.MerchandiseService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@ControllerAdvice
@PropertySource({"classpath:configs.properties"})
public class MerchandiseController {
    @Autowired
    private MerchandiseService MerSer;

    public MerchandiseController() {
    }

    @GetMapping({"/merchandise/{id}"})
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("merchandise", this.MerSer.getMerchandiseById(id));
        return "merchandise";
    }

    @GetMapping({"/merchandise"})
    public String list1(Model model) {
        model.addAttribute("merchandise", new Merchandise());
        return "merchandise";
    }

    @PostMapping({"/merchandise"})
    public String add(@ModelAttribute("merchandise") @Valid Merchandise c, BindingResult rs) {
        return !rs.hasErrors() && this.MerSer.addOrUpdateMerchandise(c) ? "redirect:/merchandisecabinet" : "merchandise";
    }
}
