package com.springcloud.ms.controller;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: yaorp
 */
public class Mytest {

    public static void main(String[] args) {
//        System.out.println(Math.round(-1.4));
//        loor(-2.51+0.5) = floo(-2.01) = -3
//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        int[] a = new int[]{-1,3,-2,5,-4};
        bubblingSort(a);
        System.out.printf(Arrays.toString(a));

    }

//    将包含正负数的数组按绝对值进行从小到大排序
    // 相当于是排序，可以用冒泡排序
    // 前后两个比较，大的往前移动
    // 或者三目运算符 a<0?-a:a
    public static void bubblingSort(int[] a) {
        for (int i=0; i<a.length;i++){
            for (int j=0; j<a.length-1; j++){
                if (Math.abs(a[j])>Math.abs(a[j+1])){
                    int tmp = a[j];
                    a[j]= a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }
}
