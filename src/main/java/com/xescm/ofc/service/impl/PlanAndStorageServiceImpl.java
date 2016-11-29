package com.xescm.ofc.service.impl;

import com.xescm.ofc.model.dto.vo.PlanAndStorageVo;
import com.xescm.ofc.mapper.PlanAndStorageMapper;
import com.xescm.ofc.service.PlanAndStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hiyond on 2016/11/25.
 */
@Service
public class PlanAndStorageServiceImpl implements PlanAndStorageService {

    @Autowired
    private PlanAndStorageMapper planAndStorageMapper;

    @Override
    public List<PlanAndStorageVo> queryPlanAndStorage(String orderCode, String transCode) {
        return planAndStorageMapper.queryPlanAndStorage(orderCode,transCode);
    }

    @Override
    public List<PlanAndStorageVo> queryPlanAndStorageTrans(String orderCode, String transCode) {
        return planAndStorageMapper.queryPlanAndStorageTrans(orderCode,transCode);
    }
}
