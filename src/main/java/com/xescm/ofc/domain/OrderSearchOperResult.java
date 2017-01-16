package com.xescm.ofc.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class OrderSearchOperResult {

    private String custName;

    private String orderCode;

    private String custOrderCode;

    private String orderBatchNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderTime;

    private String orderType;

    private String businessType;

    private String baseName;

    private String orderStatus;

    private String consigneeType;

    private String consigneeName;

    private String consigneeContactName;

    private String warehouseName;

    private String notes;

    private String storeName;



}
