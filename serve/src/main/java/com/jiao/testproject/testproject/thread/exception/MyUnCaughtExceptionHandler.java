package com.jiao.testproject.testproject.thread.exception;

public class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    //实现这个 接口 捕获 run 方法中 抛出的异常
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("threadName " + t  );
        System.out.println("caught " + e  );
    }
}
