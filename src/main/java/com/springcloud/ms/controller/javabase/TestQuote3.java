package com.springcloud.ms.controller.javabase;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yaorp
 */
@RequestMapping
public class TestQuote3 {
    public static void main(String[] args) {
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        System.out.println(sb.length());
    }
}
