package com.dong.service;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface AccountsService {
    List<Accounts> getAccounts(Map<String, String> params);
    Accounts getAccountsById(int id);
    boolean addOrUpdateAccounts(Accounts r );
    boolean deleteAccounts(int id);
    List<Accounts> getAccountsUser(Map<String, String> params);
    Customer getAccountsByIdCustomer(int customerId);

    List<Accounts> getAccountsByIds(List<Integer> ids);
}
