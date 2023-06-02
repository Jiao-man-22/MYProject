package com.jiao.testproject.testproject.thinking_in_java.thread.deadlock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
                //executorService.execute(new Person("张- " + i));
            //Future对象来等待任务完成：
            Future<?> submit = executorService.submit(new Person("张- " + i));
            try {
                //Future对象来等待任务完成：请注意，使用Future对象的get方法可以等待任务完成，
                // 但它并不是与join方法完全等价。get方法可以通过设置超时时间来控制等待的时间限制，
                // 而join方法会一直阻塞当前线程，直到被等待线程完成。
                Object o = submit.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        executorService.shutdown();
//        new Thread(new Person("张3")).start();
//        new Thread(new Person("张4")).start();
//        new Thread(new Person("张5")).start();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Apple apple = Apple.creatApple();
        System.out.println(apple.viewVisitedRow());
        System.out.println( "appleName " + apple .getName() + "\n"
                 + "被访问 次数  " + apple.getVisitedCount()  + "\n"
                   + "在 谁的手上 "  + apple.getMaster());
    }
}
