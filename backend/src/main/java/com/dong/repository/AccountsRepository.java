package com.dong.repository;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.pojo.Room;
import com.dong.pojo.Service;

import java.util.List;

public interface AccountsRepository {
    List<Accounts> getAccounts();
    Accounts getAccountsById(int id);
    boolean addOrUpdateAccounts(Accounts r );
    boolean deleteAccounts(int id);
    List<Accounts> getAccountsUser();
     Customer getAccountsByIdCustomer(int customerId);

    List<Accounts> getByIds(List<Integer> ids);
}
