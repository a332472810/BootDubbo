<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title>运输计划单分配</title>

    <meta name="description" content="Static &amp; Dynamic Tables">

    <script language="javascript" type="text/javascript" src="../js/bootstrap-paginator.js"></script>
    <style type="text/css">
        #serviceProviderListDiv1 {
            position:fixed;
            left:185px;
            top:85px;
            width:1146px;
            height:500px;
            z-index:3;
            overflow: auto;
            border:solid #7A7A7A 2px;
        }
        #serviceProviderListDiv2 {
            position:fixed;
            left:185px;
            top:85px;
            width:1146px;
            height:500px;
            z-index:3;
            overflow: auto;
            border:solid #7A7A7A 2px;
        }
    </style>
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div class="modal-content" id="serviceProviderListDiv1" style="display: none;">
    <div class="modal-header"><span id="serviceProviderDivNoneTop1" style="cursor:pointer"><button type="button" id="" style="cursor:pointer" class="bootbox-close-button close" data-dismiss="modal" aria-hidden="true">×</button></span>
        <h4 class="modal-title">服务商列表</h4></div>
    <div class="modal-body">
        <div class="bootbox-body">
            <form id="goodsSelConditionForm" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name">出发地</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input  id = "goodsCodeCondition" name="goodsCode" type="text" style="color: black" class="form-control input-sm" placeholder="" aria-controls="dynamic-table">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name">目的地</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input  id = "goodsNameCondition" name="goodsName" type="text" style="color: black" class="form-control input-sm" placeholder="" aria-controls="dynamic-table">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name">服务商名称</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input  id = "goodsNameCondition" name="goodsName" type="text" style="color: black" class="form-control input-sm" placeholder="" aria-controls="dynamic-table">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name"></label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <span id="goodsSelectFormBtn1" class="btn btn-info btn-sm popover-info">筛选</span>
                        </div>
                    </div>
                </div>
            </form>
            <form class="bootbox-form">
                <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="dynamic-table_info">
                    <thead>
                    <tr role="row"><th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                        <label class="pos-rel">
                            <input type="checkbox" class="ace">
                            <span class="lbl"></span>
                        </label>
                    </th>
                    <#--<th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">序号</th>-->
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Price: activate to sort column ascending">服务商名称</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">线路名称</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">发站</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">到站</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">发车频次</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">经停</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">容量</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">温度带</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">联系人</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">联系电话</th>

                    </thead>
                    <tbody id="goodsSelectListTbody1"></tbody>
                </table>


            </form>
        </div>
    </div>
    <div class="modal-footer"><span id="goodsListDivNoneBottom1" style="cursor:pointer"><button  data-bb-handler="cancel" type="button" class="btn btn-default">Cancel</button></span><button id="goodsEnter" data-bb-handler="confirm" type="button" class="btn btn-primary">OK</button></div>
