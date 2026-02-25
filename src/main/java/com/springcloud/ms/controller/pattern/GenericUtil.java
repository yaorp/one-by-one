package com.springcloud.ms.controller.pattern;

public class GenericUtil {

    public static <T> T getFirst(T[] arr) {
        return arr[0];
    }
}