package com.xescm.ofc.service;

import com.xescm.ofc.model.vo.ofc.OrderScreenResult;
import com.xescm.ofc.model.dto.form.OrderOperForm;

import java.util.List;

/**
 * Created by hiyond on 2016/11/24.
 */

public interface OfcOrderManageOperService {

    List<OrderScreenResult> queryOrderOper(OrderOperForm form);

}
