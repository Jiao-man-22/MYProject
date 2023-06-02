package com.jiao.testproject.testproject.thinking_in_java.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

// SortedSet 元素 可以保证排序
public class SortEdSetDemo {
    public static void main(String[] args) {
        TreeSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet,"one two three four five six seven eight".split(" "));
        System.out.println(sortedSet);
        String first = sortedSet.first();
        String last = sortedSet.last();
        System.out.println(first);
        System.out.println(last);
        Iterator<String> iterator = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) first = iterator.next();
            if (i == 6) last = iterator.next();
            else iterator.next();
        }
        System.out.println(first);
        System.out.println(last);
        System.out.println(sortedSet.subSet(first,last));
        System.out.println(sortedSet.headSet(last));
        System.out.println(sortedSet.tailSet(first));


    }
}
