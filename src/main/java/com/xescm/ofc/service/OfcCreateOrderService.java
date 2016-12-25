package com.xescm.ofc.service;

import com.xescm.ofc.constant.ResultModel;
import com.xescm.ofc.model.dto.coo.CreateOrderEntity;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.model.dto.wms.AddressDto;
import java.util.Map;

/**
 * 接口创建订单
 * Created by hiyond on 2016/11/18.
 */
public interface OfcCreateOrderService {

    int queryCountByOrderStatus(String orderCode, String  orderStatus);

    ResultModel ofcCreateOrder(CreateOrderEntity createOrderEntity, String orderCode) throws BusinessException;

    /**
     * 根据省市区名称获取编码
     * @param addressDto
     * @return
     */
    Map<String, String> getAddressCode(AddressDto addressDto);

}