package com.xescm.ofc.constant;

/**
 * 返回结果
 * Created by hiyond on 2016/11/15.
 */
public class ResultModel {

    private String code;

    private String desc;

    public ResultModel(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ResultModel(ResultEnum resultCode) {
        this.code = resultCode.getCode();
        this.desc = resultCode.getDesc();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public enum ResultEnum {

        CODE_0000("0000", "OK"),
        CODE_0001("0001", "传递的订单类型有误！"),
        CODE_0002("0002", "传递的业务类型与订单类型不匹配！"),
        CODE_0003("0003", "传递的店铺编码有误！"),
        CODE_0004("0004", "仓库编码不能为空！"),
        CODE_0005("0005", "仓库不存在！"),
        CODE_0006("0006", "仓库不存在！"),
        CODE_0007("0007", "重量、数量、体积不能全部为空"),
        CODE_0008("0008", "货主名称不能为空"),
        CODE_0009("0009", "货主编码不正确或为空"),
        CODE_1000("1000", "自动审核异常"),
        CODE_1001("1001", "订单已经审核"),
        CODE_9999("9999", "系统异常！");

        private String code;

        private String desc;

        ResultEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }

}