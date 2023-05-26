package com.jiao.testproject.testproject.corsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
public class MyCorsConfiguration {
    //在 Spring 引导应用程序中使用控制器方法 CORS 配置和@CrossOrigin注释不需要任何特定配置。
    // 全局 CORS 配置可以通过使用自定义方法注册 Bean 来定义，
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }

        };
    }
}
