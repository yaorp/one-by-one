package com.springcloud.ms.controller.redis.idincrease;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 使用redis实现分布式ID自增
 * @author: yaorp
 */
@Component
public class DistributeIdIncrease {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    public String generateIdByToday(String key, Integer length){
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long num = redisAtomicLong.incrementAndGet();

        redisAtomicLong.expireAt(getTodayEndTime());
        String id = getToday() + String.format("%0" + length + "d", num);
        return id;
    }

    private static Date getTodayEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return calendar.getTime();
    }

    public static String getToday(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

}
