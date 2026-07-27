package com.springcloud.ms.service;

import com.alibaba.fastjson.JSON;
import com.springcloud.ms.config.RocketMqDemoProperties;
import com.springcloud.ms.pojo.dto.RocketMqBlockingMessage;
import com.springcloud.ms.pojo.dto.RocketMqBlockingRequest;
import com.springcloud.ms.pojo.dto.RocketMqBlockingSendItem;
import com.springcloud.ms.pojo.dto.RocketMqBlockingSendResponse;
import com.springcloud.ms.pojo.dto.RocketMqSendRequest;
import com.springcloud.ms.pojo.dto.RocketMqSendResponse;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RocketMqProducerService {

    private static final int DEFAULT_BLOCKING_MESSAGE_COUNT = 3;

    private static final int DEFAULT_BLOCKING_SLEEP_SECONDS = 5;

    private static final int MAX_BLOCKING_MESSAGE_COUNT = 20;

    private static final int MAX_BLOCKING_SLEEP_SECONDS = 60;

    private final RocketMQTemplate rocketMQTemplate;

    private final RocketMqDemoProperties properties;

    public RocketMqSendResponse send(RocketMqSendRequest request) {
        if (request == null || !StringUtils.hasText(request.getContent())) {
            throw new IllegalArgumentException("Message content cannot be blank");
        }

        String topic = defaultIfBlank(request.getTopic(), properties.getTopic());
        String tag = defaultIfBlank(request.getTag(), properties.getTag());
        if (!StringUtils.hasText(topic)) {
            throw new IllegalArgumentException("RocketMQ topic cannot be blank");
        }

        SendResult sendResult = rocketMQTemplate.syncSend(buildDestination(topic, tag), request.getContent());
        MessageQueue messageQueue = sendResult.getMessageQueue();
        Integer queueId = messageQueue == null ? null : messageQueue.getQueueId();
        String sendStatus = sendResult.getSendStatus() == null ? null : sendResult.getSendStatus().name();
        return new RocketMqSendResponse(sendResult.getMsgId(), sendStatus, topic, normalizeTag(tag), queueId);
    }

    public RocketMqBlockingSendResponse sendBlockingDemo(RocketMqBlockingRequest request) {
        RocketMqBlockingRequest safeRequest = request == null ? new RocketMqBlockingRequest() : request;
        int count = normalizeRange(safeRequest.getCount(), DEFAULT_BLOCKING_MESSAGE_COUNT, 1, MAX_BLOCKING_MESSAGE_COUNT);
        int sleepSeconds = normalizeRange(
                safeRequest.getSleepSeconds(),
                DEFAULT_BLOCKING_SLEEP_SECONDS,
                0,
                MAX_BLOCKING_SLEEP_SECONDS
        );
        String content = defaultIfBlank(safeRequest.getContent(), "blocking demo");
        String shardingKey = defaultIfBlank(safeRequest.getShardingKey(), properties.getBlockingShardingKey());
        String topic = properties.getBlockingTopic();
        String tag = properties.getBlockingTag();
        String destination = buildDestination(topic, tag);
        String traceId = UUID.randomUUID().toString();
        List<RocketMqBlockingSendItem> sendItems = new ArrayList<>(count);

        for (int i = 1; i <= count; i++) {
            RocketMqBlockingMessage message = new RocketMqBlockingMessage(traceId, i, sleepSeconds, content);
            SendResult sendResult = rocketMQTemplate.syncSendOrderly(destination, JSON.toJSONString(message), shardingKey);
            sendItems.add(toBlockingSendItem(i, sendResult));
        }

        return new RocketMqBlockingSendResponse(traceId, topic, normalizeTag(tag), shardingKey, sleepSeconds, sendItems);
    }

    private String defaultIfBlank(String value, String defaultValue) {
        return StringUtils.hasText(value) ? value : defaultValue;
    }

    private int normalizeRange(Integer value, int defaultValue, int min, int max) {
        int current = value == null ? defaultValue : value;
        if (current < min || current > max) {
            throw new IllegalArgumentException("Value must be between " + min + " and " + max);
        }
        return current;
    }

    private RocketMqBlockingSendItem toBlockingSendItem(Integer sequence, SendResult sendResult) {
        MessageQueue messageQueue = sendResult.getMessageQueue();
        Integer queueId = messageQueue == null ? null : messageQueue.getQueueId();
        String sendStatus = sendResult.getSendStatus() == null ? null : sendResult.getSendStatus().name();
        return new RocketMqBlockingSendItem(sequence, sendResult.getMsgId(), sendStatus, queueId);
    }

    private String buildDestination(String topic, String tag) {
        if (StringUtils.hasText(tag) && !"*".equals(tag)) {
            return topic + ":" + tag;
        }
        return topic;
    }

    private String normalizeTag(String tag) {
        return StringUtils.hasText(tag) ? tag : "*";
    }
}
