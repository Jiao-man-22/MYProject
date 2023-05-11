package com.jiao.testproject.testproject.generator;


import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

public class MpGenerator {

    public static void main(String[] args) {
        new DataSourceConfig.Builder("jdbc:mysql://192.168.101.17:3306/testdatabase?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false","root","123456")
                .dbQuery(new MySqlQuery()) // 数据库查询
                .typeConvert(new MySqlTypeConvert())
                .build();
        System.out.println("=========连接成功=========");
        //
        new GlobalConfig.Builder()
                .fileOverride()
                .outputDir("E:\\JIAO-PERSONAL-PROJECT\\MYProject\\serve\\src\\main\\java\\com\\jiao\\testproject\\testproject\\generator\\out")
                .author("jrj")
                .enableKotlin()
                .enableSwagger()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .build();

    }
}
