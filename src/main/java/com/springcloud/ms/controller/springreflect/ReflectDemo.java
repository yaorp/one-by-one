package com.springcloud.ms.controller.springreflect;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * @author: yaorp
 */
@RestController
public class ReflectDemo {
    public String hello() {

        try {
            Class<?> aClass = Class.forName("aaa");
            Object bean = aClass.newInstance();
            Field field = aClass.getField("属性名");
            field.set(bean,"属性值");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Hello Reflect";
    }
}
