package com.jiao.ssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**只要在将@SpringBootApplication修改为
 * @SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})就可以启动的时候不需要连接数据库。
**/

 @SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SsoServerApplication {

    public static void main(String[] args)   {
        SpringApplication.run(SsoServerApplication.class, args);
    }

}
