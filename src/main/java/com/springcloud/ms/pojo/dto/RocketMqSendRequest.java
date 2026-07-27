package com.springcloud.ms.pojo.dto;

import lombok.Data;

@Data
public class RocketMqSendRequest {

    private String topic;

    private String tag;

    private String content;
}
