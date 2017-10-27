package com.xescm.ofc.web.restcontroller.operationWorkbench.orderRevise;

import com.xescm.base.model.dto.auth.AuthResDto;
import com.xescm.base.model.wrap.WrapMapper;
import com.xescm.base.model.wrap.Wrapper;
import com.xescm.core.utils.PubUtils;
import com.xescm.ofc.constant.OrderConstConstant;
import com.xescm.ofc.domain.OfcGoodsDetailsInfo;
import com.xescm.ofc.enums.BusinessTypeEnum;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.model.dto.ofc.OfcOrderDTO;
import com.xescm.ofc.service.OfcOrderPlaceService;
import com.xescm.ofc.service.OfcOrderReviseService;
import com.xescm.ofc.web.controller.BaseController;
import com.xescm.tfc.edas.model.dto.ofc.req.GoodsAmountDetailDto;
import com.xescm.tfc.edas.model.dto.ofc.req.GoodsAmountSyncDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2017/8/30.
 */
@Controller
@RequestMapping(value = "/page/ofc/orderRevise")
public class OfcOrderReviseController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OfcOrderPlaceService ofcOrderPlaceService;
    @Resource
    private OfcOrderReviseService ofcOrderReviseService;

    /**
     * 订单修改
     *
     * @param ofcOrderDTOStr 订单基本信息、收发货方信息
     * @param tag            标识下单、编辑、运输开单
     * @return Wrapper
     */
    @RequestMapping("/orderReviseCon/{tag}")
    @ResponseBody
    public Wrapper<?> orderPlace(@RequestBody OfcOrderDTO ofcOrderDTOStr, @PathVariable String tag) {
        logger.info("==>订单修改实体 ofcOrderDTOStr={}", ofcOrderDTOStr);
        logger.info("==>订单修改标志位 tag={}", tag);
        String resultMessage;
        try {
            AuthResDto authResDtoByToken = getAuthResDtoByToken();
            /*if (ofcOrderDTOStr == null) {
                throw new BusinessException("订单修改dto不能为空！");
            }
            if (null == ofcOrderDTOStr.getOrderTime()) {
                throw new BusinessException("请选择订单日期");
            }
            if (CollectionUtils.isEmpty(ofcOrderDTOStr.getGoodsList())) {
                throw new BusinessException("请至少添加一条货品！");
            }
            if (CollectionUtils.isEmpty(ofcOrderDTOStr.getGoodsList())) {
                throw new BusinessException("请至少添加一条货品！");
            }
            if (ofcOrderDTOStr.getConsignor() == null) {
                throw new BusinessException("发货人信息不允许为空！");
            }
            if (ofcOrderDTOStr.getConsignee() == null) {
                throw new BusinessException("发货人信息不允许为空！");
            }*/
            // 校验业务类型，如果是卡班，必须要有运输单号
            if (StringUtils.equals(ofcOrderDTOStr.getBusinessType(), BusinessTypeEnum.CABANNES.getCode())) {
                if (StringUtils.isBlank(ofcOrderDTOStr.getTransCode())) {
                    throw new BusinessException("业务类型是卡班，运输单号是必填项");
                }
            }
            if (null == ofcOrderDTOStr.getProvideTransport()) {
                ofcOrderDTOStr.setProvideTransport(OrderConstConstant.WAREHOUSE_NO_TRANS);
            }
            if (null == ofcOrderDTOStr.getUrgent()) {
                ofcOrderDTOStr.setUrgent(OrderConstConstant.DISTRIBUTION_ORDER_NOT_URGENT);
            }
            // 修改时询问结算中心是否可以修改（未结算）
            // 修改后通知运输中心、调度中心、结算中心
            boolean flag = checkReviseStatus(ofcOrderDTOStr);
            if (flag) {
                resultMessage = "订单修改成功!";
            } else {
                resultMessage = "订单修改失败!";
            }
        } catch (BusinessException ex) {
            logger.error("订单修改出现异常:{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("订单修改出现未知异常:{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, resultMessage);
    }


    // 修改时询问结算中心是否可以修改（未结算）
    public boolean checkReviseStatus(OfcOrderDTO ofcOrderDTOStr) {
        // 封装
        GoodsAmountSyncDto goodsAmountSyncDto = new GoodsAmountSyncDto();
        List<GoodsAmountDetailDto> goodsAmountDetailDtos = new ArrayList<GoodsAmountDetailDto>();
        // 赋值
        goodsAmountSyncDto.setCustCode(ofcOrderDTOStr.getCustCode());
        goodsAmountSyncDto.setCustName(ofcOrderDTOStr.getCustName());
        goodsAmountSyncDto.setCustOrderCode(ofcOrderDTOStr.getCustOrderCode());
        // 封装
        List<OfcGoodsDetailsInfo> goodsList = ofcOrderDTOStr.getGoodsList();
        for (OfcGoodsDetailsInfo ofcGoodsDetailsInfo : goodsList) {
            GoodsAmountDetailDto goodsAmountDetailDto = new GoodsAmountDetailDto();
            // 行号
            if (!PubUtils.isOEmptyOrNull(ofcGoodsDetailsInfo.getPaasLineNo())){
                goodsAmountDetailDto.setPassLineNo(ofcGoodsDetailsInfo.getPaasLineNo());
            }
            // 货品编码
            if (!PubUtils.isOEmptyOrNull(ofcGoodsDetailsInfo.getGoodsCode())){
                goodsAmountDetailDto.setGoodsCode(ofcGoodsDetailsInfo.getGoodsCode());
            }
            // 货品名称
            if (!PubUtils.isOEmptyOrNull(ofcGoodsDetailsInfo.getGoodsName())){
                goodsAmountDetailDto.setGoodsName(ofcGoodsDetailsInfo.getGoodsName());
            }
            if (!PubUtils.isOEmptyOrNull(ofcGoodsDetailsInfo.getQuantity())){
                goodsAmountDetailDto.setQty(ofcGoodsDetailsInfo.getQuantity().toString());
            }
            if (!PubUtils.isOEmptyOrNull(ofcGoodsDetailsInfo.getUnit())){
                goodsAmountDetailDto.setUnit(ofcGoodsDetailsInfo.getUnit());
            }
            if (!PubUtils.isOEmptyOrNull(ofcGoodsDetailsInfo.getCubage())){
                goodsAmountDetailDto.setVolume(ofcGoodsDetailsInfo.getCubage().toString());
            }
            goodsAmountDetailDto.setWeight(ofcGoodsDetailsInfo.getWeight().toString());
            goodsAmountDetailDtos.add(goodsAmountDetailDto);
        }
        // 赋值
        goodsAmountSyncDto.setGoodsAmountDetailDtoList(goodsAmountDetailDtos);
        // 校验
        Wrapper<?> result = ofcOrderReviseService.goodsAmountSync(goodsAmountSyncDto,ofcOrderDTOStr.getOrderCode());
        if (Wrapper.SUCCESS_CODE == result.getCode()) {
            return true;
        } else {
            return false;
        }
    }
}
