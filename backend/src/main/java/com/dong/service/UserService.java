package com.dong.service;

import com.dong.pojo.Accounts;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author admin
 */
public interface UserService extends UserDetailsService {
    Accounts getUserByUsername(String username);
    void addUser(Accounts user);
}