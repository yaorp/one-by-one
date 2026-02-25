package com.springcloud.ms.controller.redis;

import com.springcloud.ms.controller.entity.Blog;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: yaorp
 */
@RestController
@RequestMapping("/blog")
public class RedisBaseCon {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/distributeLock")
    public String distributeLock(){
        String lockName = "distributeLock_key";
        RLock lock = redissonClient.getLock(lockName);
        String message ="";
//        lock.tryLock(10,20, TimeUnit.SECONDS);
        boolean lockR=false;
        try {
            lockR = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("获取锁异常："+e);
            throw new RuntimeException(e);
        }

        if (lockR){
            try {
                message = "获取锁成功";
                System.out.println("获取锁成功");
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
                System.out.println("释放锁成功");
            }
        }else {
            message = "获取锁失败";
            System.out.println("获取锁失败");
        }

        return message;
    }

    @GetMapping("/signCount")
    public Long signCount(String userId){
        LocalDateTime now = LocalDateTime.now();
        String key = "sign:"+userId+":"+ now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        List<Long> longs = stringRedisTemplate.opsForValue().bitField(key, BitFieldSubCommands.create()
                .get(BitFieldSubCommands.BitFieldType.unsigned(now.getDayOfMonth()))
                .valueAt(0));

        if (CollectionUtils.isEmpty(longs)) {
            return 0L;
        }

        Long num = longs.get(0);
        Long count = 0L;
        while (true){
            if (((num&1)==0)){
                break;
            }else {
                count++;
            }
            num >>>=1;
        }

        return count;
    }

    /**
     * setbit sign:1001:202409 0 1
     * getbit sign:1001:202409 0
     * @param userId
     * @return
     */
    @GetMapping("/sign")
    public String sign(String userId){
        LocalDateTime now = LocalDateTime.now();
        String key = "sign:"+userId+":"+ now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        stringRedisTemplate.opsForValue().setBit(key,now.getDayOfMonth()-1,true);
        return "SUCCESS";
    }

    /**
     * 存储上海医院的经纬度信息
     * @return
     */
    @GetMapping("addRedius")
    private String addRedius(String longitude, String latitude,String name) {
        String baiduKey = "hospital:shanghai";

        stringRedisTemplate.opsForGeo().add(baiduKey,
                new Point(Double.parseDouble(longitude), Double.parseDouble(latitude)),name);

        return "SUCCESS";
    }

    // 获取附近的医院
    @GetMapping("/getRadius")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> radius(double x, double y) {
//        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().limit(5).includeDistance();
//        GeoResults<RedisGeoCommands.GeoLocation<String>> results = stringRedisTemplate.opsForGeo().radius("hospital:shanghai", new Circle(new Point(x, y), new Distance(100, Metrics.KILOMETERS)), args);
//        if (results != null) {
//            return results.getContent();
//        }
//        return null;

        String baiduKey = "hospital:shanghai";

        //设置当前位置
        // // Point 中 x：经度"longitude":114.56，y：纬度"latitude":38.13
        Point point = new Point(x, y);
        //设置半径范围 (KILOMETERS 千米；METERS 米)
        Metric metric = RedisGeoCommands.DistanceUnit.KILOMETERS;
        Distance distance = new Distance(3, metric);
        Circle circle = new Circle(point, distance);
        //设置参数 包括距离、坐标、条数
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()//包含距离
                .includeCoordinates();//包含经纬度
//                .sortAscending()//正序排序
//                .limit(50); //条数
        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = stringRedisTemplate.opsForGeo().radius(baiduKey, circle, geoRadiusCommandArgs);

        if (radius != null) {
            Iterator<GeoResult<RedisGeoCommands.GeoLocation<String>>> iterator = radius.iterator();
            while (iterator.hasNext()) {
                GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult = iterator.next();
                // 与目标点相距的距离信息
                Distance geoResultDistance = geoResult.getDistance();
                // 该点的信息
                RedisGeoCommands.GeoLocation<String> geoResultContent = geoResult.getContent();
            }

        }
        return radius;
    }

    @GetMapping("/likeBlog")
    public String likeBlog(String blogId, String userId) {
        String key = "blog:" + blogId;
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        if (score != null) {
            stringRedisTemplate.opsForZSet().remove(key, userId);
        } else {
            stringRedisTemplate.opsForZSet().add(key, userId, System.currentTimeMillis());
        }


        return "SUCCESS";
    }

    @GetMapping("getBlog")
    public Blog getBlog(String blogId, String userId) {
        String key = "blog:" + blogId;
        Blog blog = new Blog();
        blog.setIsLike(Objects.nonNull(stringRedisTemplate.opsForZSet().score(key, userId)));
        blog.setLikeUsers(stringRedisTemplate.opsForZSet().range(key, 0, 2));
        return blog;
    }
}
