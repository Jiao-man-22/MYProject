package com.jiao.testproject.testproject.interview;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;


public class CodeTest {
    //取模和循环取模
    @Test
    public void code(){
        int x = 188;
        int y =10;
        System.out.println(x % 10);

    }

    //字符串初始化· 输入一个字符串，此字符串中包含有大小写字母，逗号（“，”），空格（“  ”），现需要对其进行初始化。
    //初始化规则：每个逗号或空格后紧跟的第一个字母如果已经是大写，则保持不变，否则变为大写；其余字母如果有大写的，需要变为小写字母，而小写字母不变；逗号或空格原样输出。（要求输出后仍然是一个字符串）
    public void func2() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        char[] chars = next.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ',' || chars[i] == ' ') {
                if (chars[i + 1] >= 97 && chars[i + 1] <= 122) {
                    chars[i + 1] = (char) (chars[i + 1] - 32);
                } else {
                    if (chars[i + 1] >= 65 && chars[i + 1] <= 90) {              //当前是否为大写字母（大写字母ASCII范围是65~90）
                        chars[i + 1] = (char) (chars[i + 1] + 32);          //变小写
                    }
                }
            }
        }
        System.out.println(String.valueOf(chars));
    }



}
