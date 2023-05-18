package com.jiao.testproject.testproject.thread.deadlock;

import java.util.ArrayList;
import java.util.List;

public class Person implements Runnable {

    private String name ;

    private static int count ;


    List<Apple> appleList = new ArrayList<Apple>();

    public List<Apple> getAppleList() {
        return appleList;
    }

    public void setAppleList(List<Apple> appleList) {
        this.appleList = appleList;
    }

    public boolean addAppleList(Apple apple) {
       return this.appleList.add(apple);
    }
    public boolean hasGetAll(){
        if (this.appleList.size() == 2 ) return true ;
        else return false;
    }

    private boolean isGetAll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public void iterList(){
        appleList.stream().forEach(x ->{
            System.out.println(x.getName() + "\t " + x.getMaster());
        });
    }

    @Override
    public void run() {

        Apple apple = Apple.creatApple();
        apple.setVisitedCount(apple.getVisitedCount() + 1 );
        apple.setMaster(name);
        apple.addVisitedRow(name);
        ++ count;
        if (count == 4) {
            try {
                System.out.println("开始sleep ");
            } catch (Exception e) {
               System.out.println(" 线程 沉睡 触发的异常 ： " + e);
            }
        }
        //如果时张一 就拿到苹果 不释放
//        if (name.equals("张- 1")){
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }


}
