package com.jiao.testproject.testproject.thinking_in_java.demo;

import org.hibernate.mapping.Set;

import java.util.*;

// set ---> Collection   被 hashset treeSet 实现
public class Test {
    public static void main(String[] args) {
        //hashset 不会保存插入时的次序  因为直接的 散列算法 吗
        HashSet<Object> hashSet = new HashSet<>();
        // tree 按照元素排序  可以方便实现自定义排序
        TreeSet<Object> treeSet = new TreeSet<>(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        //linkHashSet 能保留插入的顺序
        LinkedHashSet<Object> linkedHashset = new LinkedHashSet<>();
        String[] s = "你 真 是 个 小 可 爱".split(" ");
        for (int i = 0; i < s.length; i++) {
            linkedHashset.add(s[i]);
        }
        Iterator<Object> iterator = linkedHashset.iterator();

        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println("迭代器打印 " + next);
        }


    }
}
