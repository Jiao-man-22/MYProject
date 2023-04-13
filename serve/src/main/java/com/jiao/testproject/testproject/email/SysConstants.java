package com.jiao.testproject.testproject.email;

import java.util.HashMap;
import java.util.Map;

//SYS_EMAIL_MAP里面存放了我多个邮件账号实例，我将使用他们发送邮件
public interface SysConstants {

    String EMAIL_126 = "126";
    String EMAIL_qq = "qq";
    // 邮件服务器
    Map<String, MailAccountSon> SYS_EMAIL_MAP = new HashMap<String, MailAccountSon>() {{
//        put(EMAIL_126,new MailAccountSon("zhangxuewei0303@126.com","CTLTO***K****J") );
        put(EMAIL_qq,new MailAccountSon("2935996123@qq.com","nhtxcimazypodgdd") );
    }};

}
