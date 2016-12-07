package com.xescm.ofc.service.impl;

import com.xescm.ofc.domain.OfcTransplanInfo;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.feign.api.csc.FeignCscCustomerAPI;
import com.xescm.ofc.mapper.OfcTransplanInfoMapper;
import com.xescm.ofc.model.vo.ofc.OfcTransplanInfoVo;
import com.xescm.ofc.service.OfcTransplanInfoService;
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
public class OfcTransplanInfoServiceImpl extends BaseService<OfcTransplanInfo> implements OfcTransplanInfoService {
    private static final Logger logger = LoggerFactory.getLogger(FeignCscCustomerAPI.class);
    @Autowired
    private OfcTransplanInfoMapper ofcTransplanInfoMapper;
    @Override
    public List<OfcTransplanInfo> ofcTransplanInfoScreenList(String orderCode) {
        if(!"".equals(PubUtils.trimAndNullAsEmpty(orderCode))){
            Map<String,String> mapperMap = new HashMap<>();
            mapperMap.put("orderCode",orderCode);
            return ofcTransplanInfoMapper.ofcTransplanInfoScreenList(mapperMap);
        }else {
            throw new BusinessException();
        }
    }

    @Override
    public List<OfcTransplanInfoVo> ofcTransplanInfoVoList(String planCode) {
        if(!"".equals(PubUtils.trimAndNullAsEmpty(planCode))){
            Map<String,String> mapperMap = new HashMap<>();
            mapperMap.put("planCode",planCode);
            return ofcTransplanInfoMapper.ofcTransplanInfoVoList(mapperMap);
        }else {
            throw new BusinessException();
        }
    }
}
