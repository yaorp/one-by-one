package com.springcloud.ms.controller.algorithm;

/**
 * @author: yaorp
 */
public class ShellSort {
    public static void shellSort(int []arr){
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                int j=i;
                int temp =arr[i];
                if (arr[j] < arr[j-gap]) {
                    while(j-gap>=0 && temp<arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j]= temp;
                }
            }
        }
    }
}
