package com.springcloud.ms.controller.springreflect;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: yaorp
 */
@RestController
public class ReflectDemo {
    public String hello() {

        try {
            Class<?> clazzType = Class.forName("aaa");

            // 通过无参的构造器
            Constructor constructor = clazzType.getConstructor();
            // 返回对象
            Object o = constructor.newInstance();

            // 另一种
            Object bean = clazzType.newInstance();
            Field field = clazzType.getField("属性名");
            // 暴力破解
            field.setAccessible(true);
            field.set(bean,"属性值");

            // 调用方法
            Method initMethod = clazzType.getMethod("initMethod");
            initMethod.invoke(bean);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Hello Reflect";
    }
}
