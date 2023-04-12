package com.jiao.testproject.testproject.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Column(name = "user_id")
    @Excel(name = "user_id")
    private Integer user_id;

    @Excel(name = "user_name")
    @Column(name = "user_name")
    private String user_name;

    @Excel(name = "user_password")
    @Column(name = "user_password")
    private String user_password;

    @Excel(name = "role")
    @Column(name = "role")
    // 0 :是 普通 1：admin
    private Integer role;

    @Excel(name = "status")
    @Column(name = "status")
    //0:存在  1：逻辑删除
    private Integer status;

    @Excel(name = "creater_id")
    @Column(name = "creater_id")
    private String creater_id;

    @Excel(name = "create_time")
    @Column(name = "create_time")
    private String create_time;

    @Excel(name = "updater_id")
    @Column(name = "updater_id")
    private String updater_id;

    @Excel(name = "update_time")
    @Column(name = "update_time")
    private String update_time;



}
