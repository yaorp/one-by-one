package com.springcloud.ms.controller.algorithm;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;

/**
 * 移动0
 * @author: yaorp
 */
public class MoveZero {

    /**
     * 10012
     * 0移动到最后面
     * 原顺序不变
     */
    public static void moveZero(int[] a){
        int j=0;

        // 先移动非0
        for (int i=0; i<a.length; i++){
            if (a[i] !=0){
                a[j++]= a[i];
            }
        }

        // 再移动0
        for (int i=j;i<a.length; i++){
            a[i] =0;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,0,0,1,2};
        moveZero(a);
        System.out.println(Arrays.toString(a));
    }
}
