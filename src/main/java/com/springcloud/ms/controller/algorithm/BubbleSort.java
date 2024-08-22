package com.springcloud.ms.controller.algorithm;

/**
 * 冒泡排序
 * 稳定
 * @author: yaorp
 */
public class BubbleSort {

    public static void main(String[] args) {
        int i=0;
//        int[] a= new int[]{1,2,3,7,6,4,5};
        int[] a= new int[]{1,2,4,3};

        System.out.printf("before sort:");
        for (i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }

        System.out.println();
        bubbleSort(a);


        System.out.printf("after  sort:");
        for (i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }


    }
    // 从左到右两个数之间比较，左边比右边大的话交换位置
    // 当本次排序没有位置发生变化，就结束
    public static void bubbleSort(int[] a){

        for (int i=0;i<a.length;i++){
            boolean isOver = true;
            for (int j=1;j<a.length-i;j++){
                if (a[j-1] >a[j]){
                    int tmp= a[j];
                    a[j] = a[j-1];
                    a[j-1] = tmp;
                    isOver= false;
                }
            }
            if (isOver) {
                System.out.println("i="+i);
                break;
            }

        }
    }
}
