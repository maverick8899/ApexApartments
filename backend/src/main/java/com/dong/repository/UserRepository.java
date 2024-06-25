package com.dong.repository;

import com.dong.pojo.Accounts;

public interface UserRepository {
    Accounts getUserByUsername(String username);
    Accounts save(Accounts user);

    boolean authUser(String username, String password);
}
