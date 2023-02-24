package com.jiao.testproject.testproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiao.testproject.testproject.dto.FileDto;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * @ClassName Test_1
 * @Description TODO
 * @AUTHOR jiaorongjin
 * @Date 2022/8/29 13:12
 * @Version 1.0
 **/
@SpringBootTest
public class Test_1 {


    @org.junit.jupiter.api.Test
    void contextLoads(){
       // pieceTime("20221212");
      /*  System.out.println(2 >>> 10);*/

/*        String s="fs,";
        String[] split = s.split(",");
        Arrays.stream(split).forEach(System.out::println);*/
        FileDto dto = new FileDto();
        test(dto);
//        System.out.println(dto.getFileName());
//        System.out.println(dto.getUserId());

        //System.out.printf("my name : %s ming is :　%s age is : %d ","jiao","rong",144);

    }
    public  String pieceTime(String s){
        //解析模板
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //格式化
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String formatTime = simpleDateFormat2.format(parse);
        return formatTime;
    }

    void test(FileDto fd){
        fd.setFileName("jiao");
        FileDto dto=fd;
        dto.setUserId(1);
    }

    void yy(){
        new JSONArray();
/*        for(int i =0 ; i < zoneJsonArr.size() ;i++ ) {
            JSONObject jsonObject = zoneJsonArr.getJSONObject(i);
            if(!jsonObject.get("pid").equals("root")) {
                zoneJsonArr.remove(jsonObject);
            }
        }*/
    }


    void test(){
        LocalDate localDate = LocalDate.parse("15-03-2018", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    }


}
