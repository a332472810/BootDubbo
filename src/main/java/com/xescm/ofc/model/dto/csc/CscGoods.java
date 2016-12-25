package com.xescm.ofc.model.dto.csc;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbl_csc_goods")
public class CscGoods implements Serializable{
    private static final long serialVersionUID = 8946219384405647243L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name="serial_no")
    private String serialNo;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 货品类型
     */
    @Column(name = "goods_type")
    private String goodsType;
    /**
     * 货品类型id
     */
    @Column(name = "goods_type_id")
    private String goodsTypeId;

    /**
     * 货品编码
     */
    @Column(name = "goods_code")
    private String goodsCode;

    /**
     * 货品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 单位
     */
    private String unit;

    /**
     * 成本价
     */
    @Column(name = "cost_price")
    private String costPrice;

    /**
     * 单价
     */
    @Column(name = "unit_price")
    private String unitPrice;



    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 最近操作人
     */
    @Column(name = "last_operator")
    private String lastOperator;

    /**
     * 最后操作人ID
     */
    @Column(name = "last_operator_id")
    private String lastOperatorId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 删除(1-已删除；0-未删除)
     */
    private Integer yn;

    /**
     * 客户ID
     */
    @Column(name = "customer_code")
    private String customerCode;
    /**
     * 条形码
     * */
    @Column(name = "bar_code")
    private String barCode;
    /**
     * 状态(0-停用；1-正常)
     * */
    private String state;
    /**
     * 保质期限
     * */
    @Column(name = "expiry_date")
    private String expiryDate;
    /**
     * 长
     * */
    private String length;
    /**
     * 宽
     * */
    private String width;
    /**
     * 高
     * */
    private String height;
    /**
     * 体积
     * */
    private String volume;
    /**
     * 重量
     * */
    private String weight;
    /**
     * 货品描述
     * */
    private String description;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 温度带
     */
    private String keeptemperate;


    /**
     * 货品大类id
     */
    @Transient
    private String gtpid;


    /**
     * 货品大类名称
     */
    @Transient
    private String goodsTypeName;

    /**
     * 货品小类id
     */
    @Transient
    private String gtid;

    /**
     * 货品小类名称
     */
    @Transient
    private String smallGoodsTypeName;


    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取货品类型
     *
     * @return goods_type - 货品类型
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置货品类型
     *
     * @param goodsType 货品类型
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取货品编码
     *
     * @return goods_code - 货品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 设置货品编码
     *
     * @param goodsCode 货品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    /**
     * 获取货品名称
     *
     * @return goods_name - 货品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置货品名称
     *
     * @param goodsName 货品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取规格
     *
     * @return specification - 规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 设置规格
     *
     * @param specification 规格
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取成本价
     *
     * @return cost_price - 成本价
     */
    public String getCostPrice() {
        return costPrice;
    }

    /**
     * 设置成本价
     *
     * @param costPrice 成本价
     */
    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取单价
     *
     * @return unit_price - 单价
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置单价
     *
     * @param unitPrice 单价
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建人ID
     *
     * @return creator_id - 创建人ID
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人ID
     *
     * @param creatorId 创建人ID
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取最近操作人
     *
     * @return last_operator - 最近操作人
     */
    public String getLastOperator() {
        return lastOperator;
    }

    /**
     * 设置最近操作人
     *
     * @param lastOperator 最近操作人
     */
    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    /**
     * 获取最后操作人ID
     *
     * @return last_operator_id - 最后操作人ID
     */
    public String getLastOperatorId() {
        return lastOperatorId;
    }

    /**
     * 设置最后操作人ID
     *
     * @param lastOperatorId 最后操作人ID
     */
    public void setLastOperatorId(String lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取删除(1-已删除；0-未删除)
     *
     * @return yn - 删除(1-已删除；0-未删除)
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置删除(1-已删除；0-未删除)
     *
     * @param yn 删除(1-已删除；0-未删除)
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }


    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CscGoods{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", goodsType='" + goodsType + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", costPrice='" + costPrice + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", creator='" + creator + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", createdTime=" + createdTime +
                ", lastOperator='" + lastOperator + '\'' +
                ", lastOperatorId='" + lastOperatorId + '\'' +
                ", updateTime=" + updateTime +
                ", yn=" + yn +
                ", customerCode='" + customerCode + '\'' +
                ", barCode='" + barCode + '\'' +
                ", state='" + state + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", volume='" + volume + '\'' +
                ", weight='" + weight + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getKeeptemperate() {
        return keeptemperate;
    }

    public void setKeeptemperate(String keeptemperate) {
        this.keeptemperate = keeptemperate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGtpid() {
        return gtpid;
    }

    public void setGtpid(String gtpid) {
        this.gtpid = gtpid;
    }

    public String getGtid() {
        return gtid;
    }

    public void setGtid(String gtid) {
        this.gtid = gtid;
    }

    public String getSmallGoodsTypeName() {
        return smallGoodsTypeName;
    }

    public void setSmallGoodsTypeName(String smallGoodsTypeName) {
        this.smallGoodsTypeName = smallGoodsTypeName;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}