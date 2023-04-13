//package configs;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.context.annotation.Configuration;
//
///****
//* @Description: 配置类
//* @Param:
//* @return:
//* @Author: JRJ
//* @Date: 2023/4/13
//*/
//
//@Configuration
//public class MybatisPlusConfig {
//    private String mapperLocations = "classpath*:/mapper/*Mapper.xml";
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
//            throws Exception {
//        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
//        return sessionFactory.getObject();
//    }
//
//}
