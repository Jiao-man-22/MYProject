package com.jiao.testproject.testproject.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 *<pre>
 *     自定义redis连接工厂ReadWriteLettuceConnectionFactory
 * 由于我们有多个只读库，为了实现动态切换，我们自己是实现一个工厂，方便后面操作
 *</pre>
 *
 */
@Slf4j
@Component
public class ReadWriteLettuceConnectionFactory implements DisposableBean {


    @Override
    public void destroy() throws Exception {

    }
}
