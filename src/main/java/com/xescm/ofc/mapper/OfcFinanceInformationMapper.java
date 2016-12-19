package com.xescm.ofc.mapper;

import com.xescm.ofc.domain.OfcFinanceInformation;
import com.xescm.ofc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OfcFinanceInformationMapper extends MyMapper<OfcFinanceInformation> {


    OfcFinanceInformation queryByOrderCode(@Param("orderCode") String orderCode);

}