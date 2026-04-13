package com.springcloud.ms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springcloud.ms.entity.User;
import com.springcloud.ms.mapper.UserMapper;
import com.springcloud.ms.pojo.dto.AddUserDto;
import com.springcloud.ms.pojo.dto.UpdateUserDto;
import com.springcloud.ms.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaorp
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User qryUserById(String id) {
//        return userMapper.selectById(id);
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId,id));
    }

    @Override
    public boolean addOne(AddUserDto userDto) {
        User user = BeanUtil.copyProperties(userDto, User.class);
        userMapper.insert(user);
        return true;
    }

    @Override
    public boolean update(UpdateUserDto user) {
        userMapper.updateById(BeanUtil.copyProperties(user,User.class));
        return true;
    }

    @Override
    public boolean delete(String id) {
        userMapper.deleteById(id);
        return false;
    }

    @Override
    public List<User> listAll() {
        return userMapper.selectList(null);
    }
}
