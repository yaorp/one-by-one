package com.springcloud.ms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class AuthUser {
    private String id;
    private String username;
    private String password;
}
