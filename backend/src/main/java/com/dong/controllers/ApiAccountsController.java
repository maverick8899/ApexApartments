//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.Accounts;
import com.dong.service.AccountsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class ApiAccountsController {
    @Autowired
    private AccountsService accSer;

    public ApiAccountsController() {
    }

    @DeleteMapping({"/account/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        this.accSer.deleteAccounts(id);
    }

    @RequestMapping({"/account/"})
    @CrossOrigin
    public ResponseEntity<List<Accounts>> list() {
        return new ResponseEntity(this.accSer.getAccounts(), HttpStatus.OK);
    }
}
