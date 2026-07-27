package com.springcloud.ms.controller.rocketmq;

import com.springcloud.ms.controller.pattern.R;
import com.springcloud.ms.pojo.dto.RocketMqBlockingRequest;
import com.springcloud.ms.pojo.dto.RocketMqBlockingSendResponse;
import com.springcloud.ms.pojo.dto.RocketMqSendRequest;
import com.springcloud.ms.pojo.dto.RocketMqSendResponse;
import com.springcloud.ms.service.RocketMqProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rocketmq")
@AllArgsConstructor
public class RocketMqController {

    private final RocketMqProducerService rocketMqProducerService;

    @PostMapping("/send")
    public R<RocketMqSendResponse> send(@RequestBody RocketMqSendRequest request) {
        try {
            return R.success(rocketMqProducerService.send(request));
        } catch (IllegalArgumentException e) {
            return R.fail("400", e.getMessage());
        } catch (Exception e) {
            log.error("RocketMQ send failed", e);
            return R.fail("500", "RocketMQ send failed: " + e.getMessage());
        }
    }

    @PostMapping("/blocking/send")
    public R<RocketMqBlockingSendResponse> sendBlockingDemo(@RequestBody(required = false) RocketMqBlockingRequest request) {
        try {
            return R.success(rocketMqProducerService.sendBlockingDemo(request));
        } catch (IllegalArgumentException e) {
            return R.fail("400", e.getMessage());
        } catch (Exception e) {
            log.error("RocketMQ blocking demo send failed", e);
            return R.fail("500", "RocketMQ blocking demo send failed: " + e.getMessage());
        }
    }
}
