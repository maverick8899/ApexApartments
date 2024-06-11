/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.service.impl;

import com.dong.pojo.UseService;
import com.dong.repository.UseServiceRepository;
import com.dong.service.UseServiceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MAVERICK
 */
@Service
public class UseServiceServiceImpl implements UseServiceService {

    @Autowired
    private UseServiceRepository useServiceRepository;

    @Override
    public List<UseService> getUseServicesByIdCustomer(int customerId) {
        return this.useServiceRepository.getUseServicesByIdCustomer(customerId);
    }

    @Override
    public boolean UpdateUseService(Map<String, String> params) {
        return this.useServiceRepository.UpdateUseService(params);
    }

    @Override
    public List<Object> getUseServices(Map<String, String> params) {
        return this.useServiceRepository.getUseServices(params);
    }

}
