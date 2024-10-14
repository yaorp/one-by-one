package com.springcloud.ms.controller.encipher.md5;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Md5加解密测试类
 * @author: yaorp
 */
public class DemoMd5 {

    /**
     * 借助apache工具类DigestUtils实现
     * @param str 待加密字符串
     * @return 16进制加密字符串
     */
    public static String encryptToMD5(String str){
        return DigestUtils.md5Hex(str);
    }
}
