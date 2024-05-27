/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.service.impl;

import com.dong.pojo.UseService;
import com.dong.service.UseServiceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MAVERICK
 */
public class UseServiceServiceImpl implements UseServiceService {
    
    @Autowired
    private UseServiceService useServiceService;
    
    @Override
    public List<UseService> getUseServicesByIdCustomer(int customerId) {
        return this.useServiceService.getUseServicesByIdCustomer(customerId);
    }
    
    @Override
    public boolean UpdateUseService(Map<String, String> params) {
        return this.useServiceService.UpdateUseService(params);
    }
    
}