</div>
<div class="modal-content" id="serviceProviderListDiv2" style="display: none;">
    <div class="modal-header"><span id="serviceProviderDivNoneTop2" style="cursor:pointer"><button type="button" id="" style="cursor:pointer" class="bootbox-close-button close" data-dismiss="modal" aria-hidden="true">×</button></span>
        <h4 class="modal-title">服务商列表</h4></div>
    <div class="modal-body">
        <div class="bootbox-body">
            <form id="goodsSelConditionForm" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name">出发地</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input  id = "goodsCodeCondition" name="goodsCode" type="text" style="color: black" class="form-control input-sm" placeholder="" aria-controls="dynamic-table">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name">目的地</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input  id = "goodsNameCondition" name="goodsName" type="text" style="color: black" class="form-control input-sm" placeholder="" aria-controls="dynamic-table">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name">服务商名称</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input  id = "goodsNameCondition" name="goodsName" type="text" style="color: black" class="form-control input-sm" placeholder="" aria-controls="dynamic-table">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-1 no-padding-right" for="name"></label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <span id="goodsSelectFormBtn2" class="btn btn-info btn-sm popover-info">筛选</span>
                        </div>
                    </div>
                </div>
            </form>
            <form class="bootbox-form">
                <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="dynamic-table_info">
                    <thead>
                    <tr role="row"><th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                        <label class="pos-rel">
                            <input type="checkbox" class="ace">
                            <span class="lbl"></span>
                        </label>
                    </th>
                    <#--<th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">序号</th>-->
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Price: activate to sort column ascending">服务商名称</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">线路名称</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">发站</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">到站</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">发车频次</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">经停</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">容量</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">温度带</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">联系人</th>
                        <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">联系电话</th>

                    </thead>
                    <tbody id="goodsSelectListTbody2"></tbody>
                </table>


            </form>
        </div>
    </div>
    <div class="modal-footer"><span id="goodsListDivNoneBottom" style="cursor:pointer"><button  data-bb-handler="cancel" type="button" class="btn btn-default">Cancel</button></span><button id="goodsEnter" data-bb-handler="confirm" type="button" class="btn btn-primary">OK</button></div>
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container ace-save-state" id="main-container">
    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="page-header">
                    <p>
                        筛选条件
                    </p>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right" for="name">资源分配状态</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <select multiple="" class="chosen-select form-control" id="resourceAllocationStatues" name="resourceAllocationStatues" data-placeholder="请选择一个状态..." style="display: none;">
                                            <option value="10">待分配</option>
                                            <option value="20">未分配</option>
                                            <option value="30">已分配</option>
                                            <option value="40">已确定</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right" for="name">客户名称</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <input id="custName" name="custName" type="search" class="form-control" placeholder="" aria-controls="dynamic-table">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right" for="name">订单批次号</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <input id="orderBatchNumber" name="orderBatchNumber" style="color: black" type="search" class="form-control input-sm" placeholder="" aria-controls="dynamic-table">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right" for="name">订单日期</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <input id="orderTimePre" name="orderTimePre" type="datetime"  placeholder="" aria-controls="dynamic-table" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                        至
                                        <input id="orderTimeSuf" name="orderTimeSuf" type="search"  placeholder="" aria-controls="dynamic-table"onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right" for="name"></label>
                                <div class="col-sm-6">
                                    <button class="btn btn-white btn-info btn-bold filters" id="doSearch">
                                        筛选
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="page-header">
                    <p>
                        订单列表
                    </p>
                </div>
                <span style="cursor:pointer" id="serviceProviderDivBlock"><button class="btn btn-info btn-sm popover-info" id="doSelt" value="">分配资源</button></span>
                <button class="btn btn-info btn-sm popover-info" id="doEnter" >资源确认</button>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">

                        <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="dynamic-table_info">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <#--<th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">-->
                                    <#--<label class="pos-rel">-->
                                        <#--<input type="checkbox" class="ace">-->
                                        <#--<span class="lbl"></span>-->
                                    <#--</label>-->
                                <#--</th>-->
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">选择</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">订单编号</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Price: activate to sort column ascending">计划单编号</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">订单批次号</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">类型</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">资源分配状态</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">服务商名称</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">订单日期</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">出发地</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">目的地</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">数量</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">重量</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">资源确认人员</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">资源确认时间</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">服务商联系人</th>
                                <th class="" tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">服务商联系电话</th>

                            </thead>
                            <tbody id="dataTbody">
                            <#--订单列表数据-->
                            </tbody>
                        </table>
                        <div class="row">
                            <div id="pageBarDiv" style="float: right;padding-top: 0px;margin-top: 0px;">
                        </div>
                    </div>

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
</div>
</div><!-- /.main-content -->

</div><!-- /.main-container -->




