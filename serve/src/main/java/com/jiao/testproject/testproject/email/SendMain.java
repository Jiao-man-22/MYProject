package com.jiao.testproject.testproject.email;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.jiao.testproject.testproject.utils.RandomUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SendMain {

    public static void main(String[] args) {
        MailAccount mailAccount = SysConstants.SYS_EMAIL_MAP.get(SysConstants.EMAIL_qq);
        String fourBitRandom = RandomUtils.getFourBitRandom();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("你的验证码:"+ fourBitRandom +" 一分钟之内过期");
        String msg = MailUtil.send(mailAccount, CollUtil.newArrayList("1366845865@qq.com"), "测试", stringBuffer.toString(), false);
        log.info(msg);
    }
}
