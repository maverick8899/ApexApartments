//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.controllers;

import com.dong.pojo.MerchandiseCabinetDetail;
import com.dong.service.MerchandiseCabinetService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/merchandisecabinet"})
public class ApiMerchandiseCabinetController {
    @Autowired
    private MerchandiseCabinetService merSer;

    public ApiMerchandiseCabinetController() {
    }

    @RequestMapping
    @CrossOrigin
    public ResponseEntity<List<MerchandiseCabinetDetail>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity(this.merSer.getMerchandiseCabinet(params), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<List<MerchandiseCabinetDetail>> list2(@PathVariable("id") int id) {
        return new ResponseEntity(this.merSer.getMerchandiseByCustomerId(id),HttpStatus.OK);
    }

    }