<#include "common/include.ftl">
<script type="text/javascript">
    $(function () {

    });
    var scripts = [ null, "../components/chosen/chosen.jquery.js", null ]
    $(".page-content-area").ace_ajax("loadScripts", scripts, function() {
        $(document).ready(main);
        $('.chosen-select').chosen({allow_single_deselect:true});
        //resize the chosen on window resize

        $(window)
                .off('resize.chosen')
                .on('resize.chosen', function() {
                    $('.chosen-select').each(function() {
                        var $this = $(this);
                        $this.next().css({'width': $this.parent().width()});
                    })
                }).trigger('resize.chosen');
        //resize chosen on sidebar collapse/expand
        $(document).on('settings.ace.chosen', function(e, event_name, event_val) {
            if(event_name != 'sidebar_collapsed') return;
            $('.chosen-select').each(function() {
                var $this = $(this);
                $this.next().css({'width': $this.parent().width()});
            })
        });
    });

    function main(){
        //初始化页面数据
        initPageData();
        // 查询
        queryData(1);

        $("#doSearch").click(function () {
            queryData(1);
        });

    }

    //页面数据初始化
    function initPageData(){
        var active_class = "active";
        $("#simple-table > thead > tr > th input[type=checkbox]").eq(0).on("click", function(){
            var th_checked = this.checked;//checkbox inside "TH" table header

            $(this).closest("table").find("tbody > tr").each(function(){
                var row = this;
                if(th_checked) $(row).addClass(active_class).find("input[type=checkbox]").eq(0).prop("checked", true);
                else $(row).removeClass(active_class).find("input[type=checkbox]").eq(0).prop("checked", false);
            });
        });
        $("#simple-table").on("click", "td input[type=checkbox]" , function(){
            var $row = $(this).closest("tr");
            if($row.is(".detail-row ")) return;
            if(this.checked) $row.addClass(active_class);
            else $row.removeClass(active_class);
        });
        $("#serviceProviderDivBlock").click(function(){
            var serive = "";
            var i=1;
            $("#dataTbody").find("tr").each(function(index){
                var tdArr = $(this).children();
                debugger;
                if(tdArr.eq(1).find("input").prop("checked")){
                    var businessType = tdArr.eq(5).text();//计划单类型
                    var departure = tdArr.eq(9).text();//出发地
                    var destination = tdArr.eq(10).text();//    目的地
                    if(i==1){
                        serive=businessType;
                        $("#goodsCodeCondition").val(departure);
                        $("#goodsNameCondition").val(destination);
                    }
                    i=i+1;
                }
            });
            if(serive==""){
                alert("请至少选择一行");
            }else {
                if(serive=="600"){
                    $("#serviceProviderListDiv1").fadeIn("slow");//淡入淡出效果 显示div
                }else if(serive=="601"){
                    $("#serviceProviderListDiv2").fadeIn("slow");//淡入淡出效果 显示div
                }else {
                    alert("缺少类型");
                }

            }
        });

        $("#serviceProviderDivNoneTop1").click(function(){

            $("#serviceProviderListDiv1").fadeOut("slow");//淡入淡出效果 隐藏div

        });
        $("#serviceProviderDivNoneTop2").click(function(){

            $("#serviceProviderListDiv2").fadeOut("slow");//淡入淡出效果 隐藏div

        });
    }
