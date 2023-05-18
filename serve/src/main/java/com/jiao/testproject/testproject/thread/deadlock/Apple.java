package com.jiao.testproject.testproject.thread.deadlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Apple {

    private String name ;
    private final int Number = 1 ;
    private int visitedCount  ;
    //并发编程 ArrayList 不能支持

    private String master = "暂无" ;
    private List<String> visitedList = Collections.synchronizedList(new ArrayList<>());

    private static Apple apple = new Apple("红富士苹果", 0 , "");
    private static Apple apple_sd = new Apple("山东苹果", 0 , "");

    private static ReentrantLock lock = new ReentrantLock();

    //请求阻塞
    public static  Apple takeSdApple(Person person){
        lock.lock();
        apple_sd.setMaster(person.getName());
        try {
            // wait 不能在 非 同步块中使用
           Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return apple_sd;
    }
    // 循环 等待
    public static Apple takeHfsApple(Person person){
        synchronized (Apple.class){
                apple.setMaster(person.getName());
            try {
               Apple.class.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                Apple.class.notify();
            }
        }
        return apple;

    }


    public void addVisitedRow(String name ){
        this.visitedList.add(name);
    }
    public String viewVisitedRow(){
        StringBuffer stringBuffer = new StringBuffer("访问苹果的 人员记录 ：\n");
        for (String s: visitedList) {
            stringBuffer.append( s + "\t \n" );
        }
        return stringBuffer.toString();
    }


    private Apple(String name, int visitedCount, String master) {
        this.name = name;
        this.visitedCount = visitedCount;
        this.master = master;
    }

    public  static synchronized Apple creatApple(){

        try {
            apple.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return apple;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return Number;
    }

    public int getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(int visitedCount) {
        this.visitedCount = visitedCount;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
