package com.springcloud.ms.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "${rocketmq.demo.topic:one-by-one-demo-topic}",
        consumerGroup = "${rocketmq.demo.consumer-group:one-by-one-demo-consumer-group}",
        selectorExpression = "${rocketmq.demo.tag:*}"
)
public class RocketMqDemoConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("RocketMQ demo consumer received message: {}", message);
    }
}
