package com.springcloud.ms.initalize;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: yaorp
 */
@Component
public class CommandLineRunnerDemo implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("CommandLineRunnerDemo ----开始----");
//        try {
//            Thread.sleep(3*1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("CommandLineRunnerDemo ----结束----");
    }
}
