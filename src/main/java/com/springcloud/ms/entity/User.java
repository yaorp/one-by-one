package com.springcloud.ms.entity;

import lombok.Data;

/**
 * @author: yaorp
 */
@Data
public class User {

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
