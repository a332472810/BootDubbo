package com.xescm.ofc.service;

import com.xescm.ofc.domain.OfcPlanScreenCondition;
import com.xescm.ofc.domain.OfcPlanScreenResult;

import java.util.List;

/**
 * Created by lyh on 2016/10/10.
 */
public interface OfcPlanScreenService extends IService<OfcPlanScreenResult>{
    /*计划单查询条件筛选结果*/
    List<OfcPlanScreenResult> planScreen(OfcPlanScreenCondition ofcPlanScreenCondition);
}
