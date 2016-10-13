package com.xescm.ofc.mapper;

import com.xescm.ofc.domain.OfcWarehouseInformation;
import com.xescm.ofc.utils.MyMapper;
import tk.mybatis.mapper.common.Mapper;

public interface OfcWarehouseInformationMapper extends MyMapper<OfcWarehouseInformation> {
    int deleteByOrderCode(Object key);
    int updateByOrderCode(Object key);
    OfcWarehouseInformation ofcWarehouseInformationSelect(String orderCode);
}