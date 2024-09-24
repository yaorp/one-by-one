package com.springcloud.ms.controller.algorithm;

/**
 * 2 的幂
 * @author: yaorp
 */
public class IsPowerOfTwo {
    public boolean isPowerOfTwo1(int n) {
        return n>0 && ((n&(n-1))==0);
    }
}
