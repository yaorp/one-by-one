package com.springcloud.ms.controller.multithreading;

/**
 * ThreadLocal 实例
 * @author: yaorp
 */
public class DemoThreadLocal {
    private static final ThreadLocal<String> tlS = new ThreadLocal<>();
    public static void main(String[] args) {
        ThreadLocalDemo tld = new ThreadLocalDemo(tlS);

        tlS.set("0");
        for (int i=1;i<3;i++){
            new Thread(tld,"Thread-"+i).start();
        }
        System.out.println("0:"+tlS.get());
    }
}

class ThreadLocalDemo implements Runnable{
    private ThreadLocal<String> tlS = new ThreadLocal<>();

    public ThreadLocalDemo(ThreadLocal<String> tlS){
        this.tlS = tlS;
    }

    @Override
    public void run() {
        tlS.set(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+",tlS:"+tlS.get());
    }
}
