package com.dong.controllers;

import com.dong.pojo.Customer;
import com.dong.pojo.Service;
import com.dong.pojo.UseService;
import com.dong.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class ApiServiceController {
    @Autowired
    private ServiceService Ser;
    @DeleteMapping("/service/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.Ser.deleteSer(id);
    }
    @RequestMapping("/service/")
    @CrossOrigin
    public ResponseEntity<List<Service>> list() {
        return new ResponseEntity<>(this.Ser.getServices(), HttpStatus.OK);
    }
    @RequestMapping(
            path = {"/service/customers/{customerId}"},
            produces = {"application/json"}
    )
    @CrossOrigin
    public ResponseEntity<Customer> details(@PathVariable("customerId") int id) {
        return new ResponseEntity(this.Ser.getServicesByIdCustomer(id), HttpStatus.OK);
    }
    @PostMapping("/use-services")
    public ResponseEntity<Void> createUseService(@RequestBody UseService useService) {
        this.Ser.save(useService);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/use-services")
    public ResponseEntity<UseService> getUseService(
            @RequestParam int customerId,
            @RequestParam int serviceId) {
        UseService useService = this.Ser.getUseServiceByCustomerIdAndServiceId(customerId, serviceId);
        if (useService != null) {
            return ResponseEntity.ok(useService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/use_service/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete2(@PathVariable(value = "id") int id) {
        this.Ser.deleteUseSer(id);
    }
}
