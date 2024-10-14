package com.springcloud.ms.controller.algorithm;

/**
 * 各位相加
 * @author: yaorp
 */
public class AddDigits {

    /**
     * 记公式就行了，我也没看懂
     * @param n
     * @return
     */
    public int addDigits(int n) {
        return (n-1)%9+1;
    }
}
