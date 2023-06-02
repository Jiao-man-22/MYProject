package com.jiao.testproject.testproject.thinking_in_java.demo;

public class Test3 {

    // 内存中的 多个 String 对象 会映射到同一块内存区域 所有能 生成同一个hashcode  但 仍是不同对象
    public static void main(String[] args) {
        String s = new String("123");
        String ss = new String("123");
        String sss = "123";
        String ssss = "123";
        // 证明 hashcode 一样 但可以是不同对象
        System.out.println(s.hashCode());
        System.out.println(ss.hashCode());
        System.out.println(sss.hashCode());
        System.out.println(ssss.hashCode());
        System.out.println(s == ss); // 证明 堆内存会有两个对象
        System.out.println(ss == sss); //证明字符串常量池 和 堆内存之间对象独立
        System.out.println(s == sss); // 同上
        System.out.println(sss == ssss); // 证明 字符串常量池 对象共享

    }
}
