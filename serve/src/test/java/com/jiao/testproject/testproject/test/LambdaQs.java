package com.jiao.testproject.testproject.test;


import com.jiao.testproject.testproject.dto.FileDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ClassName LambdaQs
 * @Description TODO
 * @AUTHOR jiaorongjin
 * @Date 2022/8/18 13:17
 * @Version 1.0
 **/

@SpringBootTest
public class LambdaQs {
    // 调用该方法需要传入一个 Eatable 类型的对象
    @Test
    void test(){

        // 定义两个 用于判断的表达式
        Predicate<Integer> predicateOne = x -> x >= 1;
        Predicate<Integer> predicateTwo = x -> x <= -1;
        Predicate<String> sp = s -> s.equals("4144") ;
        testParam("4144",sp);
        testBj();
    }
    public void eat(Eatable e) {
        System.out.println(e);
        e.taste();
    }


    public void testParam(String s,Predicate<String> stringPredicate){
        boolean test = stringPredicate.test(s);
        if (test){
            System.out.print(test);
        }
    }

    public void Bigdemacil(List<FileDto> list){
        list.stream().forEach(x -> {
            BigDecimal.valueOf(0.01).divide(new BigDecimal(""));
        });

    }

    public void testBj(){
        Integer [] arr = {1,2,3,4,5,6,7,8};
        Integer [] arr2 = {1};
        List<Integer> integers = Arrays.asList(arr);
        List<Integer> integers1 = Arrays.asList(arr2);
        // 获取年龄最大学生
        Optional<Integer> max = integers.stream().reduce(Integer::max);
        System.out.println(max.get());
        // 获取成绩最大的学生
        boolean b = integers.stream().anyMatch(x -> x.equals(5));
        boolean b1 = integers1.stream().allMatch(x -> x.equals(1));
        List<Integer> collect = integers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
        System.out.println(b);
        System.out.println(b1);
    }


}
