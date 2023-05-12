package com.jiao.testproject.testproject.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionThread());
        } catch (Exception e){
            // 没有效果 无法抓到 异常
          System.out.println( "捕获 异常 " + e);
        }
    }
}
