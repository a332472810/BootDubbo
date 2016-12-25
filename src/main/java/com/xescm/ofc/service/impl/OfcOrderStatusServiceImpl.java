package com.xescm.ofc.service.impl;

import com.xescm.ofc.domain.OfcOrderStatus;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.feign.api.csc.FeignCscCustomerAPI;
import com.xescm.ofc.mapper.OfcOrderStatusMapper;
import com.xescm.ofc.service.OfcOrderStatusService;
import com.xescm.ofc.utils.PubUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyh on 2016/10/10.
 */
@Service
@Transactional
public class OfcOrderStatusServiceImpl extends BaseService<OfcOrderStatus> implements OfcOrderStatusService {
    private static final Logger logger = LoggerFactory.getLogger(FeignCscCustomerAPI.class);
    @Autowired
    private OfcOrderStatusMapper ofcOrderStatusMapper;

    @Override
    public int deleteByOrderCode(Object key) {
        return ofcOrderStatusMapper.deleteByOrderCode(key);
    }

    @Override
    public List<OfcOrderStatus> orderStatusScreen(String code, String followTag) {
        if (!PubUtils.trimAndNullAsEmpty(code).equals("")) {
            String orderCode = null;
            String custOrderCode = null;
            String transCode = null;
            if (followTag.equals("orderCode")) {
                orderCode = code;
            } else if (followTag.equals("custOrderCode")) {
                custOrderCode = code;
            } else if (followTag.equals("transCode")) {
                transCode = code;
            }
            // Map<String,String> mapperMap = new HashMap<String,String>();
            Map<String, String> mapperMap = new HashMap<>();
            mapperMap.put("orderCode", orderCode);
            mapperMap.put("custOrderCode", custOrderCode);
            mapperMap.put("transCode", transCode);
            return ofcOrderStatusMapper.orderStatusScreen(mapperMap);
        } else {
            throw new BusinessException("订单状态查询有误");
        }
    }

    @Override
    public OfcOrderStatus orderStatusSelect(String code, String followTag) {
        if (!PubUtils.trimAndNullAsEmpty(code).equals("")) {
            String orderCode = null;
            String custOrderCode = null;
            String transCode = null;

            if (followTag.equals("orderCode")) {
                orderCode = code;
            } else if (followTag.equals("custOrderCode")) {
                custOrderCode = code;
            } else if (followTag.equals("transCode")) {
                transCode = code;
            }
            // Map<String,String> mapperMap = new HashMap<String,String>();
            Map<String, String> mapperMap = new HashMap<>();
            mapperMap.put("orderCode", orderCode);
            mapperMap.put("custOrderCode", custOrderCode);
            mapperMap.put("transCode", transCode);
            OfcOrderStatus ofcOrderStatus = ofcOrderStatusMapper.orderStatusSelect(mapperMap);
            if (ofcOrderStatus == null) {
                return new OfcOrderStatus();
            }
            return ofcOrderStatus;
        } else {
            throw new BusinessException("订单状态查询有误");
        }

    }

    @Override
    public OfcOrderStatus queryOrderStateByOrderCode(String orderCode) {
        return ofcOrderStatusMapper.queryOrderStateByOrderCode(orderCode);
    }

    @Override
    public void cancelOrderStateByOrderCode(String orderCode) {
        ofcOrderStatusMapper.cancelOrderStateByOrderCode(orderCode);
    }

    @Override
    public OfcOrderStatus queryLastUpdateOrderByOrderCode(String orderCode) {
        return ofcOrderStatusMapper.queryLastUpdateOrderByOrderCode(orderCode);
    }
}