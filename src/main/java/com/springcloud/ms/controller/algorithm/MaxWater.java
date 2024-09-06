package com.springcloud.ms.controller.algorithm;

/**
 * 能盛最多的水
 * @author: yaorp
 */
public class MaxWater {

    public static void main(String[] args) {
        int[] a = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxWater(a));

    }

    // 左右边间向中间汇合
    // 往小的那边汇合
    // 宽开始是最大的，然后再修改一个参数进行比较
    public static int maxWater(int[] a){
        int maxArea = 0;
        for (int i=0, j=a.length-1; i<j;){
            int minHeight = a[i] < a[j] ? a[i++] : a[j--];
            // 上面j-- i++了，所以要+1
            int area = (j-i+1)*minHeight;
            maxArea = Math.max(maxArea,area);
        }

        return maxArea;
    }
}
