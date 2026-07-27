package com.springcloud.ms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketMqBlockingMessage {

    private String traceId;

    private Integer sequence;

    private Integer sleepSeconds;

    private String content;
}
