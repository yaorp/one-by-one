package com.springcloud.ms.controller.algorithm;

/**
 * 找出只出现一次的数
 * @author: yaorp
 */
public class DemoOnce {

    /**
     * 暴力模式
     * @param a
     * @return
     */
    public static int qryOnceChar(int[] a){
        for (int i = 0; i < a.length-1; i++) {
            boolean isOver = true;
            int tmp = a[i];

            for (int j=i+1; j<a.length; j++){
                if (tmp == a[j]){
                    isOver = false;
                    i++;
                    break;
                }
            }
            if (isOver) {
                return tmp;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
//        nums=[1,1,2,3,3,4,4,8,8]
//        int[] a = new int[] {1,1,2,3,3,4,4,8,8};
        int[] a = new int[] {3,3,7,7,10,11,11};
        System.out.println(qryOnceChar2(a));
    }

    public static int qryOnceChar2(int[] a){
        for (int i=0; i<a.length-1;i=i+2){
            if (a[i] == a[i+1]){
                continue;
            }
            return a[i];
        }
        return 0;
    }
}
