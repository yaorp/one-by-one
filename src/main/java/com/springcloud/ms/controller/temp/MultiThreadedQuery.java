package com.springcloud.ms.controller.temp;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class MultiThreadedQuery {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个固定大小的线程池，最多执行5个查询
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            String finalI = i+"";
            Callable<String> query1 = () -> performQuery(finalI);
        }
        // 假设我们有5个查询
        Callable<String> query1 = () -> performQuery("Query1");
        Callable<String> query2 = () -> performQuery("Query2");
        Callable<String> query3 = () -> performQuery("Query3");
        Callable<String> query4 = () -> performQuery("Query4");
        Callable<String> query5 = () -> performQuery("Query5");

        // 将查询任务提交给线程池
        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(query1);
        tasks.add(query2);
        tasks.add(query3);
        tasks.add(query4);
        tasks.add(query5);

        // 使用 invokeAll 执行所有任务并返回结果
        List<Future<String>> results = executor.invokeAll(tasks);

        // 组合查询结果并处理异常
        StringBuilder combinedResults = new StringBuilder();
        for (Future<String> result : results) {
            try {
                // 获取每个任务的执行结果
                combinedResults.append(result.get()).append("\n");
            } catch (ExecutionException e) {
                // 处理任务执行期间抛出的异常
                System.err.println("Task failed with exception: " + e.getCause());
                combinedResults.append("A task failed: " + e.getCause().getMessage()).append("\n");
            } catch (InterruptedException e) {
                // 处理线程被中断的情况
                System.err.println("Thread was interrupted: " + e.getMessage());
                combinedResults.append("Task was interrupted").append("\n");
            }
        }

        // 打印最终的结果
        System.out.println("Combined Results:\n" + combinedResults.toString());

        // 关闭线程池
        executor.shutdown();
    }

    private static String performQuery(String query) throws Exception {
        // 模拟查询操作，实际可以是数据库查询、HTTP请求等
        if ("Query3".equals(query)) {
            throw new Exception("Simulated query failure");
        }
        try {
            Thread.sleep(1000);  // 模拟查询耗时
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // 恢复中断状态
            throw e;
        }
        return query + " result";
    }
}