package com.jiao.testproject.testproject.controller.interview;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
@Service
public class InterViewServiceImpl  implements  InterViewService{

    @Resource
    InterViewMapper interViewMapper;




    @Override
    public List<Map<Object, Object>> getList(String eventReasons) {

        // Page 分页看插件 l
//        Page<Object> of = Page.of(0, 6);
        int currentPage = 0 ;
        int pageSize = 5 ;
        if (currentPage != 0){
            currentPage += pageSize;
        }
        List<Map<Object, Object>> supportEventList = interViewMapper.getSupportEvent(eventReasons,currentPage,pageSize);

        if (supportEventList == null || CollectionUtil.isEmpty(supportEventList)){
            log.info("supportEventList 集合 为 null  line 30 ");
            return null;
        }else {
            //得到菜单表信息
//            List<ZhrjMenu> zhrjMenuListInfo = interViewMapper.getZhrjMenuListInfo();
//            Map<String, String> nameMap = zhrjMenuListInfo.stream().collect(Collectors.toMap(ZhrjMenu::getMenu_name, ZhrjMenu::getMenu_description));
//            supportEventList.stream().forEach(x -> {
//                Integer event_type = (Integer)x.get("event_type");
//                String ventType = nameMap.get(event_type);
//                x.put("modifiedEventType",ventType);
//                Integer eventStatus = (Integer)x.get("event_status");
//                x.put("modifiedeventStatus",ventType);
//            });

        }
        return supportEventList;
    }

    @Override
    public List<Map<Object, Object>> getListPage(String eventReasons) {
        int pageSize = 5 ;
        int currentPage = 0;
        Page<Map<Object,Object>> page = new Page<>(currentPage,pageSize);

        List<Map<Object, Object>> supportEventList = interViewMapper.getSupportEventPage(page,eventReasons);
        return   supportEventList;
    }
}
