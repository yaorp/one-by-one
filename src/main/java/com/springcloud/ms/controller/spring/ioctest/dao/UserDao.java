package com.springcloud.ms.controller.spring.ioctest.dao;

import com.springcloud.ms.controller.spring.ioctest.po.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

	List<User> queryUserList(Map<String, Object> param);
}
