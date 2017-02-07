package com.xescm.ofc.mapper;

import com.xescm.ofc.domain.OrderScreenCondition;
import com.xescm.ofc.domain.OrderScreenResult;
import com.xescm.ofc.model.dto.form.OrderOperForm;
import com.xescm.ofc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * Created by lyh on 2016/10/10.
 */

/*@Service用于标注业务层组件

@Controller用于标注控制层组件

@Repository用于标注数据访问组件，即DAO组件

@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。*/
@Component
public interface OfcOrderScreenMapper extends MyMapper<OrderScreenResult> {
    /**
     * 订单查询结果来自于
     * 订单基本信息表的: 订单编号, 客户订单编号, 订单日期, 订单类型, 业务类型
     * 订单状态表的: 订单状态
     * 配送基本信息表的: 收货方名称
     * 仓配信息表的: 仓库名称
     * 订单基本信息表的:备注
     * 订单基本信息表的: 店铺名称
     */
    /**
     * 订单筛选
     * @return
     */
    List<OrderScreenResult> orderScreen(OrderScreenCondition orderScreenCondition);

    /**
     * 查询订单--运营
     * @param form
     * @return List<OrderScreenResult>
     */
    List<OrderScreenResult> queryOrderOper(@Param(value = "form") OrderOperForm form);
}
