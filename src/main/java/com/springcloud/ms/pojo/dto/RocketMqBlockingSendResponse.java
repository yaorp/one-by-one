package com.springcloud.ms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketMqBlockingSendResponse {

    private String traceId;

    private String topic;

    private String tag;

    private String shardingKey;

    private Integer sleepSeconds;

    private List<RocketMqBlockingSendItem> messages;
}
