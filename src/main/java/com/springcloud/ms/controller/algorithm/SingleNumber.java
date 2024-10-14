package com.springcloud.ms.controller.algorithm;

import java.util.Arrays;

/**
 * 只出现一次的数字
 * https://leetcode.cn/problems/single-number/description/
 * @author: yaorp
 */
public class SingleNumber {
    /**
     * 只出现过一次
     * @param a
     * @return
     */
    public int onlyOne(int[] a){
        // 升序
        Arrays.sort(a);
        for (int i=0; i<a.length-1; i=i+2){
            if (a[i] !=a[i+1]) {
                return a[i];
            }
        }
        return a[a.length-1];
    }

    /**
     * ^异或 同值取0，异值取1
     * &与运算 同值取1，异值取0
     * @param a
     * @return
     */
    public int onlyOne2(int[] a){
        int x = 0;
        for (int i : a){
            x=x ^ i;
        }
        return x;
    }
}
