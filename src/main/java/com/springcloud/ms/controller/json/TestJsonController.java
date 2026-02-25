package com.springcloud.ms.controller.json;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: yaorp
 */
@AllArgsConstructor
@Slf4j
@RestController
public class TestJsonController {

    public static void main(String[] args) {
        String aaa = "{\"name\":\"Tom\",\"age\":18}";
        String bbb = "\"{\\\"name\\\":\\\"Tom\\\",\\\"age\\\":18}\"";
        String aaaReal = isEscapedJsonDeal(aaa);  // 去掉外层转义
        String bbbReal = isEscapedJsonDeal(bbb);  // 去掉外层转义
        log.info("aaaReal: {}", aaaReal);
        log.info("bbbReal: {}", bbbReal);
    }

    public static String isEscapedJsonDeal(String str) {
        boolean json = str.startsWith("\"") && str.endsWith("\"") && str.contains("\\\"");
        if (json) {
            return JSON.parse(str).toString();
        }else {
            return str;
        }
    }
}
