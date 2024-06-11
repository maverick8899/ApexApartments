/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.service;

import com.dong.pojo.UseService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MAVERICK
 */
public interface UseServiceService {

    List<UseService> getUseServicesByIdCustomer(int customerId);

    boolean UpdateUseService(Map<String, String> params);

    List<Object> getUseServices(Map<String, String> params);
}
