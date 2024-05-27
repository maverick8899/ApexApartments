package com.dong.service;

import com.dong.pojo.Customer;
import com.dong.pojo.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getServicesByIdCustomer(int customerId);
    public boolean addOrUpdateService(Service s) ;
    public List<Service> getServices();
    public Service getServiceById(int id);
    boolean deleteSer(int id);

}
