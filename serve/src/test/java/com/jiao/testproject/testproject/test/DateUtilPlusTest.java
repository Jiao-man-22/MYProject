package com.jiao.testproject.testproject.test;

import cn.hutool.core.lang.copier.Copier;
import cn.hutool.core.lang.func.Consumer3;
import com.jiao.testproject.testproject.utils.DateUtilPlus;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DateUtilPlusTest
 * @Description TODO
 * @AUTHOR jiaorongjin
 * @Date 2022/8/29 15:29
 * @Version 1.0
 **/
@SpringBootTest
public class DateUtilPlusTest {

    @org.junit.jupiter.api.Test
    void contextLoads(){

        String fullDate = DateUtilPlus.getFullDate();
        System.out.println(fullDate);
        ArrayList<File> fileArrayList = new ArrayList<>();
        Long aLong = DateUtilPlus.toEpochMilli("2022-08-18 15:32:21");
        System.out.println(aLong);
        String s = DateUtilPlus.formatMillToFull(aLong);
        System.out.println(s);

    }
}

