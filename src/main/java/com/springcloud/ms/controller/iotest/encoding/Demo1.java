package com.springcloud.ms.controller.iotest.encoding;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author: yaorp
 */
public class Demo1 {
    @Test
    public void gbkTransferUtf8() throws Exception {
        String test="测试";
        String gbkS = new String(test.getBytes(StandardCharsets.UTF_8),"gbk");
        System.out.println(gbkS);
        String utf8S = new String(gbkS.getBytes("gbk"), StandardCharsets.UTF_8);
        System.out.println(utf8S);
    }

    @Test
    public void gbkTransferUtf82() throws Exception {
        String test="测试";
        String gbkS = new String(test.getBytes(StandardCharsets.UTF_8),"gbk");
        System.out.println(gbkS);
        String utf8S = new String(gbkS.getBytes("gbk"), StandardCharsets.UTF_8);
        System.out.println(utf8S);

        byte[] src=test.getBytes("GBK");
//        byte[] gbks = new String(src, "GBK").getBytes("UTF-8");
        String s = new String(src, "GBK");
        System.out.println(new String(s));

        try {
            Thread.interrupted();
        } finally {

        }
    }
}
