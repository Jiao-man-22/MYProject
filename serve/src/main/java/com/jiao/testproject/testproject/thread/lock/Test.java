package com.jiao.testproject.testproject.thread.lock;

import cn.hutool.core.util.RandomUtil;

public class Test {

    public static void main(String[] args) {

        IntGenerator intGenerator = new IntGenerator() {
            @Override
            public int next() {
                int i = RandomUtil.randomInt(1, 9);
                return i;
            }
        };
        EvenChecker.test(intGenerator);
    }
}
