package com.jiao.testproject.testproject.thread.exception;
//任务线程
public class ExceptionThread2 implements Runnable{
    private String name;

    public ExceptionThread2(String name ) {
    this.name = name;
    }

    @Override
    public void run() {
        //这边的 线程是从 线程工厂获得 的
        Thread thread = Thread.currentThread(); // 拿到当前 线程
        System.out.println(" 原来的 线程名字 " + thread.getName());
        thread.setName(this.name);
        System.out.println(" 重命名的 线程名字 " + thread.getName());
        System.out.println("run by "  + thread );
        System.out.println(" eh " + thread.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
