package com.dong.service;

import com.dong.pojo.Accounts;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UserService extends UserDetailsService {
    Accounts getUserByUsername(String username);
    boolean authUser(String username, String password);
    Accounts updateUser(Map<String, String> params, MultipartFile avatar);
    void uploadAvatar(Accounts user, MultipartFile avatar) throws IOException;

}
