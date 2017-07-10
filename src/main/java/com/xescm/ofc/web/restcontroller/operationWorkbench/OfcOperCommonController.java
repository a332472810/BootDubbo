package com.xescm.ofc.web.restcontroller.operationWorkbench;

import com.github.pagehelper.PageInfo;
import com.xescm.base.model.dto.auth.AuthResDto;
import com.xescm.base.model.dto.component.req.Select2ReqDto;
import com.xescm.base.model.dto.component.resp.Select2RespDto;
import com.xescm.base.model.wrap.WrapMapper;
import com.xescm.base.model.wrap.Wrapper;
import com.xescm.core.utils.JacksonUtil;
import com.xescm.core.utils.PubUtils;
import com.xescm.csc.model.dto.CscSupplierInfoDto;
import com.xescm.csc.model.dto.goodstype.CscGoodsTypeDto;
import com.xescm.csc.model.vo.CscGoodsTypeVo;
import com.xescm.csc.provider.CscCustomerEdasService;
import com.xescm.csc.provider.CscGoodsTypeEdasService;
import com.xescm.csc.provider.CscSupplierEdasService;
import com.xescm.ofc.domain.Page;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.model.dto.ofc.AuditOrderDTO;
import com.xescm.ofc.service.OfcOrderManageService;
import com.xescm.ofc.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by lyh on 2017/7/5.
 */
