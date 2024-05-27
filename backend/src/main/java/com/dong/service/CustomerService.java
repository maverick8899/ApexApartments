package com.dong.service;

import com.dong.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<Customer> getCustomers(Map<String, String> params);
    boolean addOrUpdateCustomer(Customer c);
    Customer getCustomerById(int id);
    boolean deleteCustomer(int id);
    public List<Customer> getCustomer();
}
