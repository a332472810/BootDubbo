package com.xescm.ofc.model.dto.form;

/**
 * Created by hiyond on 2016/11/24.
 */
public class OrderOperForm extends BaseForm {

    private String custName;

    private String orderCode;

    private String orderState;

    private String orderType;

    private String businessType;

    private String orderBatchNumber;

    public String getOrderBatchNumber() {
        return orderBatchNumber;
    }

    public void setOrderBatchNumber(String orderBatchNumber) {
        this.orderBatchNumber = orderBatchNumber;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = super.trim(custName);
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = super.trim(orderCode);
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = super.trim(orderState);
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = super.trim(orderType);
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = super.trim(businessType);
    }
}