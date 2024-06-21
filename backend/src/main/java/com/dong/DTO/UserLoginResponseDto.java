package com.dong.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDto {
    private String token;
    private String firebaseToken;
    private Integer id;
    private String fullName;
    private String role;
    private String avatar;
    private String status;
}
