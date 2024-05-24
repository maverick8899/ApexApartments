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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
