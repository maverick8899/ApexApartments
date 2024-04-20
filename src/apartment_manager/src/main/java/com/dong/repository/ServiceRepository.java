package com.dong.repository;

import com.dong.pojo.Customer;
import com.dong.pojo.Service;

import java.util.List;

public interface ServiceRepository {
    List<Service> getServicesByIdCustomer(int customerId);
    public boolean addOrUpdateService(Service s) ;

    boolean deleteSer(int id);
    List<Service> getServices();
    public Service getServiceById(int id);

}
