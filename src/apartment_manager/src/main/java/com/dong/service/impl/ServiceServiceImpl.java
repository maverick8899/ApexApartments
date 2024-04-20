package com.dong.service.impl;

import com.dong.pojo.Service;
import com.dong.repository.ServiceRepository;
import com.dong.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository Seser;
    @Override
    public List<Service> getServicesByIdCustomer(int customerId) {
        return this.Seser.getServicesByIdCustomer(customerId);
    }

    @Override
    public boolean addOrUpdateService(Service s) {
        return this.Seser.addOrUpdateService(s);
    }

    @Override
    public List<Service> getServices() {
        return this.Seser.getServices();
    }

    @Override
    public Service getServiceById(int id) {
        return this.Seser.getServiceById(id);
    }

    @Override
    public boolean deleteSer(int id) {
        return this.Seser.deleteSer(id);
    }
}
