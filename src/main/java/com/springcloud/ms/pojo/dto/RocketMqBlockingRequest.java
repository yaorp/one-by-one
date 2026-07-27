package com.springcloud.ms.pojo.dto;

import lombok.Data;

@Data
public class RocketMqBlockingRequest {

    private String content;

    private Integer count;

    private Integer sleepSeconds;

    private String shardingKey;
}