</script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    function queryData(pageNum) {

        var param = {};

        param.pageNum = pageNum;
        param.pageSize = 10;
        var orderTimePre = $dp.$('orderTimePre').value;
        var orderTimeSuf = $dp.$('orderTimeSuf').value;
        param.orderTimePre = orderTimePre;
        param.orderTimeSuf = orderTimeSuf;
        param.custName = $("#custName").val();
        param.orderBatchNumber = $("#orderBatchNumber").val();
        var rsa = $("#resourceAllocationStatues").val();
        //alert($("#resourceAllocationStatus").val());

        debugger;
        var list = [];
        if(rsa!=null && rsa!=""){
            for(var i=0;i<rsa.length;i++){
                if(rsa[i]!=""){
                    list.push(rsa[i])
                }
            }
            param.resourceAllocationStatues=list+"*|*";
        }else {
            param.resourceAllocationStatues=rsa;
        }

        debugger;
        CommonClient.post(sys.rootPath + "/ofc/queryPlanPageByCondition", param, function(result) {

            if (result == undefined || result == null) {
                alert("HTTP请求无数据返回！");
            } else if (result.code == 200) {// 1:normal
                reloadGrid(result);// 刷新页面数据
                laypage({
                    cont: $("#pageBarDiv"), // 容器。值支持id名、原生dom对象，jquery对象,
                    pages: result.result.pages, // 总页数
                    skip: true, // 是否开启跳页
                    skin: "molv",
                    groups: 3, // 连续显示分页数
                    curr: result.result.pageNum, // 当前页
                    jump: function(obj, first){ // 触发分页后的回调
                        if(!first){ // 点击跳页触发函数自身，并传递当前页：obj.curr
                            queryData(obj.curr);
                        }
                    }
                });
            } else if (result.code == 403){
                alert("没有权限")
            } else {
                $("#dataTbody").html("");
            }
        });
    }

    function reloadGrid(data) {
        var htmlText = "";

        var length = data.result.list.length;

        if(length < 1){
            $("#dataTbody").html("");
            return;
        }

        for ( var i = 0; i < data.result.list.length; i++) {
            var order = data.result.list[i];

            htmlText +="<tr role=\"row\" class=\"odd\">"
                    +"<td>"+[i+1]+"</td>"
                    +"<td class='center'> "+"<label class='pos-rel'>"+"<input id='selGoods' type='checkbox' class='ace'>"+"<span class='lbl'></span>"+"</label>"+"</td>"
                    +"<td>"+StringUtil.nullToEmpty(order.orderCode)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.planCode)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.orderBatchNumber)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.businessType)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.resourceAllocationStatus)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.serviceProviderName)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.orderTime)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.departureProvince)+StringUtil.nullToEmpty(order.departureCity)+StringUtil.nullToEmpty(order.departureDistrict)+StringUtil.nullToEmpty(order.departureTowns)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.destinationProvince)+StringUtil.nullToEmpty(order.destinationCity)+StringUtil.nullToEmpty(order.destinationDistrict)+StringUtil.nullToEmpty(order.destinationTown)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.quantity)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.weight)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.resourceConfirmation)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.resourceConfirmationTime)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.serviceProviderContact)+"</td>"
                    +"<td class=\"hidden-480\">"+StringUtil.nullToEmpty(order.serviceProviderContactPhone)+"</td>"
                    + "</tr>";
        }
        $("#dataTbody").html(htmlText);

    }

    function getOrderStatus(status) {
        var value ="";
        if(status == '10'){
            value = "<span class=\"label label-sm label-yellow\">待审核</span>"
        }else if(status == '20'){
            value = "<span class=\"label label-sm label-warning\">已审核</span>"
        }else if(status == '30'){
            value = "<span class=\"label label-sm label-info\">执行中</span>"
        }else if(status == '40'){
            value = "<span class=\"label label-sm label-success\">已完成</span>"
        }else if(status == '50'){
            value = "<span class=\"label label-sm label-default\">已取消</span>"
        }
        return value;
    }

    function getBusiType(order) {
        var value = "";
        if(order.businessType == '600'){
            value = "城配"
        }else if(order.businessType == "601"){
            value = "干线";
        }else if(order.businessType == "610"){
            value = "销售出库";
        }else if(order.businessType == "611"){
            value = "调拨出库";
        }else if(order.businessType == "612"){
            value = "报损出库";
        }else if(order.businessType == "613"){
            value = "其他出库";
        }else if(order.businessType == "620"){
            value = "采购入库";
        }else if(order.businessType == "621"){
            value = "调拨入库";
        }else if(order.businessType == "622"){
            value = "退货入库";
        }else if(order.businessType == "623"){
            value = "加工入库";
        }
        return value;
    }

    function getOrderType(order) {
        var value = "";
        if(order.orderType == "60"){
            value = "运输订单";
        }else if(order.orderType == "61"){
            value = "仓配订单";
        }
        return value;

    }

    function getOperatorByStatus(order,index) {
        var value = "";

        var newStatus = "<button type=\"button\" id=\"review\" "+index+ " onclick=\"reviewOrder('"+order.orderCode+"','"+order.orderStatus+"')\" class=\"btn btn-minier btn-yellow\">审核</button>"
                +"<button type=\"button\" id=\"edit\" "+index+" onclick=\"editOrder('"+order.orderCode+"')\" class=\"btn btn-minier btn-success\">编辑</button>"
                +"<button type=\"button\" id=\"delete\" "+index+" onclick=\"deleteOrder('"+order.orderCode+"','"+order.orderStatus+"')\"  class=\"btn btn-minier btn-danger\">删除</button>";

        var unApproveStatus = "<button type=\"button\" id=\"rereview\" "+index+ " onclick=\"reReviewOrder('"+order.orderCode+"','"+order.orderStatus+"')\"  class=\"btn btn-minier btn-inverse\">反审核</button>";
        var cancelStatus = "<button type=\"button\" id=\"cancel\" "+index+ " onclick=\"cancelOrder('"+order.orderCode+"','"+order.orderStatus+"')\"  class=\"btn btn-minier btn-default\">取消</button>";

        if (order.orderStatus == "10") {
            value = newStatus;
        }
        if (order.orderStatus == "20" || order.orderStatus == "30") {
            value = unApproveStatus + cancelStatus;
        }
        return value;
    }



