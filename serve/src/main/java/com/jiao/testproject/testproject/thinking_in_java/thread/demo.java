package com.jiao.testproject.testproject.thinking_in_java.thread;

public class demo implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            System.out.println("demo线程 已启动 -------- " + i );
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "线程执行完毕 ------");

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new demo());
        t1.start();
        System.out.println("t1线程 已启动 -------- " + t1.getName());

        Thread t2 = new Thread(new demo());
        t2.start();
        System.out.println("t2 线程 已启动 -------- " + t2.getName());

        Thread t3 = new Thread(new demo());
        t3.start();
        System.out.println("t3 线程 已启动 -------- " + t3.getName());

        System.out.println("-------------------mian 方法 已经 全部执行完 成 ----------------------------");


    }
}
