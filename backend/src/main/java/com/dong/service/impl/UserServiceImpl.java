/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.service.impl;

import com.dong.pojo.Accounts;
import com.dong.repository.AccountsRepository;
import com.dong.repository.UserRepository;
import com.dong.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
//    @Qualifier("userRepository")

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Accounts users = this.userRepo.getUserByUserName(username);
        if (users == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        Accounts u = users;
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                authorities
        );
    }

    @Override
    public void addUser(Accounts account) {
        userRepo.addUser(account);
    }

    @Override
    public Accounts getUserByUserName(String username) {
        return userRepo.getUserByUserName(username);
    }

}
