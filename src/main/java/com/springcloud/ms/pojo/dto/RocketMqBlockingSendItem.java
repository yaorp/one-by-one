package com.springcloud.ms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketMqBlockingSendItem {

    private Integer sequence;

    private String msgId;

    private String sendStatus;

    private Integer queueId;
}
