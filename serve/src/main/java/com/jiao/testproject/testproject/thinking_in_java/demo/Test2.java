package com.jiao.testproject.testproject.thinking_in_java.demo;

import java.util.*;

// treeSet a —— z 排序
public class Test2 {
    public static void main(String[] args) {
       char[] s = ("A b c d e f g h i j k l  m n  o p" +
                " q r s t u v w  x y z a B C D E " +
                " F G H I J K L M N O P Q " +
                " R S T U V W X Y Z ").trim().toCharArray();

        TreeSet<Character> treeSet = new TreeSet<Character>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                Integer integer = Integer.valueOf(o1.charValue());
                Integer integer1 = Integer.valueOf(o2.charValue());
                return (integer -integer1) ;
            }
        });
        for (char x:s) {
            treeSet.add(new Character(x));
        }


        for (char ss : treeSet) {
        System.out.print(ss + "\t");
        }
    }
}
