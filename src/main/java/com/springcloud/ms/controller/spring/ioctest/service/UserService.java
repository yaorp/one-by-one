package com.springcloud.ms.controller.spring.ioctest.service;


import com.springcloud.ms.controller.spring.ioctest.po.User;

import java.util.List;
import java.util.Map;

public interface UserService {
	List<User> queryUsers(Map<String, Object> param);
}
