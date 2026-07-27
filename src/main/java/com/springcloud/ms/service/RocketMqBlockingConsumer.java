package com.springcloud.ms.service;

import com.alibaba.fastjson.JSON;
import com.springcloud.ms.pojo.dto.RocketMqBlockingMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "${rocketmq.demo.blocking-topic:one-by-one-blocking-demo-topic}",
        consumerGroup = "${rocketmq.demo.blocking-consumer-group:one-by-one-blocking-demo-consumer-group}",
        selectorExpression = "${rocketmq.demo.blocking-tag:blocking}",
        consumeMode = ConsumeMode.ORDERLY
)
public class RocketMqBlockingConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        RocketMqBlockingMessage blockingMessage = JSON.parseObject(message, RocketMqBlockingMessage.class);
        log.info(
                "RocketMQ blocking demo start, traceId={}, sequence={}, sleepSeconds={}, time={}",
                blockingMessage.getTraceId(),
                blockingMessage.getSequence(),
                blockingMessage.getSleepSeconds(),
                LocalDateTime.now()
        );
        sleep(blockingMessage.getSleepSeconds());
        log.info(
                "RocketMQ blocking demo end, traceId={}, sequence={}, time={}",
                blockingMessage.getTraceId(),
                blockingMessage.getSequence(),
                LocalDateTime.now()
        );
    }

    private void sleep(Integer sleepSeconds) {
        if (sleepSeconds == null || sleepSeconds <= 0) {
            return;
        }
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("RocketMQ blocking demo interrupted", e);
        }
    }
}
