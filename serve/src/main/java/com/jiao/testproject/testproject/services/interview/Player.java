package com.jiao.testproject.testproject.services.interview;

public interface Player {
    /**
     * 用户名。
     */
    String getUsername();

    /**
     * 向玩家发送消息。
     */
    void write(String message);

    /**
     * 玩家是否掉线。
     */
    boolean isOffline();

}
