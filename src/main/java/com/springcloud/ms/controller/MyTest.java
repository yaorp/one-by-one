package com.springcloud.ms.controller;

import com.springcloud.ms.entity.User;
import com.springcloud.ms.mapper.UserMapper;
import io.lettuce.core.RedisClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yaorp
 */
@RestController
@RequestMapping("/myTest"  )
public class MyTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisClient redisClient;
    public static void main(String[] args) {
//        String s = new String("a"+"b");
//        String s = "Ab5cECbad123";
//        System.out.printf(String.valueOf(symmetry(s)));

//        List<String> list = Arrays.asList("abc", "bcde", "1234");
//        List<String> collect = list.stream().filter(item -> item.length() > 3).collect(Collectors.toList());
//        System.out.println(collect);

        Map<String, Object> a = new HashMap<>();
        List b = new ArrayList<>();
    }

    @GetMapping("/qryUsers"  )
    public List<User> qryUsers() {
        List<User> users = userMapper.qryUsers();
        return users;
    }
}
