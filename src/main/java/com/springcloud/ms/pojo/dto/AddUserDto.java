package com.springcloud.ms.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserDto {
    /**
     * 主键
     */
    private String id;
    /**
     * 姓名
     */
    private String username;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 性别：m-男，w-女
     */
    private String sex;
    /**
     * 地址
     */
    private String address;
}
