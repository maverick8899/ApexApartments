package com.dong.repository;

import com.dong.pojo.Accounts;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface UserRepository {

    Accounts getUserByUserName(String username);

    void addUser(Accounts user);

}
