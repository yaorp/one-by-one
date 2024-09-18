package com.springcloud.ms.controller.algorithm;

/**
 * 爬楼梯问题
 * 一次爬一节，或者一次爬两节
 * @author: yaorp
 */
public class ClimbStairs {


    /**
     * 滚动数组思想 后面的一位的值是前面两位的和，然后把值往前移动
     * @param n
     * @return
     */
    public int climbStairs(int n){
        int q=0,p=0,s=1;
        for (int i=1;i<=n;i++){
            q=p;
            p=s;
            s=q+p;
        }
        return s;
    }

    /**
     * 最后一步要么是怕一节，要么是爬两节，也就是一阶过来方法数+两节过来方法数
     * f(n)=f(n-1)+f(n-2)
     */
    public int climbStairs2(int n){
        int maxType = 0;
        if (n==1){
            return 1;
        }

        if (n==2){
            return 2;
        }

//        for (int i=3;i<;i)
        maxType = climbStairs(n-1)+ climbStairs(n-2);
        return maxType;
    }

}
