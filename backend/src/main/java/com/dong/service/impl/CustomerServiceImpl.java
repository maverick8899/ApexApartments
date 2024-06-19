package com.dong.service.impl;

import com.dong.pojo.Customer;
import com.dong.repository.CustomerRepository;
import com.dong.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepo;
    @Override
    public List<Customer> getCustomers(Map<String, String> params) {
        return this.customerRepo.getCustomer(params);
    }

    @Override
    public boolean addOrUpdateCustomer(Customer c) {
        return this.customerRepo.addOrUpdateCustomer(c);
    }

    @Override
    public Customer getCustomerById(int id) {
        return this.customerRepo.getCustomerById(id);
    }

    @Override
    public boolean deleteCustomer(int id) {
        return this.customerRepo.deleteCustomer(id);
    }

    @Override
    public List<Customer> getCustomer() {
        return this.customerRepo.getCustomer();
    }

    @Override
    public List<Customer> findCustomersByAccountId(Integer accountId) {
        return this.customerRepo.getCustomersByAccountId(accountId);
    }
}
