package com.dong.repository;

import com.dong.pojo.Customer;
import com.dong.pojo.Service;
import com.dong.pojo.UseService;

import java.util.List;
import java.util.Map;

public interface UseServiceRepository {

    List<UseService> getUseServicesByIdCustomer(int customerId);

    boolean UpdateUseService(Map<String, String> params);

    List<Object> getUseServices(Map<String, String> params);

//    boolean deleteSer(int id);
//
//    List<Service> getUseServices();
//
//    public Service getServiceById(int id);

}
