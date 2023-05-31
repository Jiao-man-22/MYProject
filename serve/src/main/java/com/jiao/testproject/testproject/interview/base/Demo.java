package com.jiao.testproject.testproject.interview.base;

import java.util.HashMap;
import java.util.HashSet;

public class Demo {

    static  int x = 1 ;int y =2;

    public static void main(String[] args) {

        //bit();
        hasMap();
    }

    void test(){
        // short s1 = 1 ; s1 = s1+1; 不兼容的类型: 从int转换到short可能会有损失”。
        Integer integer = new Integer(1);
        Integer integer1 = new Integer(1);
        System.out.println(integer == integer1);
//        Integer integer = Integer.valueOf(1);
//        Integer integer1 = Integer.valueOf(1);
////        System.out.println(integer == integer1);
//        Integer integer1 = Integer.valueOf(128);
//        Integer integer2 = Integer.valueOf(128);
//        System.out.println(integer1 == integer2);
        x=2;
        x = 2 ;
        y= 3  ;
    }

    static  void bit(){
        //https://joonwhee.blog.csdn.net/article/details/115712641位运算详解
        System.out.println(2 >> 1);
        System.out.println(2 >>> 1);
        System.out.println(2 << 1) ;
    }

    static void hasMap(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("key", "00000");
        objectObjectHashMap.put("key", "11111");
        HashSet hashSet = new HashSet();
        hashSet.add("1111");
        hashSet.add("2222");
//        System.out.println(objectObjectHashMap.get("key"));
        Object next = hashSet.iterator().next();
        System.out.println(next);
    }
}
