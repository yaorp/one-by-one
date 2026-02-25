package com.springcloud.ms.controller.pattern;

import lombok.Data;

@Data
public class R<T> {
    private String code;
    private String message;
    private T data;

    public R(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> R<T> success(T data) {
        return new R<>("200", "Success", data);
    }
}
