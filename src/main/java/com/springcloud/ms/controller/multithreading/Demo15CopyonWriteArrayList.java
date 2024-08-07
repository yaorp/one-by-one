package com.springcloud.ms.controller.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yaorp
 */
public class Demo15CopyonWriteArrayList {

    public static void main(String[] args) {
        List<Integer> tempList = Arrays.asList(new Integer[] {1,2});
        CopyOnWriteArrayList<Integer> cl = new CopyOnWriteArrayList<>(tempList);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadDemo(cl));
        executorService.execute(new WriteDemo(cl));
        executorService.execute(new WriteDemo(cl));
        executorService.execute(new WriteDemo(cl));
        executorService.execute(new ReadDemo(cl));
        executorService.execute(new WriteDemo(cl));
        executorService.execute(new ReadDemo(cl));
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("cl.size()="+cl.size());
        executorService.shutdown();
    }

    static class ReadDemo implements Runnable{
        private List<Integer> li;

        public ReadDemo(List<Integer> li){
            this.li = li;
        }

        @Override
        public void run(){
            System.out.print("size:="+li.size()+",::");
            for (Integer i: li) {
                System.out.print(i+",");
            }
            System.out.println();
        }
    }

    static class WriteDemo implements Runnable{

        private List<Integer> li;

        public WriteDemo(List<Integer> li) {
            this.li = li;
        }

        @Override
        public void run(){
            this.li.add(3);
        }
    }
}
