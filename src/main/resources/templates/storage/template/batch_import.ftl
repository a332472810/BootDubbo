<link rel="stylesheet" href="/components/select2.v3/select2.min.css" />
<link rel="stylesheet" href="/components/select2.v3/select2-bootstrap.css" />
<style type="text/css">
    @import url("//unpkg.com/element-ui/lib/theme-default/index.css");
</style>

<style>
  .el-upload__input{
    opacity:0;
  }
</style>
<span hidden="true" id = "ofc_web_url">${(OFC_WEB_URL)!}</span>
<div id="app">
    <title>{{titleName}}</title>
    <div class="list-mian-01">
        <el-form :model="templateBatchIn"  label-width="100px" class="demo-ruleForm" v-loading="loading2"
                 element-loading-text="拼命加载中">
            <div class="xe-block" >
                <el-form-item label="客户名称"  class="xe-col-3" requird>
                    <el-input v-model="templateBatchIn.custName" v-if="custNameShow"   ></el-input>
                    <el-input v-model="templateBatchIn.custCode" v-if="custCodeShow"   ></el-input>
                    <input class="form-control select2-single"  name="custName" id="custName" placeholder="请输入客户名称"/>
                    <input  hidden name="custCode" id="custCode"  />
                </el-form-item>
            </div>
            <div class="xe-block">
                <el-form-item label="模板名称"   class="xe-col-3" requird>
                    <el-select placeholder="请选择" @change="templateChange(templateBatchIn.templateName)" v-model="templateBatchIn.templateName">
                        <el-option  v-for="item in templateNameList" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item class="xe-col-1">
                    <a  id="tempIn" href="${(OFC_WEB_URL)!}/templates/template_in_bound_list.xlsx">入库单导入标准模板.xls</a>
                    <a  id="tempOut" href="${(OFC_WEB_URL)!}/templates/template_out_bound_list.xlsx">出库单导入标准模板.xls</a>
                </el-form-item>
            </div>
            <div class="xe-block" style="margin-left:100px;">
                <el-upload  :action="uploadAction" type="drag" :data="uploadParam" :accept="fileTypeAccept" :on-change="uploadChange" :on-progress="uploading"
                              :multiple="false" :before-upload="beforeUpload" :on-preview="handlePreview" :on-remove="handleRemove" :on-success="handleSuccess"
                              :on-error="handleError" :default-file-list="fileList">
                    <i class="el-icon-upload"></i>
                    <div class="el-dragger__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip">只能上传xls/xlsx文件，且不超过500kb</div>
                </el-upload>
            </div>
        </el-form>

        <el-table
                :data="tableData"
                v-if="errorMsgShow"
                highlight-current-row
                border
                style="width: 100%">
            <el-table-column
                    type="index"
                    label="序号">
            </el-table-column>
            <el-table-column
                    property="errorMsg"
                    label="错误信息">

            </el-table-column>
        </el-table>

        <el-table
                :data="orderTableData"
                v-if="orderMsgShow"
                highlight-current-row
                border
                style="width: 100%">
            <el-table-column
                    type="index"
                    label="序号">
            </el-table-column>
            <el-table-column
                    v-for="item in orderTableHeads"
                    :property="item.propertyCode"
                    :label="item.propertyName">

            </el-table-column>
        </el-table>
        <div class="xe-block">
            <el-button type="primary"  v-if="orderMsgShow" v-on:click="orderSaveBtn" icon="save">执行批量导入</el-button>
        </div>

    </div>



