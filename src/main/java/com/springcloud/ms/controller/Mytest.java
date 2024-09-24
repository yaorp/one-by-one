package com.springcloud.ms.controller;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: yaorp
 */
public class Mytest {

    public static void main(String[] args) {
//        String s = new String("a"+"b");
        String s = "a"+"b";
        System.out.printf(s);
    }

    public boolean isPowerOfTwo(int n) {
        return n>0 && ((n&(n-1))==0);
    }

}
