package com.springcloud.ms.service;

import com.springcloud.ms.entity.User;
import com.springcloud.ms.pojo.dto.AddUserDto;
import com.springcloud.ms.pojo.dto.UpdateUserDto;

import java.util.List;

/**
 * @author yaorp
 */
public interface IUserService {
    User qryUserById(String id);
    boolean addOne(AddUserDto user);
    boolean update(UpdateUserDto user);
    boolean delete(String id);
    List<User> listAll();
    // 分页

}