</script>
<script type="text/javascript">
    // 查询
    $("#screenOrderFormBtn").click(function () {
        debugger;
        var jsonStr={};
        var orderTimePre = $dp.$('orderTimePre').value;
        console.log(orderTimePre);
        jsonStr.orderTimePre=orderTimePre;
        var orderTimeSuf = $dp.$('orderTimeSuf').value;
        console.log(orderTimeSuf);
        jsonStr.orderTimeSuf=orderTimeSuf;
        jsonStr.orderCode=$("#orderCode").val();
        jsonStr.custOrderCode=$("#custOrderCode").val();
        jsonStr.orderStatus=$("#orderStatus").val();
        jsonStr.orderType=$("#orderType").val();
        jsonStr.businessType=$("#businessType").val();
        var tag = "screen";
        var orderScreenConditionJSON = JSON.stringify(jsonStr);
        var currPage = "1";
        var pageSize = "10";
        var url = "/ofc/orderScreenByCondition/" + orderScreenConditionJSON + "/" + tag + "/" + currPage + "/" + pageSize;
        xescm.common.loadPage(url);
    });
    var time = 1;
    $('#pageLimit').bootstrapPaginator({
        currentPage: ${currPage!"1"},//当前页码
        totalPages: ${totalPage!"1"}, //总页数
        size:"normal",
        bootstrapMajorVersion: 3,
        alignment:"right",
        numberOfPages:10,//每页显示多少
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },shouldShowPage:function(type,page,current){
            if(page == 1 && time == 1){
                return false;
            }else if(current == 1){
                return true;
            }else{
                return true;
            }
        },onPageClicked:function (event, originalEvent, type, page) {//异步刷新页面
            var jsonStr={};
            var orderTimePre = $dp.$('orderTimePre').value;
            jsonStr.orderTimePre=orderTimePre;
            var orderTimeSuf = $dp.$('orderTimeSuf').value;
            jsonStr.orderTimeSuf=orderTimeSuf;
            jsonStr.orderCode=$("#orderCode").val();
            jsonStr.custOrderCode=$("#custOrderCode").val();
            jsonStr.orderStatus=$("#orderStatus").val();
            jsonStr.orderType=$("#orderType").val();
            jsonStr.businessType=$("#businessType").val();
            var tag = "screen";
            var orderScreenConditionJSON = JSON.stringify(jsonStr);
            var currPage = page;
            var pageNum = "10";
            var url = "/ofc/orderScreenByCondition/" + orderScreenConditionJSON + "/" + tag + "/" + currPage + "/" + pageNum;
            xescm.common.loadPage(url);

        }
    });
</script>
<link rel="stylesheet" href="../components/chosen/chosen.css" />
<script src="../components/chosen/chosen.jquery.js"></script>

</body>
