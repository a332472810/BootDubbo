package com.xescm.ofc.web.api;

import com.xescm.base.model.wrap.WrapMapper;
import com.xescm.base.model.wrap.Wrapper;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.model.dto.epc.CancelOrderDto;
import com.xescm.ofc.model.vo.epc.CannelOrderVo;
import com.xescm.ofc.service.CreateOrderService;
import com.xescm.ofc.web.controller.BaseController;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单取消接口
 * Created by hiyond on 2016/11/30.
 */
@RestController
public class OfcOrderCancelApiController extends BaseController {

    @Autowired
    private CreateOrderService createOrderService;

    /**
     * 鲜易网取消接口
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/api/epc/order/orderCancel", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation(value = "鲜易网取消接口", notes = "返回是否成功", response = Wrapper.class)
    public Wrapper<CannelOrderVo> orderCancel(@RequestBody CancelOrderDto cancelOrderDto) {
        logger.info("取消订单接口参数：custOrderCode：{}", ToStringBuilder.reflectionToString(cancelOrderDto));
        try {
            if (cancelOrderDto == null) {
                throw new IllegalArgumentException("客户订单编号不能为空");
            }
            if (StringUtils.isBlank(cancelOrderDto.getCustOrderCode())) {
                throw new IllegalArgumentException("客户订单编号不能为空");
            }
            if (StringUtils.isBlank(cancelOrderDto.getCustCode())) {
                throw new IllegalArgumentException("货主编码不能为空");
            }
            Wrapper<CannelOrderVo> wrapper = createOrderService.cancelOrderStateByOrderCode(cancelOrderDto);
            return wrapper;
        } catch (IllegalArgumentException ex) {
            logger.error("取消订单接口处理失败：错误原因：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (BusinessException ex) {
            logger.error("取消订单接口处理失败：错误原因：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("取消订单接口处理失败：错误原因：{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

}
