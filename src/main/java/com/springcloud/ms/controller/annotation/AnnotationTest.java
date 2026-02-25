package com.springcloud.ms.controller.annotation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yaorp
 */
@RestController
@Slf4j
@AllArgsConstructor
public class AnnotationTest {

        @MethodTime
        @GetMapping("/test")
        public String test(@RequestParam String name) {
            log.info("test");
            return "hello annotation";
        }

        public static void main(String[] args) {
            AnnotationTest annotationTest = new AnnotationTest();
            annotationTest.test("yaorp");
        }
}
