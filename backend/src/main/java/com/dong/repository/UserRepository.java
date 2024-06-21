package com.dong.repository;

import com.dong.pojo.Accounts;

public interface UserRepository {
    Accounts getUserByUsername(String username);
    void addUser(Accounts user);
}
