package com.jiao.testproject.testproject.thinking_in_java.thread.threadjoin;

public class Joiner extends Thread{
    private Sleeper sleeper;

    public Joiner(String name , Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run(){
        try {
            //  一个 线程 调用 join
            System.out.println( "joinner  中 " + getName() + " 准备 启动 "  + sleeper.getName() );
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println( "joiner 中 " +  e );
        }
        System.out.println(getName() + " join completed ");
    }
}
