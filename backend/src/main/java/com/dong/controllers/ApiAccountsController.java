package com.dong.controllers;

import com.dong.pojo.Accounts;
import com.dong.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiAccountsController {
    @Autowired
    private AccountsService accSer;
    @DeleteMapping("/account/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.accSer.deleteAccounts(id);
    }
    @RequestMapping("/account/")
    @CrossOrigin
    public ResponseEntity<List<Accounts>> list() {
        return new ResponseEntity<>(this.accSer.getAccounts(), HttpStatus.OK);
    }
}
