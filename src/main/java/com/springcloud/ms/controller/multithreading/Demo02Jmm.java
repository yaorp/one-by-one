package com.springcloud.ms.controller.multithreading;

/**
 * @author: yaorp
 */
public class Demo02Jmm {
    public static void main(String[] args) throws InterruptedException {
        JmmDemo jmmDemo = new JmmDemo();
        Thread t1 = new Thread(jmmDemo);
        t1.start();
        Thread.sleep(100);
        jmmDemo.flag = false;
        System.out.println("flag 已经修改为 false");
        System.out.println(jmmDemo.flag);
    }

    static class JmmDemo implements Runnable {
        public boolean flag = true;

        @Override
        public void run() {
            System.out.println("子线程执行。。。。。");
            while (flag) {
                synchronized (this){
                    System.out.println("flag = " + flag);
                }
            }
            System.out.println("子线程结束。。。。。");
        }
    }
}