@Controller
@RequestMapping(value = "/page/ofc/common")
@Api(value = "OfcOperCommonController", tags = "OfcOperCommonController", description = "OFC运营公用", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OfcOperCommonController extends BaseController{

    Logger logger = LoggerFactory.getLogger(OfcOperCommonController.class);

    @Resource
    private CscCustomerEdasService cscCustomerEdasService;

    @Resource
    private OfcOrderManageService ofcOrderManageService;

    @Resource
    private CscSupplierEdasService cscSupplierEdasService;

    @Resource
    private CscGoodsTypeEdasService cscGoodsTypeEdasService;

    /**
     * Select2查询客户
     */
    @RequestMapping(value = "/queryCustsBySelect2", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "返回客户列表", httpMethod = "POST", value = "Select2查询客户")
    public Wrapper<PageInfo<Select2RespDto>> queryCustsBySelect2(@RequestBody Page<String> reqParam) {
        logger.info("Select2查询客户==>{}", reqParam);
        Wrapper<PageInfo<Select2RespDto>> wrapper;
        PageInfo<Select2RespDto> result;
        try {
            Select2ReqDto select2ReqDto = new Select2ReqDto();
            BeanUtils.copyProperties(reqParam, select2ReqDto);
            select2ReqDto.setName(reqParam.getParam());
            wrapper = cscCustomerEdasService.queryCustomerListPageWithSelect2(select2ReqDto);
            if (null == wrapper) throw new BusinessException("Select2查询客户为空");
            result = wrapper.getResult();
        } catch (BusinessException e) {
            logger.error("Select2查询客户出错:{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception e) {
            logger.error("Select2查询客户出错:{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE,Wrapper.ERROR_MESSAGE);
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }

    /**
     * 订单删除
     *
     * @param orderCode     订单编号
     * @return  Wrapper
     */
    @RequestMapping(value = "/orderDeleteOper/{orderCode}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(
            notes = "删除订单",
            httpMethod = "POST",
            value = "删除订单"
    )
    public Wrapper<?> orderDeleteOper(@ApiParam(name = "orderCode",value = "订单号" ) @PathVariable String orderCode) {
        try {
            if (StringUtils.isBlank(orderCode)) {
                throw new Exception("订单编号不能为空！");
            }
            String result = ofcOrderManageService.orderDelete(orderCode);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            logger.error("订单中心订单管理订单删除出现异常:{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("订单中心订单管理订单删除出现异常:{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
        }
    }

    /**
     * 仓储订单的审核与反审核  暂用该方法    如果改版后的方法兼容  该方法可去除
     * @param auditOrderDTO
     * @return
     */
    @RequestMapping(value = "/auditOrderOrNotAuditOper", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(
            notes = "审核订单",
            httpMethod = "POST",
            value = "审核订单"
    )
    public Wrapper<?> auditOrderOrNotAuditOper(@ApiParam(name = "AuditOrderDTO",value = "审核订单的DTO") @RequestBody AuditOrderDTO auditOrderDTO) {
        try {
            AuthResDto authResDtoByToken = getAuthResDtoByToken();
            if (auditOrderDTO == null) {
                throw new Exception("审核DTO不能为空！");
            }
            if (StringUtils.isBlank(auditOrderDTO.getOrderCode())) {
                throw new Exception("订单编号不能为空！");
            }
            if (StringUtils.isBlank(auditOrderDTO.getReviewTag())) {
                throw new Exception("订单标识不能为空！");
            }
            String result = ofcOrderManageService.auditStorageOrder(auditOrderDTO.getOrderCode(),auditOrderDTO.getReviewTag(), authResDtoByToken);
            if(!result.equals(String.valueOf(Wrapper.SUCCESS_CODE))){
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "审核或反审核出现异常");
            }
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
        } catch (BusinessException ex) {
            logger.error("订单中心订单管理订单审核反审核出现异常:{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("订单中心订单管理订单审核反审核出现异常:{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }
    }

    /**
     * 订单取消
     * @param orderCode     订单编号
     * @return  Wrapper
     */
    @ApiOperation(
            notes = "取消订单",
            httpMethod = "POST",
            value = "取消订单"
    )
    @RequestMapping(value = "/orderCancelOper/{orderCode}", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> orderCancelOper(@ApiParam(name = "orderCode",value = "订单号" ) @PathVariable String orderCode) {
        AuthResDto authResDtoByToken = getAuthResDtoByToken();
        try {
            if (StringUtils.isBlank(orderCode)) {
                throw new Exception("订单编号不能为空！");
            }
            String result = ofcOrderManageService.orderCancel(orderCode,authResDtoByToken);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
        } catch (BusinessException ex) {
            logger.error("订单中心订单管理订单取消出现异常orderCode：{},{}", "", orderCode, ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("订单中心订单管理订单取消出现异常orderCode：{},orderStatus：{},{}", "", orderCode, ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "订单中心订单管理订单取消出现异常");
        }
    }

    @RequestMapping(value = "/querySupplierSelect2/{customerCode}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "返回供应商列表", httpMethod = "POST", value = "Select2查询供应商")
    public Wrapper<PageInfo<Select2RespDto>> querySupplierByName(@ApiParam(name = "select2ReqDto", value = "select2查询信息") @RequestBody  Page<Select2ReqDto> page,@PathVariable String customerCode) {
        Wrapper<PageInfo<Select2RespDto>> result = new Wrapper<>();
        try {
            if(PubUtils.isSEmptyOrNull(customerCode)){
                throw new BusinessException("客户编码不能为空");
            }
            CscSupplierInfoDto queryParam = new CscSupplierInfoDto();
            queryParam.setCustomerCode(customerCode);
            queryParam.setPNum(page.getPageNum());
            queryParam.setPSize(page.getPageSize());
            queryParam.setSupplierName(page.getParam().getName());
            Wrapper<PageInfo<CscSupplierInfoDto>> pageInfoWrapper =  cscSupplierEdasService.querySupplierByAttributePageList(queryParam);
            result.setCode(pageInfoWrapper.getCode());
            result.setMessage(pageInfoWrapper.getMessage());
            PageInfo<CscSupplierInfoDto> resultForRevert = pageInfoWrapper.getResult();
            if (null == resultForRevert || CollectionUtils.isEmpty(resultForRevert.getList())) {
                logger.error("查询供应商名称Select2失败, resultForRevert:{}", ToStringBuilder.reflectionToString(resultForRevert));
                throw new BusinessException("查询供应商名称Select2失败");
            }
            PageInfo<Select2RespDto> pageInfo = new PageInfo<>();
            org.apache.commons.beanutils.BeanUtils.copyProperties(pageInfo, resultForRevert);
            pageInfo.setList(null);
            List<Select2RespDto> select2RespDtoList = new ArrayList<>();
            for (CscSupplierInfoDto cscSupplierInfoDto : resultForRevert.getList()) {
                Select2RespDto select2RespDto = new Select2RespDto();
                select2RespDto.setId(cscSupplierInfoDto.getSupplierId());
                select2RespDto.setCode(cscSupplierInfoDto.getSupplierCode());
                select2RespDto.setName(cscSupplierInfoDto.getSupplierName());
                select2RespDtoList.add(select2RespDto);
            }
            pageInfo.setList(select2RespDtoList);
            if (Wrapper.ERROR_CODE == result.getCode()) {
                logger.error("查询供应商列表失败,查询结果有误!");
            }
            result = WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, pageInfo);
        } catch (BusinessException ex) {
            logger.error("==>查询供应商列名称Select2根据客户编码查询供应商发生错误：{}", ex);
            result = WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("==>查询供应商列名称Select2根据供应商列名称查询客户发生异常：{}", ex);
            result = WrapMapper.wrap(Wrapper.ERROR_CODE, "查询供应商列名称Select2根据客户编码查询供应商列发生异常！");
        }
        return result;
    }

    /**
     * 货品类别(调用客户中心API)
     */

    @RequestMapping(value = "/getCscGoodsTypeList/{cscGoodsType}",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="下单货品筛选",  httpMethod = "POST", notes="根据查询条件筛选货品")

    public Wrapper<?> getCscGoodsTypeList(@ApiParam(name = "cscGoodsType", value = "查询条件筛选货品") @PathVariable String cscGoodsType){
        logger.info("下单货品筛选==> cscGoodsType={}", cscGoodsType);
        //调用外部接口,最低传CustomerCode
        try{
            CscGoodsTypeDto cscGoodType=new CscGoodsTypeDto();
            if(!PubUtils.trimAndNullAsEmpty(cscGoodsType).equals("")){
                cscGoodType.setPid(cscGoodsType);
            }
            Wrapper<List<CscGoodsTypeVo>> CscGoodsType = cscGoodsTypeEdasService.getCscGoodsTypeList(cscGoodType);
            logger.info("===========================" + CscGoodsType);
            logger.info("###############返回货品类别列表为{}####################",JacksonUtil.toJsonWithFormat(CscGoodsType.getResult()));
           return  WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, CscGoodsType);
        }catch (Exception ex){
            logger.error("订单中心筛选货品出现异常:{}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "根据查询条件筛选货品发生异常！");
        }
    }
}
