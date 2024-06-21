package com.dong.controllers;

import com.dong.pojo.Accounts;
import com.dong.service.AccountsService;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
@PropertySource({"classpath:configs.properties"})
public class AccountsController {
    @Autowired
    private AccountsService accSer;

    public AccountsController() {
    }

    @RequestMapping({"/account"})
    public String list(Model model,@RequestParam Map<String, String> params) {
        model.addAttribute("account", this.accSer.getAccounts(params));
        return "account";
    }

    @GetMapping({"/accounts"})
    public String list1(Model model) {
        model.addAttribute("account", new Accounts());
        return "accounts";
    }

    @GetMapping({"/accounts/{id}"})
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("account", this.accSer.getAccountsById(id));
        return "accounts";
    }

    @PostMapping({"/accounts"})
    public String add(@ModelAttribute("account") @Valid Accounts c, BindingResult rs) {
        return !rs.hasErrors() && this.accSer.addOrUpdateAccounts(c) ? "redirect:/account" : "accounts";
    }
}
