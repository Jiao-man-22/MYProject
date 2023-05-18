package com.jiao.testproject.testproject.thread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetAllTask implements  Runnable{

//死锁条件
//1 互拆 资源在使用时 不能被共享
//2 任务A  持有一个资源 并且 请求 一个 被 任务 B 占用的资源
//3 资源不能 被任务抢占 只能 等待 另一个任务释放
//4 循环 等待
    private Person person ;

    public GetAllTask(Person person) {
        this.person = person;
    }

    @Override
    public void run() {

        while (!person.hasGetAll()) {
            if (person.getName().equals("李磊")) {
                Apple apple = Apple.takeSdApple(person);
                person.addAppleList(apple);
                //4 循环 等待
                Apple.takeHfsApple(person);

            }
            if (person.getName().equals("韩梅梅")) {
                Apple apple = Apple.takeHfsApple(person);
                person.addAppleList(apple);
                Apple.takeSdApple(person);
            }
        }
        person.iterList();
    }

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(new GetAllTask());
        Thread t1 = new Thread(new GetAllTask(new Person("李磊")));
        Thread t2 = new Thread(new GetAllTask(new Person("韩梅梅")));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" main print process end lockDead");

    }
}
