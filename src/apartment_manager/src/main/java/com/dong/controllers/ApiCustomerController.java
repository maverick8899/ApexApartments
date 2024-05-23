//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.service.CustomerService;
import com.dong.service.RoomService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class ApiCustomerController {
    @Autowired
    CustomerService cusSerivce;
    @Autowired
    RoomService roomSer;

    public ApiCustomerController() {
    }

    @DeleteMapping({"/customers/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        this.cusSerivce.deleteCustomer(id);
    }

    @RequestMapping({"/customers/"})
    @CrossOrigin
    public ResponseEntity<List<Customer>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity(this.cusSerivce.getCustomers(params), HttpStatus.OK);
    }

    @RequestMapping(
            path = {"/customers/{customerId}/"},
            produces = {"application/json"}
    )
    @CrossOrigin
    public ResponseEntity<Customer> details(@PathVariable("customerId") int id) {
        return new ResponseEntity(this.cusSerivce.getCustomerById(id), HttpStatus.OK);
    }
}
