package com.jiao.testproject.testproject.controller.interview;


import com.jiao.testproject.testproject.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InterViewController {

    @Autowired
    private InterViewService InterViewServiceImpl;

    @GetMapping("/getListData")
    AjaxResult getListData(@RequestParam(required = false) String reason ){
        List<Map<Object, Object>> list = InterViewServiceImpl.getList(reason);
        return AjaxResult.success(list);
    }

    @GetMapping("/getListDataPage")
    AjaxResult getListDataPage(@RequestParam(required = false) String reason ){
        List<Map<Object, Object>> list = InterViewServiceImpl.getListPage(reason);
        return AjaxResult.success(list);
    }

}
