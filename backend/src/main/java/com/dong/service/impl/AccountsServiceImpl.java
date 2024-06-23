package com.dong.service.impl;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.repository.AccountsRepository;
import com.dong.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private AccountsRepository accRep;

    @Autowired
    private BCryptPasswordEncoder passEncoder;
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
        r.setPassword(passEncoder.encode(r.getPassword()));
        return this.accRep.addOrUpdateAccounts(r);
    }

    @Override
    public boolean deleteAccounts(int id) {
        return this.accRep.deleteAccounts(id);
    }

    @Override
    public List<Accounts> getAccountsUser() {
        return this.accRep.getAccountsUser();
    }

    @Override
    public Customer getAccountsByIdCustomer(int customerId) {
        return  this.accRep.getAccountsByIdCustomer(customerId);
    }

    @Override
    public List<Accounts> getAccountsByIds(List<Integer> ids) {
        return this.accRep.getByIds(ids);
    }

    @Override
    public boolean authenticate(String username, String password) {
        Accounts a = accRep.findByUsername(username);
        if (a == null)
            throw new UsernameNotFoundException("User not found");

        return passEncoder.matches(password, a.getPassword());
    }

    @Override
    public Accounts getByUserName(String username) {
        return accRep.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts account = accRep.findByUsername(username);
        if (account == null)
            throw new UsernameNotFoundException("User not found");

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole()));
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), authorities);
    }
}
