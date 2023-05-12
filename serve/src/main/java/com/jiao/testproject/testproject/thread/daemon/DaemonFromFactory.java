package com.jiao.testproject.testproject.thread.daemon;

import lombok.SneakyThrows;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable{
    @SneakyThrows
    @Override
    public void run() {
        while (true){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread() + " " + this );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new DaemonFromFactory());
            System.out.println("Daemons started ");
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
