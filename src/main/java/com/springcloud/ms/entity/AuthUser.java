package com.springcloud.ms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("auth_user")
public class AuthUser {

    /**
     * 主键，插入时由 MyBatis-Plus 雪花算法（ASSIGN_ID）生成
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
}
