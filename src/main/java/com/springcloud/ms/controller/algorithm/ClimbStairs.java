package com.springcloud.ms.controller.algorithm;

/**
 * 爬楼梯问题
 * 一次爬一节，或者一次爬两节
 * @author: yaorp
 */
public class ClimbStairs {



    public int climbStairs(int n){
        int q=0,p=0,s=1;
        for (int i=1;i<=n;i++){
            q=p;
            p=s;
            s=q+p;
        }
        return s;
    }
}
