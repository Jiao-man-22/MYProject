package com.jiao.testproject.testproject;


import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.jiao.testproject.testproject.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEmail {
    private static final Logger log = LoggerFactory.getLogger(TestEmail.class);
    public static void main(String[] args) {

        boolean flag = true;
        try {
            MailAccount account = new MailAccount();
            account.setHost("2935996123@qq.com");
            account.setPort(465);
            account.setAuth(true);
            account.setFrom(("1366845865@qq.com"));
            account.setUser("tu");
            account.setPass("");
            // 使用SSL安全连接
            account.setSslEnable(true);
            //指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
            account.setSocketFactoryClass("javax.net.ssl.SSLSocketFactory");
            //如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
            account.setSocketFactoryFallback(true);
            // 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口456
            account.setSocketFactoryPort(465);
//            MailUtil.send(account, adressees, title, html, true);
        } catch (Exception e) {
            log.debug("邮件发送异常信息：{}", e.getMessage());
            flag = false;
        }
        log.debug("邮件发送状态：{}", flag ? "发送成功" : "发送失败");

    }
}
