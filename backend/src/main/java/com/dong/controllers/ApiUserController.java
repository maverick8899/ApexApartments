package com.dong.controllers;


import com.dong.DTO.ChatUserInfoDto;
import com.dong.DTO.UserLoginRequestDto;
import com.dong.DTO.UserLoginResponseDto;
import com.dong.components.JwtService;
import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.service.AccountsService;
import com.dong.service.CustomerService;
import com.dong.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiUserController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountsService accService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

        @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        if (accService.authenticate(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword())) {
            String token = this.jwtService.generateTokenLogin(userLoginRequestDto.getUsername());
            Accounts accounts = accService.getByUserName(userLoginRequestDto.getUsername());
            String firebaseToken = generateFirebaseToken(accounts.getId());
            return ResponseEntity.ok(convertToDto(token, firebaseToken, accounts));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @GetMapping("/check-avatar")
    @CrossOrigin
    public ResponseEntity<?> checkAvatar(@RequestParam("username") String username) {
        Accounts accounts = accService.getByUserName(username);
        if (accounts == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        boolean hasAvatar = accounts.getAvatar() != null && !accounts.getAvatar().isEmpty();
        return ResponseEntity.ok(hasAvatar);
    }
    @PostMapping("/upload-avatar")
    @CrossOrigin
    public ResponseEntity<?> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
        Accounts accounts = accService.getByUserName(username);
        if (accounts == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        try {
            userService.uploadAvatar(accounts, file);
            return ResponseEntity.ok("Avatar uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        }
    }
    private String generateFirebaseToken(Integer userId) {
        try {
            String firebaseToken = FirebaseAuth.getInstance().createCustomToken(userId.toString());
            return firebaseToken;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Error creating firebase token";
        }
    }

    @GetMapping("/users/get-chat-users")
    public ResponseEntity<List<ChatUserInfoDto>> getAllUsers(@RequestParam(name = "ids") List<Integer> ids) {
        List<Accounts> users = accService.getAccountsByIds(ids);
        users.forEach(user -> Hibernate.initialize(user.getCustomerCollection())); // Initialize the collection
        return ResponseEntity.ok(entity2Dto(users));    }

    private List<ChatUserInfoDto> entity2Dto(List<Accounts> users) {
        return users.stream()
                .map(e -> {
                    ChatUserInfoDto dto = modelMapper.map(e, ChatUserInfoDto.class);
                    dto.setAvatar(e.getAvatar());
                    String customerName = e.getCustomerCollection().stream()
                            .findFirst()
                            .map(Customer::getName)
                            .orElse("No Customer");
                    dto.setFullName(customerName);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private UserLoginResponseDto convertToDto(String token, String firebaseToken, Accounts accounts) {
        UserLoginResponseDto ulrd = new UserLoginResponseDto();
        ulrd.setToken(token);
        ulrd.setFirebaseToken(firebaseToken);
        ulrd.setId(accounts.getId());
        if (accounts.getCustomerCollection().isEmpty()) {
            ulrd.setFullName("No Customer");
        } else {
            ulrd.setFullName(accounts.getCustomerCollection().stream().findFirst().get().getName());
        }
        ulrd.setRole(accounts.getRole());
        ulrd.setAvatar(accounts.getAvatar());
        return ulrd;
    }
}
