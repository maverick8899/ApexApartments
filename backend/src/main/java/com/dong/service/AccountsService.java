package com.dong.service;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface AccountsService extends UserDetailsService {
    List<Accounts> getAccounts(Map<String, String> params);
    Accounts getAccountsById(int id);
    boolean addOrUpdateAccounts(Accounts r );
    boolean deleteAccounts(int id);
    List<Accounts> getAccountsUser();
    Customer getAccountsByIdCustomer(int customerId);

    List<Accounts> getAccountsByIds(List<Integer> ids);
    boolean authenticate(String username, String password);
    Accounts getByUserName(String username);
}
