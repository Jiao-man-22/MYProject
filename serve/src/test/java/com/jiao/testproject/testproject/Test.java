package com.jiao.testproject.testproject;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

/**
 * @ClassName Test
 * @Description TODO
 * @AUTHOR jiaorongjin
 * @Date 2022/8/19 16:30
 * @Version 1.0
 **/
@SpringBootTest
public class Test {


    @org.junit.jupiter.api.Test
    void contextLoads(){
//        Stream<String> stream= Stream.of("I", "love", "you", "too");
////        stream.sorted((x,y) -> x.length() - y.length()).forEach(x -> System.out.println(x));
////
//        Integer lengthSum = stream.reduce(0,
//        (sum, str) -> sum+str.length(), // 累加器 // (2)
//                (a, b) -> a+b);// 部分和拼接器，并行执行时才会用到 // (3)
//// int lengthSum = stream.mapToInt(str -> str.length()).sum();
//        System.out.println(lengthSum);

/*        Double a = 12d;
        Integer b = 12;
        double v = a / b;

        System.out.println(new BigDecimal(v).setScale(2, RoundingMode.HALF_UP));*/

//        String recordTime= "2020年11月24日 15:22:11";
//        String pattern= "yyyy年MM月dd日 HH:mm:ss";
/*

        Date date = new Date(1 * 1000);
        System.out.println(date);
        System.out.println(date.getTime());
*/

/*        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMdd" );
        try {
            Date parse = sdf.parse("205511020");
            System.out.println(parse);

            SimpleDateFormat toStr= new SimpleDateFormat("yyyy-MM-dd");

            String format = toStr.format(parse);
            System.out.println(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
/*

        BigDecimal bg=new BigDecimal(0.001);
        BigDecimal divide = bg.divide(new BigDecimal(1000)).setScale(3,RoundingMode.HALF_UP);*/
        //BigDecimal bigDecimal = new BigDecimal(divide).setScale(3,RoundingMode.HALF_UP);
        ;
/*
        System.out.println(divide);
        System.out.println(new BigDecimal(1.0E-6).setScale(3,RoundingMode.HALF_UP));
        new BigDecimal("daa");
*/

        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
        List<String> row6 = CollUtil.newArrayList("aa5", "bb5", "cc5", "dd5");
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

//通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
//通过构造方法创建writer
//ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

//跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();

//合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() , "测试标题");
//一次性写出内容，强制输出标题
        writer.write(rows, true);
 
//关闭writer，释放内存
        writer.close();



    }

    void testSteam(List list){
            

    }
}
