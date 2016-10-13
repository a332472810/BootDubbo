<html>
<head>

</head>
<body>
    <h2>订单跟踪</h2>
    <form action="/orderFollowCon" method="post">
    <select id="u200_input" data-label="lsl_条件类型" name="followTag">
        <option value="orderCode">订单编号</option>
        <option value="custOrderCode">客户订单编号</option>
        <option value="transCode">运输单号</option>
    </select>
    <input  id="" name="code"/> <button type="submit">查询</button>

    </form>
    <h2>订单跟踪记录</h2>
    订单日期
    <#--${ofcOrderDTO.orderTime?string("MM-dd-yyyy")}&nbsp;&nbsp;&nbsp;-->
    订单编号
    ${orderCode!"null"}&nbsp;&nbsp;&nbsp;
    客户订单编号
    ${custOrderCode!"null"}&nbsp;&nbsp;&nbsp;
    订单状态
    ${orderStatus!"null"}&nbsp;&nbsp;&nbsp;
    订单类型
    ${orderType!"null"}&nbsp;&nbsp;&nbsp;
    业务类型
    ${businessType!"null"}&nbsp;&nbsp;&nbsp;

    备注
    ${notes!"null"}&nbsp;&nbsp;&nbsp;

    货品类型
    ${goodsType!"null"}&nbsp;&nbsp;&nbsp;
    出发地
    ${departurePlace!"null"}&nbsp;&nbsp;&nbsp;
    目的地

    ${destination!"null"}&nbsp;&nbsp;&nbsp;

    数量
    ${quantity!"null"}&nbsp;&nbsp;&nbsp;


    重量

    ${weight!"null"}&nbsp;&nbsp;&nbsp;
    体积

    ${cubage!"null"}&nbsp;&nbsp;&nbsp;

    运输要求

    ${transRequire!"null"}&nbsp;&nbsp;&nbsp;

    运输单号

    ${transCode!"null"}&nbsp;&nbsp;&nbsp;

    车牌号
    ${plateNumber!"null"}&nbsp;&nbsp;&nbsp;

    司机姓名

    ${driverName!"null"}&nbsp;&nbsp;&nbsp;

    联系电话
    ${contactNumber!"null"}&nbsp;&nbsp;&nbsp;

    收货地址
    ${odestination!"null"}&nbsp;&nbsp;&nbsp;

    <hr />

<#--下面是订单状态信息-->
    订单跟踪信息
    <table class="table table-hover" width="80%" align="center">
        <!-- On rows -->
        <tr class="active" style="height: 15px">
            <td style="width: 50%;">订单状态</td>

        </tr>
    <#--
    vo  value object  拼出来的结果集
    dto  参数,给后端当参数,或者返回的dot.
    -->
    <#list orderStatusList! as orderStatus>
        <tr class="active">

            <td style="width: 10%;">${orderStatus.statusDesc!"null"}</td>
        </tr>
    </#list>
    </table>

</body>