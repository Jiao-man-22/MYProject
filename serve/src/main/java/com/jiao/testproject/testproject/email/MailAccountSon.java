package com.jiao.testproject.testproject.email;

import cn.hutool.extra.mail.MailAccount;

public class MailAccountSon  extends MailAccount {

    public MailAccountSon(String host, Integer port, Boolean auth, String from, String user, String pass) {
        super.setHost(host);
        super.setPort(port);
        super.setAuth(auth);
        super.setFrom(from);
        super.setUser(user);
        super.setPass(pass);
    }

    public MailAccountSon(String host, Integer port, Boolean auth, String from, String pass) {
        super.setHost(host);
        super.setPort(port);
        super.setAuth(auth);
        super.setFrom(from);
        super.setPass(pass);
    }

    public MailAccountSon(String from, String pass) {
        super.setFrom(from);
        super.setPass(pass);
    }

}
