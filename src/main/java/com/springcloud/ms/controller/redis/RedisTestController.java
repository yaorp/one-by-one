package com.springcloud.ms.controller.redis;

import com.springcloud.ms.controller.redis.idincrease.DistributeIdIncrease;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: yaorp
 */
@RestController
@RequestMapping("redis/")
public class RedisTestController {

    @Resource
    private DistributeIdIncrease distributeIdIncrease;

    @GetMapping("/getIncreaseId")
    public String getIncreaseId(){
        String key = "INCREASE_ID";
        return distributeIdIncrease.generateIdByToday(key,10);
    }
}
