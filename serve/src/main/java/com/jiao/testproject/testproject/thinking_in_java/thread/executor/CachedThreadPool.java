package com.jiao.testproject.testproject.thinking_in_java.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
            executorService.shutdown();
           // executorService = Executors.newCachedThreadPool();
        }

    }
}
