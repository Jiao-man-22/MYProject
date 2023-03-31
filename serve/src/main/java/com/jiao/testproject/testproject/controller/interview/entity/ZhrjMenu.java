package com.jiao.testproject.testproject.controller.interview.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "zhrj_menu")
public class ZhrjMenu {

   private String menu_name	;
   private Integer actual_value ;
   private String  display_value ;
   private String menu_description;

}
