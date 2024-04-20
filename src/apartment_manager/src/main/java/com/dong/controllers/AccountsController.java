package com.dong.controllers;
import com.dong.pojo.Accounts;
import com.dong.service.AccountsService;
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
public class AccountsController  {

    @Autowired
    private AccountsService accSer;


    @RequestMapping("/account")
    public String list(Model model) {
        model.addAttribute("account", this.accSer.getAccounts());
        return "account";
    }

    @GetMapping("/accounts")
    public String list1(Model model) {
        model.addAttribute("account", new Accounts());
        return "accounts";
    }
    @GetMapping("/accounts/{id}")
    public String update(Model model, @PathVariable(value = "id") int id)  {
        model.addAttribute("account", this.accSer.getAccountsById(id));
        return "accounts";
    }
    @PostMapping("/accounts")
    public String add(@ModelAttribute(value = "account") @Valid Accounts c,
                      BindingResult rs) {
        if (!rs.hasErrors())
            if (accSer.addOrUpdateAccounts(c) == true)
                return "redirect:/account";
        return "accounts";
    }

}
