package com.jiao.testproject.testproject.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Collections;

public class MpGenerator {

    public static void main(String[] args) {
        DataSourceConfig build = new DataSourceConfig.Builder("jdbc:mysql://192.168.101.17:3306/testdatabase?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456")
                .dbQuery(new MySqlQuery()) // 数据库查询
                .schema("testdatabase")
                .typeConvert(new MySqlTypeConvert())
                .build();
        //
        GlobalConfig globalconfig = new GlobalConfig.Builder()
                .fileOverride()
                .outputDir("E:\\JIAO-PERSONAL-PROJECT\\MYProject\\serve\\src\\main\\java\\com\\jiao\\testproject\\testproject\\generator\\out")
                .author("jrj")
                .enableSwagger()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .build();

        PackageConfig packageconfig = new PackageConfig.Builder()
                .parent("com.baomidou.mybatisplus.samples.generator")
                .moduleName("sys")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .xml("mapper.xml")
                .controller("controller")
                .other("other")
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\JIAO-PERSONAL-PROJECT\\MYProject\\serve\\src\\main\\java\\com\\jiao\\testproject\\testproject\\generator\\out\\xml"))
                .build();

        TemplateConfig templateconfig = new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();

        new InjectionConfig.Builder()
                .beforeOutputFile((tableInfo, objectMap) -> {
                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                })
                .customMap(Collections.singletonMap("test", "baomidou"))
                .customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))
                .build();

        new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .disableSqlFilter()
                .likeTable(new LikeTable("USER"))
                .addInclude("t_simple")
                .addTablePrefix("t_", "c_")
                .addFieldSuffix("_flag")
                .build();

        StrategyConfig strategyconfig = new StrategyConfig.Builder()
                .entityBuilder()
                //.superClass(BaseEntity.class)
                .disableSerialVersionUID()
                .enableChainModel()
                .enableLombok()
                .enableRemoveIsPrefix()
                .enableTableFieldAnnotation()
                .enableActiveRecord()
                .versionColumnName("version")
                .versionPropertyName("version")
                .logicDeleteColumnName("deleted")
                .logicDeletePropertyName("deleteFlag")
                .naming(NamingStrategy.no_change)
                .columnNaming(NamingStrategy.underline_to_camel)
                .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
                .addIgnoreColumns("age")
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                .idType(IdType.AUTO)
                .formatFileName("%sEntity")
                .build();

        new StrategyConfig.Builder()
                .serviceBuilder()
                //.superServiceClass(BaseService.class)
                //.superServiceImplClass(BaseServiceImpl.class)
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImp")
                .build();

        new StrategyConfig.Builder()
                .mapperBuilder()
                .superClass(BaseMapper.class)
                .enableMapperAnnotation()
                .enableBaseResultMap()
                .enableBaseColumnList()
                //.cache(MyMapperCache.class)
                .formatMapperFileName("%sDao")
                .formatXmlFileName("%sXml")
                .build();
        //自动生成
        AutoGenerator autoGenerator = new AutoGenerator(build);

        autoGenerator.global(globalconfig).packageInfo(packageconfig).template(templateconfig);
        //执行
        autoGenerator.execute();
        System.out.println("=========生成结束 =========");
    }
}
