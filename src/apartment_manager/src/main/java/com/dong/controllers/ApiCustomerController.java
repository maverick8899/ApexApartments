package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.service.CustomerService;
import com.dong.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class ApiCustomerController {
    @Autowired
    CustomerService cusSerivce;
    @Autowired
    RoomService roomSer;
    
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.cusSerivce.deleteCustomer(id);
    }

    @RequestMapping(path = "/customers" ,  produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Customer>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.cusSerivce.getCustomers(params), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/customers/{customerId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Customer> details(@PathVariable(value = "customerId") int id) {
        return new ResponseEntity<>(this.cusSerivce.getCustomerById(id), HttpStatus.OK);
    }
//    @PostMapping(path = "/customers", consumes = {
//            MediaType.MULTIPART_FORM_DATA_VALUE,
//            MediaType.APPLICATION_JSON_VALUE
//    })
//    @ResponseStatus(HttpStatus.CREATED)
//    public void add(@RequestParam Map<String, String> params) {
//        Customer c = new Customer();
//        c.setName(params.get("name"));
//        c.setAddress(params.get("address"));
//        c.setPhoneNumber(params.get("phoneNumber"));
//        c.setRoomId(this.roomSer.getRoomById(Integer.parseInt(params.get("roomId"))));
//        c.setId(Integer.parseInt(params.get("id")));
//        this.cusSerivce.addOrUpdateCustomer(c);
//    }

}
