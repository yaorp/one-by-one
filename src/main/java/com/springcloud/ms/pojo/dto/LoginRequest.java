package com.springcloud.ms.pojo.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
