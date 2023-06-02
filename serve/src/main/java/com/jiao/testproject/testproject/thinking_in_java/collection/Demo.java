package com.jiao.testproject.testproject.thinking_in_java.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Demo {
    public static void main(String[] args) {

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        integers.forEach(x ->{
            System.out.println(x);
        });
        boolean contains = integers.contains(1);
        System.out.println("contains " + contains);
        Integer [] array = {1,2,3};
        boolean b = integers.containsAll(Arrays.asList(array));
        System.out.println("containsAll " + b);
        //两个集合取交集
        boolean b1 = integers.retainAll(Arrays.asList(array));
        System.out.println("retainAll " + b1);
        System.out.println("size " + integers.size());
        for (Integer i: integers) {
            System.out.println(i);
        }
    }
}
