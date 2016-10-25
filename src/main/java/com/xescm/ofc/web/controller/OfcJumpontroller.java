package com.xescm.ofc.web.controller;

import com.xescm.ofc.domain.OfcWarehouseInformation;
import com.xescm.ofc.feign.client.FeignCscCustomerAPIClient;
import com.xescm.ofc.feign.client.FeignCscGoodsAPIClient;
import com.xescm.ofc.feign.client.FeignCscSupplierAPIClient;
import com.xescm.ofc.feign.client.FeignCscWarehouseAPIClient;
import com.xescm.ofc.service.OfcWarehouseInformationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by lyh on 2016/10/18.
 */
@Controller
public class OfcJumpontroller extends BaseController{

    @Autowired
    private OfcWarehouseInformationService ofcWarehouseInformationService;




    @RequestMapping(value="/ofc/orderPlace")
    public ModelAndView index(Model model,Map<String,Object> map ,String custCode, HttpServletRequest request, HttpServletResponse response){
        custCode = "2";
        List<OfcWarehouseInformation> warehouseListByCustCode = ofcWarehouseInformationService.getWarehouseListByCustCode(custCode);
        map.put("warehouseListByCustCode",warehouseListByCustCode);
        return new ModelAndView("order_place");
    }

    @RequestMapping(value = "/ofc/orderManage")
    public String orderManage(Model model){
        return "order_manage";
    }

    @RequestMapping(value = "/ofc/orderScreen")
    public String orderScreen(Model model){
        return "order_screen";
    }

    @ApiOperation(value="追踪订单", notes="根据code和followTag来追踪订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "订单编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "followTag", value = "追踪标记", required = true, dataType = "String")
    })

    @RequestMapping(value="/ofc/orderFollow",method = RequestMethod.GET)
    public String orderFollow(Model model, String code, String followTag, Map<String,Object> map){
        logger.debug("==>订单中心订单追踪条件筛选code code={}", code);
        logger.debug("==>订单中心订单追踪条件标志位 followTag={}", followTag);
        return "order_follow";
    }

    @RequestMapping(value = "/index")
    public String toIndex(Model model){

        return "index";
    }
}
