package com.jiao.testproject.testproject.thread.exception;

import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println( this + " creating new Thread");
        Thread thread = new Thread(r);
        System.out.println("created " + thread);
        thread.setUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
        System.out.println("MyEh = " + thread.getUncaughtExceptionHandler());
        return thread;
    }
}
