package com.dong.repository;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.pojo.Room;
import com.dong.pojo.Service;

import java.util.List;
import java.util.Map;

public interface AccountsRepository {
    List<Accounts> getAccounts(Map<String, String> params);
    Accounts getAccountsById(int id);
    boolean addOrUpdateAccounts(Accounts r );
    boolean deleteAccounts(int id);
    List<Accounts> getAccountsUser(Map<String, String> params);
     Customer getAccountsByIdCustomer(int customerId);
}
