package com.springcloud.ms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.ms.entity.User;

import java.util.List;

/**
 * @author: yaorp
 */
public interface UserMapper extends BaseMapper<User> {
//    @Select("select * FROM user")
    List<User> qryUsers();
}
