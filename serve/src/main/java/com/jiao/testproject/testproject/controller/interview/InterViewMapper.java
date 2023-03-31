package com.jiao.testproject.testproject.controller.interview;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiao.testproject.testproject.controller.interview.entity.ZhrjMenu;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface InterViewMapper extends BaseMapper {
    @MapKey("id")
    List<Map<Object,Object>> getSupportEvent(@Param("eventReasons") String eventReasons,@Param("currentPage") int currentPage,@Param("pageSize") int pageSize);

    List<ZhrjMenu>getZhrjMenuListInfo();

    @MapKey("id")
    List<Map<Object,Object>> getSupportEventPage(Page page, @Param("eventReasons") String eventReasons);

}
