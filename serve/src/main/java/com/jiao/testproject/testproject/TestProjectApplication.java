package com.jiao.testproject.testproject;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import java.util.concurrent.CountDownLatch;

@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties
@EnableDubbo(scanBasePackages = {"com.jiao.testproject.testproject.services"})
public class TestProjectApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(TestProjectApplication.class, args);
        //System.out.println("dubbo service started");
        //new CountDownLatch(1).await();
    }

//    @Bean
//    public ConfigurableServletWebServerFactory webServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
//            @Override
//            public void customize(Connector connector) {
//                connector.setProperty("relaxedQueryChars", "|{}[]");
//            }
//        });
//        return factory;
//    }
    //JPAQueryFactory 加入 spring 容器
    @Bean
    public JPAQueryFactory jpaQueryFactory(@Autowired EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }



    // 加入分页拦截
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
//        // 对于单一数据库类型来说,都建议配置DbType值,避免每次分页都去抓取数据库类型
//        paginationInnerInterceptor.setDbType(DbType.MYSQL);
//        // 将分页插件加入MyBatis-Plus的插件链
//        interceptor.addInnerInterceptor(paginationInnerInterceptor);
//        return interceptor;
//    }





}
