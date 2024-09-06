package com.springcloud.ms.controller.algorithm;

import java.util.*;

/**
 * 三数之和等于0
 * @author: yaorp
 */
public class ThreeNumSum {

    public static List<List<Integer>> threeNumSum(int[] a){

        Set<List<Integer>> bl = new HashSet<>();
        Arrays.sort(a); 

        for (int i = 0; i < a.length-2; i++) {
            for (int j=i+1; j<a.length-1; j++){
                for (int k=j+1; k<a.length; k++){
                    if (a[i]+a[j]+a[k] ==0) {
                        List<Integer> b = new ArrayList<>();
                        b.add(a[i]);
                        b.add(a[j]);
                        b.add(a[k]);
                        bl.add(b);

                        bl.add(Arrays.asList(a[i],a[j],a[k]));
                    }
                }
            }
        }

        return new ArrayList<>(bl);
    }

    // 先对数组进行排序
    // 然后从左边开始
    public static List<List<Integer>> threeNumSum2(int[] a){
        Arrays.sort(a);
        int t = 0;

        Set<List<Integer>> tl = new HashSet<>();

        for (int i=0; i<a.length; i++){
            int j=i+1,k=a.length-1;
            while (j<k){
                int sum = a[i]+a[j]+a[k];
               if (t== sum){
                   tl.add(Arrays.asList(a[i],a[j],a[k]));
                   j++;
                   k--;
               }else if (t< sum){
                   k--;
               }else {
                   j++;
               }
            }
        }
        return new ArrayList<>(tl);
    }
}
