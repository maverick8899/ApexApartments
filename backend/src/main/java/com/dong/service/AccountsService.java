package com.dong.service;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;

import java.util.List;

public interface AccountsService {
    List<Accounts> getAccounts();
    Accounts getAccountsById(int id);
    boolean addOrUpdateAccounts(Accounts r );
    boolean deleteAccounts(int id);
    List<Accounts> getAccountsUser();
    Customer getAccountsByIdCustomer(int customerId);

    List<Accounts> getAccountsByIds(List<Integer> ids);
}