</div>
<script type="text/javascript">

    var scripts = [null,
        "/components/select2.v3/select2.min.js",
        "/components/select2.v3/select2_locale_zh-CN.js",
        null];

    $(".page-content-area").ace_ajax("loadScripts", scripts, function () {
        $(document).ready(main);
    });

    function main() {
        initCustomerName();
        $("#custName").on("select2-selecting", function(e) {
            vm.templateBatchIn.custName = e.choice.name;
            vm.templateBatchIn.custCode = e.choice.code;
            //加载当前客户下所有模板!
            var param = {};
            param.custCode = e.choice.code;
            CommonClient.syncpost("/ofc/storage_template/templist", param, function (result) {
                vm.templateNameList = [];
                var templateNameList = vm.templateNameList;
                if(result == null || result.length == 0) {
                    return;
                }
                $.each(result, function (index, item) {
                    if(index == 0) {
                        vm.templateBatchIn.templateName = item.templateCode;
                    }
                    var templateName = {};
                    templateName.label = item.templateName;
                    templateName.value = item.templateCode;
                    templateNameList.push(templateName);
                });

            });
            vm.uploadParam = {"custCode":vm.templateBatchIn.custCode, "custName":vm.templateBatchIn.custName
                , "templateCode":vm.templateBatchIn.templateName, "templateType":vm.templateType};
        });
        if(vm.templateType == 'storageIn'){
            $("#tempIn").show();
            $("#tempOut").hide();
        }else if (vm.templateType == 'storageOut'){
            $("#tempIn").hide();
            $("#tempOut").show();
        }

    }

    function initCustomerName() {
        var ofc_web_url = $("#ofc_web_url").html();
        var url = ofc_web_url + "/ofc/distributing/queryCustomerSelect2";
        var notice = "没有找到相关客户";
        Select2Util.singleSelectInit("#custName",url,notice,"#custCode");
    }


    var Main = {
        data() {
            return {
                loading2:false,
                templateType:'${templateType!}',
                titleName:'${templateType!}' == 'storageIn' ? '入库开单_批量导入' : '出库开单_批量导入',
                <#--standardTemplteTitle:'${templateType!}' == 'storageIn' ? '入库单导入标准模板.xls' : '出库单导入标准模板.xls',-->
                orderList:'',
                orderTableHeads:[],
                orderTableData:[],
                orderMsgShow:false,
                tableData:[],
                errorMsgShow:false,
                uploadParam:{custCode:'', custName:'', templateCode:'', templateType:''},
                custNameShow:false,
                custCodeShow:false,
                fileList: [],
                excel:'',
                fileTypeAccept:'.xls,.xlsx',
                uploadAction:'${OFC_WEB_URL!}/ofc/storage_template/batch_import_upload',
                authResDto:'',
                templateBatchIn:{
                    custName:'',
                    custCode:'',
                    templateName:''
                },
                pageSheetList:[],
                templateNameList:[]
            };
        },
        methods: {
            templateChange(val){
                var vm = this;
                if(undefined == val || StringUtil.isEmpty(val)){
                    return;
                }
                vm.uploadParam = {"custCode":vm.templateBatchIn.custCode, custName:vm.templateBatchIn.custName, "templateCode":val, "templateType":vm.templateType};
            },
            handleRemove(file, fileList) {
                this.errorMsgShow = false;
                this.orderMsgShow = false;
                this.fileList = [];
            },
            handlePreview(file) {
            },
            handleSuccess(response, file, fileList) {
                var vm = this;
                vm.loading2 = false;
                vm.orderList = '';
                if(response.code == 500) {

                    vm.errorMsgShow = true;
                    vm.orderMsgShow = false;
                    vm.$message.error(response.message);
                    vm.fileList = [];
                    var tableData = vm.tableData = [];
                    if(undefined == response.result || null == response.result){
                        return;
                    }
                    $.each(response.result, function (index, item) {
                        var rowData = {};
                        rowData.errorMsg = item;
                        tableData.push(rowData)
                    });
                }else if(response.code == 501 || response.code == 502 || response.code == 503) {
                    
                    vm.errorMsgShow = false;
                    vm.fileList = [];
                    vm.$message.error(response.message);
                    vm.tableData = [];
                    vm.orderMsgShow = false;
                }else if(response.code == 200) {

                    vm.fileList.push(file);
                    var tableData = vm.orderTableData = [];
                    vm.$message(response.message);
                    vm.errorMsgShow = false;
                    var tableHeadMsg = response.result[0];
                    var orderMsg = response.result[1];
                    var headData = vm.orderTableHeads = [];
                    vm.orderMsgShow = true;
                    $.each(tableHeadMsg, function (index, itemIn) {
                        var items = itemIn.split('@');
                        var propertyName = items[0];
                        var propertyCode = items[1];
                        var head = {};
                        head.propertyName = propertyName;
                        head.propertyCode = propertyCode;
                        headData.push(head);
                    });

                    $.each(orderMsg, function (indexOut, itemOut) {
                        var tableRow = {};
                        $.each(tableHeadMsg, function (indexIn, itemIn) {
                            var items = itemIn.split('@');
                            var propertyCode = items[1];
                            tableRow[propertyCode] = itemOut[propertyCode];
                        });
                        tableData.push(tableRow);
                    });
                    vm.orderList = orderMsg;
                }
            },
            handleError(err, response, file) {
                var vm = this;
                vm.loading2 = false;
            },
            beforeUpload(file){
                
                var vm = this;
                //必须选好客户和模板
                if(undefined == vm.templateBatchIn.custName || StringUtil.isEmpty(vm.templateBatchIn.custName)){
                    vm.$message.error("请先选择客户!");
                    vm.fileList = [];
                    return false;
                }
                //限制只允许上传一个文件
                var fileList = vm.fileList;
                if(fileList.length > 0){
                    vm.$message.error("只允许上传一个文件!");
                    return false;
                }
            },
            uploading(event, file, fileList){
                var vm = this;
                var fileList = vm.fileList;
                if(fileList.length > 0){
                    vm.$message.error("只允许上传一个文件!");
                    return false;
                }
                vm.loading2 = true;
                //文件大小限制
            },
            uploadChange(file,fileList){
                var vm = this;
                if(fileList.length > 0){
                    vm.$message.error("只允许上传一个文件!");
                    return false;
                }
                //必须选好客户和模板
                /*if(undefined == vm.templateBatchIn.custName || StringUtil.isEmpty(vm.templateBatchIn.custName)){
                    vm.$message.error("请先选择客户!")
                    return;
                }*/
            },
            orderSaveBtn(){
                var vm = this;
                var param = {};
                param.orderList = JSON.stringify(vm.orderList);
                var url = "/ofc/storage_template/confirm";
                var inOrOut = vm.templateType == "storageIn" ? "入库" : "出库";
                xescm.common.submit(url, param, "您确定进行批量导入" + inOrOut + "订单吗？请确认数据无误后，继续操作!", function () {
                    var url = vm.templateType == "storageIn" ? "/ofc/orderStorageInManager" : "/ofc/orderStorageOutManager";
                    xescm.common.loadPage(url);
                });
            }
        }
    };
    var Ctor = Vue.extend(Main);
    var vm = new Ctor().$mount('#app')
</script>