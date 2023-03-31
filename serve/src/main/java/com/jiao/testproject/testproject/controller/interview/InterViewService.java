package com.jiao.testproject.testproject.controller.interview;

import java.util.List;
import java.util.Map;

public interface InterViewService {


     List<Map<Object,Object>> getList (String eventReasons);

     List<Map<Object,Object>> getListPage (String eventReasons);

}
