package com.jiao.testproject.testproject.thinking_in_java.thread.threadjoin;

public class Sleeper extends Thread{
    private int duration;

    public Sleeper(String name , int sleepTime) {
     super(name);
     duration = sleepTime ;
     start();
    }

    public void run(){
        try {
            System.out.println( "sleeper 中 " + getName() + "准备 休眠 ");
            sleep(duration);
            System.out.println( "sleeper 中 " + getName() + "被唤醒  ");

        } catch (InterruptedException e) {
            System.out.println("线程被 中断 ： " + getName());
            System.out.println(getName() + " was interrupted " + " is interrupted " + isInterrupted()  + " 发生的 Exception " + e );
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}



