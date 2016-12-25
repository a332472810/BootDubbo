package com.xescm.ofc.mapper;

import com.xescm.ofc.domain.OfcSiloproStatus;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OfcSiloproStatusMapper extends Mapper<OfcSiloproStatus> {
    int updateByPlanCode(Object key);

    List<OfcSiloproStatus> queryUncompletedPlanCodesByOrderCode(@Param(value = "orderCode") String orderCode);
}