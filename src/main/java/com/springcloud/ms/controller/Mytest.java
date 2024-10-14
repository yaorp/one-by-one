package com.springcloud.ms.controller;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: yaorp
 */
public class Mytest {

    public static void main(String[] args) {
//        String s = new String("a"+"b");
//        String s = "Ab5cECbad123";
//        System.out.printf(String.valueOf(symmetry(s)));

        int[] a = new int[]{4,2,1,5,-6,3};
        sorRang(a);
        System.out.println(Arrays.toString(a));

    }

    /**
     * 输入一个字符串，将这个字符串中的非字母去除后，不考虑大小写的情况下判断这个字符串是否是前后对称的（AbcECba）
     * @return
     */
    public static boolean symmetry(String a){
        // 判断字符是否是数字
//        Character.isDefined('');
        String s = a.replaceAll("\\d", "").toUpperCase();
        char[] arr= s.toCharArray();
        int i=0,j=arr.length-1;
        while (i<j){
            if (arr[i]==arr[j]){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }

//    升序排列
    public static void sorRang(int[] a){
        // 冒泡排序
        // 每个数比较一次 最外层次数
        // 内层每两个绝对值比较
        for (int i=0; i<a.length;i++){
            for (int j=0; j<a.length-1; j++){
                if (Math.abs(a[j+1])<Math.abs(a[j])){
                    // 交换位置
                    int tmp = a[j+1];
                    a[j+1] = a[j];
                    a[j]= tmp;
                }
            }
        }

    }


}
