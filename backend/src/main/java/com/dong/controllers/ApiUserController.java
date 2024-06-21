package com.dong.controllers;


import com.dong.DTO.ChatUserInfoDto;
import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.service.AccountsService;
import com.dong.service.CustomerService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class ApiUserController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountsService accService;
    @GetMapping("/get-chat-users")
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
}
