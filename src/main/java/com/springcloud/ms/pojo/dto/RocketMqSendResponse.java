package com.springcloud.ms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketMqSendResponse {

    private String msgId;

    private String sendStatus;

    private String topic;

    private String tag;

    private Integer queueId;
}
