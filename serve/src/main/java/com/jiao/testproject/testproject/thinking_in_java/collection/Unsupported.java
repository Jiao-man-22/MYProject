package com.jiao.testproject.testproject.thinking_in_java.collection;

import java.util.*;

// UnsupportedOperator 异常 根本原因 由于  容器的数据结构  定死了存储数据的长度 也就是 size
//例如 Arrays.asList 由于数组是定长的  就会得到 尺寸固定的容器
 public class Unsupported {

    static void test(String msg , List<String> list){
        System.out.println("--------------" + msg + "---------------");
        Collection<String> c = list;
        List<String> c2 = list.subList(1, 8);
        //copy of sublist
        Collection<String> arrayList = new ArrayList<>();
        try {
            c.retainAll(c2);
        } catch (Exception e) {
            System.out.println(" retainAll " + e);
        }

        try {
            c.removeAll(c2);
        } catch (Exception e) {
            System.out.println(" removeAll " + e);
        }

        try {
            c.clear();
        } catch (Exception e) {
            System.out.println("clear " + e);
        }
        try {
            c.add("X");
        } catch (Exception e) {
            System.out.println(" add " + e);
        }
        try {
            c.addAll(c2);
        } catch (Exception e) {
            System.out.println("addAll " + e);
        }
        try {
            c.remove("C");
        } catch (Exception e) {
            System.out.println(" remove " + e);
        }
        try {
            //set 方法 修改值 但是不会 修改数据结构(容器)的 尺寸
         list.set(0,"x");
        } catch (Exception e) {
            System.out.println(" set " + e);
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
        test("修改 Copy" , new ArrayList<String>(list));
//        test("Array.asList" , list);
//        test("没有被修改的list" , Collections.unmodifiableList(new ArrayList<String>(list)));
    }

}
