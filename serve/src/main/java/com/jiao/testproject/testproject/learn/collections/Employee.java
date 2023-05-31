package com.jiao.testproject.testproject.learn.collections;

import java.util.*;

public class Employee implements Comparable<Employee>{

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(int id, String name){
        this.id = id;
        this.name = name;

    }

    @Override
    public int compareTo(Employee o) {
        if(this.id > o.id){
            return 1;
        }else if(this.id < o.id){
            return -1;
        }else
            return 0;
    }
    public String toString(){
        return "编号：" + this.id + "姓名：" + this.name + "职位：";
    }

    public static void main(String[] args) {

//        List<Employee> list = new ArrayList<>(5);
//        list.add(new Employee(1004,"sd"));
//        list.add(new Employee(1005,"sa"));
//        list.add(new Employee(1002,"ls")) ;
//        list.add(new Employee(1003,"ww"));
//        list.add(new Employee(1001,"zs"));
//        System.out.println(List.class);
//        //按照id升序排列，如果想按照名字升序排列就要用到Comparator
//        Collections.sort(list);
//        for(Employee e:list){
//            System.out.println(e);
//        }
        String [] strings = new String[]{"zeeeeeeee","zeeeeez","zzeeezz","zzzzz","zzzzzzz"};
        List<String> strings1 = Arrays.asList(strings);
        ArrayList<String> strings2 = new ArrayList<>(strings1);
        Collections.sort(strings2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() -  o2.length());
            }
        });
        for (String s:strings2) {
            System.out.println(s);
        }

    }
}
