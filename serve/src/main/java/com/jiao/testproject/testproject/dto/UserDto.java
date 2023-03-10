package com.jiao.testproject.testproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Integer uuid;
    private String userName;
    private String password;
    // 0 :是 普通 1：admin
    private Integer role;
    //0:存在  1：逻辑删除
    private Integer status;




}
