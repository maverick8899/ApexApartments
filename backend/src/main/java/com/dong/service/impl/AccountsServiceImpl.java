package com.dong.service.impl;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.repository.AccountsRepository;
import com.dong.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private AccountsRepository accRep;
    @Override
    public List<Accounts> getAccounts(Map<String, String> params) {
        return this.accRep.getAccounts(params);
    }

    @Override
    public Accounts getAccountsById(int id) {
        return this.accRep.getAccountsById(id);
    }

    @Override
    public boolean addOrUpdateAccounts(Accounts r) {
        return this.accRep.addOrUpdateAccounts(r);
    }

    @Override
    public boolean deleteAccounts(int id) {
        return this.accRep.deleteAccounts(id);
    }

    @Override
    public List<Accounts> getAccountsUser(Map<String, String> params) {
        return this.accRep.getAccountsUser(params);
    }

    @Override
    public Customer getAccountsByIdCustomer(int customerId) {
        return  this.accRep.getAccountsByIdCustomer(customerId);
    }

    @Override
    public List<Accounts> getAccountsByIds(List<Integer> ids) {
        return this.accRep.getByIds(ids);
    }
}
