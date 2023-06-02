package com.jiao.testproject.testproject.interview.base.collection;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Demo1 {

    //一般大家都知道ArrayList和LinkedList的区别
    //1、ArrayList的实现是基于数组，LinkedList的实现是基于双向链表。
    //2、对于随机访问，ArrayList优于LinkedList
    //3、对于插入和删除操作，LinkedList优于ArrayList
    //4、LinkedList比ArrayList更占内存，因为LinkedList的节点除了存储数据，还存储了两个引用，一个指向前一个元素，一个指向后一个元素。
    @Test
    public void test1(){
        String [] strings = new String[100000000];
        for (int i = 0; i <10000000 ; i++) {
            strings[i] = i + "ASDFGHJKL;;";
        }
        List<String> strings1 = Arrays.asList(strings);
        ArrayList<String> arrayList = new ArrayList<>(strings1);
        LinkedList<String> linkedList = new LinkedList<>(strings1);
        getArrayTime(arrayList);
        getLinkTime(linkedList);
//        insetArrayList(arrayList);
//        insetLinkList(linkedList);
    }

    private void insetLinkList(LinkedList<String> linkedList) {
        long start = System.currentTimeMillis();
        for (int i = 0; i <1 ; i++) {
            linkedList.add(0,i+ "Linkinset ");
        }
        long end = System.currentTimeMillis();
        long l = -(start - end);
        System.out.format("linkedList  time : {%d} ",l);
    }

    private void insetArrayList(ArrayList<String> arrayList) {
        long start = System.currentTimeMillis();
        for (int i = 0; i <1 ; i++) {
            arrayList.add(0,i+ "Arrayinset ");
        }
        long end = System.currentTimeMillis();
        long l = -(start - end);
        System.out.format("arrayList  time : {%d} ",l);
    }

    private void getLinkTime(LinkedList<String> linkedList) {
        long start = System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            String s = linkedList.get(RandomUtil.randomInt(10000,1000000));
        }

        long end = System.currentTimeMillis();
        long l = -(start - end);
        System.out.format("linkedList  time : {%d} ",l);
    }


    private void getArrayTime(ArrayList<String> arrayList) {
        long start = System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            String s = arrayList.get(RandomUtil.randomInt(10000,1000000));
        }
        long end = System.currentTimeMillis();
        long l = -(start - end);
        System.out.format("arrayList  time : {%d} ",l);
    }




}
