package com.jiao.testproject.testproject.function;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

public class MD5Test {
    public static void main(String[] args) {

        String md5 = SecureUtil.md5("123456");
        String md5_1 = SecureUtil.md5("123456");
        System.out.println(md5 == md5_1);
        System.out.println(md5.equals(md5_1));
        //结果 两次md5 加密是一样的
    }
}
