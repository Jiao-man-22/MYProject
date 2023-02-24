package com.jiao.testproject.testproject.dto.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Department {
    private Integer departmentId;
    private String  departmentName;
    private List<UserInfo> departmentPerson;
}
