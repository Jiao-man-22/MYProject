package com.jiao.testproject.testproject.dto.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class UserInfo {
    private Integer userId;
    private String userName;
    private String departmentId;
    private String departmentName;

}
