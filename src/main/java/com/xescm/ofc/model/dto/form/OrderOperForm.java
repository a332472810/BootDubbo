package com.xescm.ofc.model.dto.form;

import lombok.Data;

/**
 * Created by hiyond on 2016/11/24.
 */
@Data
public class OrderOperForm extends BaseForm {

    private String custName;

    private String orderCode;

    private String orderState;

    private String orderType;

    private String businessType;

    private String orderBatchNumber;

    private String areaSerialNo;

    private String baseSerialNo;
}
