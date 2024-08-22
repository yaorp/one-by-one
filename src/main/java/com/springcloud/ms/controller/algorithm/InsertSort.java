package com.springcloud.ms.controller.algorithm;

import java.util.Arrays;

/**
 * 直接插入排序
 * 稳定
 * @author: yaorp
 */
public class InsertSort {
    public static void insertSort(int[] a) {
        int i, j, insertNote;// 要插入的数据
        for (i = 1; i < a.length; i++) {// 从数组的第二个元素开始循环将数组中的元素插入
            insertNote = a[i];// 设置数组中的第2个元素为第一次循环要插入的数据
            j = i - 1;
            while (j >= 0 && insertNote < a[j]) {
                a[j + 1] = a[j];// 如果要插入的元素小于第j个元素,就将第j个元素向后移动
                j--;
            }
            a[j + 1] = insertNote;// 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
        }
    }

    public static void insertSort2(int[] a){
        // 前面是有序的，后面的一位和前面的比较，比当前数大的话交换位置
        for (int i=1;i<a.length;i++){
            int j,x;
            // 左侧值
            j=i;
            x = a[i];
            while (j>0 && a[j-1]>x){
                a[j] = a[j-1];
                j--;
            }
            a[j] = x;
        }
    }

    public static void main(String[] args) {
        int a[] = { 1,3,7,2,6,4,5 };

        insertSort(a);
        System.out.println(Arrays.toString(a) );
    }
}
