package com.jiao.testproject.testproject.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaptureUnCaughtException {
    public static void main(String[] args) {
        // 创建线程池 自定义 产生线程的线程工厂 设置 自定义线程异常捕获接口
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionThread2("t1"));

    }
}
