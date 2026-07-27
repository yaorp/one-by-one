package com.springcloud.ms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rocketmq.demo")
public class RocketMqDemoProperties {

    private String topic = "one-by-one-demo-topic";

    private String consumerGroup = "one-by-one-demo-consumer-group";

    private String tag = "demo";

    private String blockingTopic = "one-by-one-blocking-demo-topic";

    private String blockingConsumerGroup = "one-by-one-blocking-demo-consumer-group";

    private String blockingTag = "blocking";

    private String blockingShardingKey = "blocking-demo";
}
