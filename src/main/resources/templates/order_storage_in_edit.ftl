<!DOCTYPE html>
<html>
<head>
    <style lang="css">
        .block {
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div id="app">
    <div class="list-mian-01">
        <el-dialog title="选择客户" v-model="customerDataInfo.chosenCus" size="small">
            <el-form :model="customerDataInfo.chosenCusForm">
                <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="customerDataInfo.chosenCusForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="" :label-width="formLabelWidth">
                    <el-button type="primary" @click="selectCustomer">筛选</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="customerDataInfo.customerData" highlight-current-row @current-change="handleCurrentChange" style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column property="custCode" label="客户编码"></el-table-column>
                <el-table-column property="type" label="类型"></el-table-column>
                <el-table-column property="custName" label="公司名称"></el-table-column>
                <el-table-column property="channel" label="渠道"></el-table-column>
                <el-table-column property="productType" label="产品类别"></el-table-column>
            </el-table>
            <el-pagination @size-change="handleCustomerSizeChange" @current-change="handleCustomerCurrentPage" :current-page="customerDataInfo.currentCustomerPage" :page-sizes="pageSizes" :page-size="customerDataInfo.customerPageSize" :total="customerDataInfo.total" layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelSelectCustomer">取 消</el-button>
                <el-button type="primary" @click="setCurrentCustInfo(customerDataInfo.currentRow)">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="发货方联系人" v-model="consignorDataInfo.chosenSend" size="small">
            <el-form :model="consignorDataInfo.consignorForm">
                <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="consignorDataInfo.consignorForm.consignorName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系人" :label-width="formLabelWidth">
                    <el-input v-model="consignorDataInfo.consignorForm.consignorContactName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" :label-width="formLabelWidth">
                    <el-input v-model="consignorDataInfo.consignorForm.consignorContactPhone" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="" :label-width="formLabelWidth">
                    <el-button type="primary" @click="selectConsignor">筛选</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="consignorDataInfo.consignorData" highlight-current-row @current-change="consignorHandleCurrentChange" border style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column property="consignorName" label="名称"></el-table-column>
                <el-table-column property="consignorContactName" label="联系人"></el-table-column>
                <el-table-column property="consignorContactPhone" label="联系电话"></el-table-column>
                <el-table-column property="departurePlace" label="地址"></el-table-column>
                <el-table-column property="consignorCode" label="发货方编码"></el-table-column>
                <el-table-column property="consignorType" label="发货方类型"></el-table-column>
                <el-table-column property="consignorContactCode" label="发货方联系人编码"></el-table-column>
                <el-table-column property="departurePlaceCode" label="发货方地址编码"></el-table-column>
            </el-table>
            <el-pagination @size-change="handleConsignorSizeChange" @current-change="handleConsignorCurrentPage" :current-page="consignorDataInfo.currentConsignorPage" :page-sizes="pageSizes" :page-size="consignorDataInfo.consignorPageSize" layout="total, sizes, prev, pager, next, jumper" :total="consignorDataInfo.totalConsignor">
            </el-pagination>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelSelectConsignor">取 消</el-button>
                <el-button type="primary" @click="setCurrentConsignorInfo(consignorDataInfo.consignorCurrentRow)">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="供应商信息" v-model="supplierDataInfo.chosenSupplier" size="small">
            <el-form :model="supplierDataInfo.supplierForm">
                <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="supplierDataInfo.supplierForm.supportName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系人" :label-width="formLabelWidth">
                    <el-input v-model="supplierDataInfo.supplierForm.contactName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" :label-width="formLabelWidth">
                    <el-input v-model="supplierDataInfo.supplierForm.contactPhone" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item  label="" :label-width="formLabelWidth">
                    <el-button type="primary" @click="selectSupplier">筛选</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="supplierDataInfo.supplierData" highlight-current-row @current-change="handlSuppliereCurrentChange" style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column property="supportName" label="名称"></el-table-column>
                <el-table-column property="contactName" label="联系人"></el-table-column>
                <el-table-column property="contactPhone" label="联系电话"></el-table-column>
                <el-table-column property="fax" label="传真"></el-table-column>
                <el-table-column property="email" label="邮箱"></el-table-column>
                <el-table-column property="postCode" label="邮编"></el-table-column>
                <el-table-column property="supportCode" label="供应商编码"></el-table-column>
                <el-table-column property="completeAddress" label="地址"></el-table-column>
            </el-table>
            <el-pagination @size-change="handleSupplierSizeChange" @current-change="handleSupplierCurrentPage" :current-page="supplierDataInfo.currentSupplierPage" :page-sizes="pageSizes" :page-size="supplierDataInfo.supplierPageSize" layout="total, sizes, prev, pager, next, jumper" :total="supplierDataInfo.totalSupplier">
            </el-pagination>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelSelectSupplier">取 消</el-button>
                <el-button type="primary" @click="setCurrentSupplierInfo(supplierDataInfo.supplierCurrentRow)">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="货品列表" v-model="goodDataInfo.chosenGoodCode" size="small">
            <el-form :model="goodDataInfo.goodsForm">
                <el-form-item label="货品种类" :label-width="formLabelWidth">
                    <template scope="scope">
                        <el-select size="small" v-model="goodDataInfo.goodsForm.goodsTypeId"  @change="getGoodsCategory" placeholder="请选择" >
                            <el-option
                                    v-for="item in goodsMsgOptions"
                                    :label="item.label"
                                    :value="item.value"
                                    style="width:100px;">
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
                <el-form-item label="货品小类" :label-width="formLabelWidth">
                    <template scope="scope">
                        <el-select  size="small" v-model="goodDataInfo.goodsForm.goodsTypeSonId"   placeholder="请选择">
                            <el-option
                                    v-for="subitem in goodsCategoryOptions"
                                    :label="subitem.label"
                                    :value="subitem.value"
                                    style="width:100px;">
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
                <el-form-item label="货品名称" :label-width="formLabelWidth">
                    <el-input v-model="goodDataInfo.goodsForm.goodsName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="条形码" :label-width="formLabelWidth">
                    <el-input v-model="goodDataInfo.goodsForm.barCode" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="" :label-width="formLabelWidth">
                    <el-button type="primary" @click="selectGoods">筛选</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="goodDataInfo.goodsCodeData" highlight-current-row @current-change="handlGoodCurrentChange" style="width: 100%">
                <el-table-column type="index" label="序号"></el-table-column>
                <el-table-column property="goodsType" label="货品类别"></el-table-column>
                <el-table-column property="goodsCategory" label="货品小类"></el-table-column>
                <el-table-column property="goodsBrand" label="品牌"></el-table-column>
                <el-table-column property="goodsCode" label="货品编码"></el-table-column>
                <el-table-column property="goodsName" label="货品名称"></el-table-column>
                <el-table-column property="goodsSpec" label="规格"></el-table-column>
                <el-table-column property="unit" label="单位"></el-table-column>
                <el-table-column property="barCode" label="条形码"></el-table-column>
            </el-table>
            <el-pagination @size-change="handleGoodSizeChange" @current-change="handleGoodCurrentPage" :current-page="goodDataInfo.currentGoodPage" :page-sizes="pageSizes" :page-size="goodDataInfo.goodPageSize" layout="total, sizes, prev, pager, next, jumper" :total="goodDataInfo.totalGoods">
            </el-pagination>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelSelectGood">取 消</el-button>
                <el-button type="primary" @click="setCurrentGoodsInfo(goodDataInfo.goodCurrentRow)">确 定</el-button>
            </div>
        </el-dialog>
          <div class="xe-pageHeader">
            基本信息
          </div>
      <el-form :model="orderForm" ref="orderForm" :rules="rules"  label-width="100px" class="demo-ruleForm">
          <div class="xe-block">
            <el-form-item label="订单日期" required prop="orderDate" class="xe-col-3">
              <el-date-picker
                      v-model="orderForm.orderDate"
                      align="right"
                      type="date"
                      placeholder="选择日期"
                      :picker-options="pickerOptions">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="开单员" required prop="merchandiser" class="xe-col-3">
              <el-input v-model="orderForm.merchandiser" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="客户名称" required prop="custName" class="xe-col-3">
              <el-input
                      placeholder="请选择"
                      icon="search"
                      v-model="orderForm.custName"
                      @click="customerDataInfo.chosenCus = true">
              </el-input>
            </el-form-item>
          </div>
          <div class="xe-block">
            <el-form-item label="仓库名称" required prop="wareHouse" class="xe-col-3">
              <el-select v-model="orderForm.wareHouse" placeholder="请选择">
                <el-option
                        v-for="item in wareHouseOptions"
                        :label="item.label"
                        :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="业务名称" required prop="businessType" class="xe-col-3">
              <el-select v-model="orderForm.businessType" placeholder="请选择">
                <el-option
                        v-for="item in businessTypeOptions"
                        :label="item.label"
                        :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="客户订单号"  prop="custOrderCode" class="xe-col-3">
              <el-input v-model="orderForm.custOrderCode" placeholder="请输入内容"></el-input>
            </el-form-item>
          </div>
          <div class="xe-block">
            <el-form-item label="供应商名称" class="xe-col-3">
              <el-input
                      placeholder="请选择"
                      icon="search"
                      v-model="orderForm.supportName"
                      v-bind:disabled = "isDisabled"
                      @click="supplierDataInfo.chosenSupplier = true">
              </el-input>
            </el-form-item>
            <el-form-item label="备注" prop="notes" class="xe-col-3">
              <el-input type="textarea" placeholder="请输入内容" v-model="orderForm.notes">
            </el-form-item>
          </div>

          <div class="xe-pageHeader">
            运输信息
          </div>
          <div class="xe-block">
            <el-form-item label="预计入库时间" class="xe-col-3">
              <el-date-picker
                    v-model="orderForm.arriveTime"
                    align="right"
                    type="date"
                    placeholder="选择日期"
                    :picker-options="pickerOptions1">
            </el-date-picker>
            </el-form-item>
            <el-form-item label="是否提供运输" class="xe-col-3">
              <el-checkbox v-model="orderForm.isNeedTransport" @click="isNeedTransport = true"></el-checkbox>
            </el-form-item>
            <el-form-item label="车牌号"  prop="plateNumber" class="xe-col-3">
              <el-input v-model="orderForm.plateNumber" placeholder="请输入内容"></el-input>
            </el-form-item>
          </div>
          <div class="xe-block">
            <el-form-item label="司机姓名" prop="driverName" class="xe-col-3">
              <el-input v-model="orderForm.driverName" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="contactNumber" class="xe-col-3">
              <el-input v-model="orderForm.contactNumber" placeholder="请输入内容"></el-input>
            </el-form-item>
          </div>
          <div class="xe-pageHeader">
            发货方信息
          </div>
          <div class="xe-block">
            <el-form-item label="名称" class="xe-col-3">
              <el-input
                      placeholder="请选择"
                      icon="search"
                      v-model="orderForm.consignorName"
                      v-bind:disabled = "isDisabled"
                      @click="consignorDataInfo.chosenSend = true">
              </el-input>
            </el-form-item>
            <el-form-item label="联系人" class="xe-col-3">
              <el-input v-model="orderForm.consignorContactName" placeholder="请输入内容" :readOnly="true"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" class="xe-col-3">
              <el-input v-model="orderForm.consignorContactPhone" placeholder="请输入内容" :readOnly="true"></el-input>
            </el-form-item>
          </div>
          <div class="xe-block">
            <el-form-item label="地址" class="xe-col-3">
              <el-input v-model="orderForm.departurePlace" placeholder="请输入内容" :readOnly="true"></el-input>
            </el-form-item>
          </div>
          <div class="xe-pageHeader">
            货品信息
          </div>
          <el-table :data="goodsData" border highlight-current-row @current-change="GoodsCurrentChange" style="width: 100%">
              <el-table-column property="goodsType" label="货品种类">
                  <template scope="scope">
                      <el-input v-model="scope.row.goodsType" :readOnly="true"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="goodsCategory" label="货品小类">
                  <template scope="scope">
                      <el-input v-model="scope.row.goodsCategory" :readOnly="true"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="goodsCode" label="货品编码">
                  <template scope="scope">
                      <el-input v-model="scope.row.goodsCode" :readOnly="true"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="goodsName" label="货品名称">
                  <template scope="scope">
                      <el-input v-model="scope.row.goodsName" :readOnly="true"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="goodsSpec" label="规格">
                  <template scope="scope">
                      <el-input v-model="scope.row.goodsSpec" :readOnly="true"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="unit" label="单位">
                  <template scope="scope">
                      <el-input v-model="scope.row.unit" :readOnly="true"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="quantity" label="入库数量">
                  <template scope="scope">
                      <el-input v-model="scope.row.quantity" placeholder="请输入内容"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="unitPrice" label="单价">
                  <template scope="scope">
                      <el-input v-model="scope.row.unitPrice" placeholder="请输入内容"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="productionBatch" label="批次号">
                  <template scope="scope">
                      <el-input v-model="scope.row.productionBatch"  placeholder="请输入内容"></el-input>
                  </template>
              </el-table-column>
              <el-table-column property="productionTime" label="生产日期">
                  <template scope="scope">
                      <el-date-picker
                              v-model="scope.row.productionTime"
                              align="right"
                              type="date"
                              placeholder="选择日期"
                              :picker-options="pickerOptions1">
                      </el-date-picker>
                  </template>
              </el-table-column>
              <el-table-column property="invalidTime" label="失效日期">
                  <template scope="scope">
                      <el-date-picker
                              v-model="scope.row.invalidTime"
                              align="right"
                              type="date"
                              placeholder="选择日期"
                              :picker-options="pickerOptions1">
                      </el-date-picker>
                  </template>
              </el-table-column>
              <el-table-column property="goodsOperation" label="操作">
                  <template scope="scope">
                      <el-button type="text" @click="deleteRow(scope.$index, goodsData)">删除</el-button>
                  </template>
              </el-table-column>
          </el-table>
        <div class="block">
          <el-button @click="addGoods">添加货品</el-button>
          <el-button type="primary" @click="submitForm('orderForm')">确认下单</el-button>
        </div>

        </el-form>
    </div>
</div>
</body>
<script>
    new Vue({
        el: '#app',
        data :function() {
            var validateOrdeTime = function(rule, value, callback){
                
                if(value.getTime()<new Date().getTime() - 3600 * 1000 * 24 * 7){
                    callback(new Error('只能选择一周之前的日期!'));
                }else if(value.getTime()>new Date().getTime()){
                    callback(new Error('只能选择一周之前的日期!'));
                }else{
                    callback();
                }
            };
            var checkPhoneOrMobile = function(rule, value, callback) {
                if(value!==""){
                    var mp=/^1\d{10}$/;
                    var pp=/^\d{3,4}-\d{3,8}(-\d{3,4})?$/;
                    var phone = pp.test(value)||mp.test(value);
                    if(phone!==true){
                        callback(new Error('请正确输入联系电话'));
                    }else{
                        callback();
                    }
                }else{
                    callback();
                }
            };
            return {
                activeNames:'',
                wareHouseObj:'',
                goodsCategoryOptions:[],
                customerDataInfo:{
                    currentCustomerPage:1,
                    customerPageSize:10,
                    total:0,
                    currentRow:null,
                    customerData:[],
                    chosenCus:false,
                    chosenCusForm: {
                        name: ''
                    }
                },
                consignorDataInfo:{
                    currentConsignorPage:1,
                    consignorPageSize:10,
                    totalConsignor:0,
                    consignorCurrentRow:null,
                    consignorData:[],
                    chosenSend:false,
                    consignorForm: {
                        consignorName: '',
                        consignorContactName: '',
                        consignorContactPhone:''
                    }
                },
                supplierDataInfo:{
                    currentSupplierPage:1,
                    supplierPageSize:10,
                    totalSupplier:0,
                    supplierCurrentRow:null,
                    supplierData:[],
                    chosenSupplier:false,
                    supplierForm:{
                        supportName:'',
                        contactName:'',
                        contactPhone:''
                    }
                },
                goodDataInfo:{
                    currentGoodPage:1,
                    goodPageSize:10,
                    totalGoods:0,
                    goodCurrentRow:null,
                    goodsCodeData:[],
                    chosenGoodCode:false,
                    goodsForm:{
                        goodsName:'',
                        goodsTypeId:'',
                        goodsTypeSonId:'',
                        barCode:''
                    }
                },
                currentRowData : '',
                wareHouseOptions:[],
                businessTypeOptions: [{
                    value: '620',
                    label: '采购入库'
                }, {
                    value: '621',
                    label: '调拨入库'
                }, {
                    value: '622',
                    label: '退货入库'
                }, {
                    value: '623',
                    label: '加工入库'
                }, {
                    value: '624',
                    label: '盘盈入库'
                }, {
                    value: '625',
                    label: '流通入库'
                }, {
                    value: '626',
                    label: '其他入库'
                }],
                goodsMsgOptions: [],
                pickerOptions1: {
                    shortcuts: [{
                        text: '今天',
                        onClick:function(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick:function(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick:function(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                pickerOptions: {
                    shortcuts: [{
                        text: '今天',
                        onClick:function(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick:function(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick:function(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                pageSizes:[10, 20, 30, 40,50],
                formLabelWidth: '100px',
                isDisabled: false,
                goodsData: [],
                orderForm:{
                    orderDate:new Date(),
                    merchandiser:'${merchandiser!}',
                    custName:'',
                    custCode:'',
                    wareHouse:'',
                    businessType:'',
                    custOrderCode:'',
                    notes:'',
                    supportName:'',
                    supportCode:'',
                    arriveTime:'',
                    isNeedTransport:false,
                    plateNumber:'',
                    driverName:'',
                    contactNumber:'',
                    consignorName:'',
                    consignorContactPhone:'',
                    departurePlace:'',
                    consignorCode:'',
                    consignorType:'',
                    consignorContactCode:'',
                    consignorContactName:'',
                    departurePlaceCode:''
                },
                rules: {
                    orderDate:[
                        { type: 'date', required: true, message: '请选择日期', trigger: 'blur' },
                        {validator: validateOrdeTime, trigger: 'blur'}
                    ],
                    merchandiser:[
                        { required: true, message: '请输入开单员', trigger: 'blur' },
                        { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'change' }
                    ],
                    custName:[
                        { required: true, message: '请输入客户名称', trigger: 'change' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change' }
                    ],
                    wareHouse:[
                        { required: true, message: '请选择仓库名称', trigger: 'change' }
                    ],
                    businessType:[
                        { required: true, message: '请选择业务类型', trigger: 'change' }
                    ],
                    custOrderCode:[
                        { min: 0, max: 30, message: '长度在 0 到 30 个字符', trigger: 'change' }
                    ],
                    notes:[
                        { min: 0, max: 200, message: '长度在 0 到 200 个字符', trigger: 'change' }
                    ],
                    plateNumber:[
                        { min: 0, max: 30, message: '长度在 0 到 30 个字符', trigger: 'change' }
                    ],
                    driverName:[
                        { min: 0, max: 30, message: '长度在 0 到 30 个字符', trigger: 'change' }
                    ],
                    contactNumber:[
                        { min: 0, max: 30, message: '长度在 0 到 30 个字符', trigger: 'change' },
                        {validator: checkPhoneOrMobile,trigger: 'blur'}
                    ]
                }
            };
        },

        beforeMount:function(){
            var vueObj=this;
            var url=window.location.href;
            CommonClient.syncpost(sys.rootPath + "/ofc/getCscGoodsTypeList",{"pid":null},function(result) {
                var data=eval(result);
                vueObj.goodsMsgOptions=[];
                $.each(data,function (index,CscGoodsTypeVo) {
                    var good={};
                    good.label=CscGoodsTypeVo.goodsTypeName;
                    good.value=CscGoodsTypeVo.id;
                    vueObj.goodsMsgOptions.push(good);
                });
            });
            if(url.indexOf("?")!=-1){
                var param=url.split("?")[1].split("=");
                if(param[0]=="orderCode"){
                    var orderCode=param[1];
                    CommonClient.post(sys.rootPath + "/ofc/orderStorageDetails", {"orderCode":orderCode}, function(result) {
                        if(result==undefined||result==null||result.result==null){
                            layer.msg("订单详情查询失败");
                            return;
                        }else if(result.code == 200){
                            var ofcFundamentalInformation=result.result.ofcFundamentalInformation;
                            var ofcWarehouseInformation=result.result.ofcWarehouseInformation;
                            var ofcGoodsDetailsInfo=result.result.ofcGoodsDetailsInfo;
                            var ofcDistributionBasicInfo=result.result.ofcDistributionBasicInfo;
                            if(ofcFundamentalInformation!=null){
                                vueObj.orderCode=ofcFundamentalInformation.orderCode;
                                vueObj.orderForm.orderDate=DateUtil.parse(ofcFundamentalInformation.orderTime);
                                vueObj.orderForm.merchandiser=ofcFundamentalInformation.merchandiser;
                                vueObj.orderForm.custName=ofcFundamentalInformation.custName;
                                vueObj.orderForm.custCode=ofcFundamentalInformation.custCode;
                                vueObj.orderForm.custOrderCode=ofcFundamentalInformation.custOrderCode;
                                vueObj.orderForm.businessType=ofcFundamentalInformation.businessType;
                                vueObj.orderForm.notes=ofcFundamentalInformation.notes;
                                if(ofcWarehouseInformation!=null){
                                    var wareHouse={};
                                    wareHouse.label= ofcWarehouseInformation.warehouseName;
                                    wareHouse.value= ofcWarehouseInformation.warehouseCode;
                                    vueObj.wareHouseOptions.push(wareHouse);
                                    vueObj.orderForm.wareHouse=ofcWarehouseInformation.warehouseCode;
                                    vueObj.orderForm.supportName=ofcWarehouseInformation.supportName;
                                    vueObj.orderForm.supportCode=ofcWarehouseInformation.supportCode;
                                    vueObj.orderForm.arriveTime=DateUtil.parse(ofcWarehouseInformation.arriveTime);
                                    vueObj.orderForm.plateNumber=ofcWarehouseInformation.plateNumber;
                                    vueObj.orderForm.driverName=ofcWarehouseInformation.driverName;
                                    vueObj.orderForm.contactNumber=ofcWarehouseInformation.contactNumber;
                                    if(ofcWarehouseInformation.provideTransport=="1"){
                                        if(ofcDistributionBasicInfo!=null){
                                            //发货方
                                            vueObj.orderForm.consignorName=ofcDistributionBasicInfo.consignorName;
                                            vueObj.orderForm.consignorCode=ofcDistributionBasicInfo.consignorCode;
                                            vueObj.orderForm.consignorContactCode=ofcDistributionBasicInfo.consignorContactCode;
                                            vueObj.orderForm.consignorContactName=ofcDistributionBasicInfo.consignorContactName;
                                            vueObj.orderForm.consignorContactPhone=ofcDistributionBasicInfo.consignorContactPhone;
                                            vueObj.orderForm.isNeedTransport=true;
                                            vueObj.orderForm.departurePlace=ofcDistributionBasicInfo.departurePlace;
                                            vueObj.orderForm.departurePlaceCode=ofcDistributionBasicInfo.departurePlaceCode;
                                        }
                                    }
                                    if(ofcGoodsDetailsInfo!=null&&ofcGoodsDetailsInfo.length>0){
                                        for(var i=0;i<ofcGoodsDetailsInfo.length;i++){
                                            var goodDetail=ofcGoodsDetailsInfo[i];
                                            var good={};
                                            good.goodsType=goodDetail.goodsType;
                                            good.goodsCategory=goodDetail.goodsCategory;
                                            var p={};
                                            p.label=goodDetail.goodsCategory;
                                            p.value=goodDetail.goodsCategory;
                                            vueObj.goodsCategoryOptions.push(p);
                                            good.goodsCode=goodDetail.goodsCode;
                                            good.goodsName=goodDetail.goodsName;
                                            good.goodsSpec=goodDetail.goodsSpec;
                                            good.quantity=goodDetail.quantity;
                                            good.unitPrice=goodDetail.unitPrice;
                                            good.productionBatch=goodDetail.productionBatch;
                                            good.productionTime=DateUtil.parse(goodDetail.productionTime);
                                            good.invalidTime=DateUtil.parse(goodDetail.invalidTime);
                                            vueObj.goodsData.push(good);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        },
        methods: {
            handleCurrentChange:function(val) {
                this.customerDataInfo.currentRow = val;
            },
            handlSuppliereCurrentChange:function(val) {
                this.supplierDataInfo.supplierCurrentRow = val;
            },
            handlGoodCurrentChange:function(val) {
                this.goodDataInfo.goodCurrentRow = val;
            },
            setCurrentCustInfo:function(val) {
                var vueObj=this;
                if (val != null) {
                    this.orderForm.custName = val.custName;
                    this.orderForm.custCode=val.custCode;
                    this.customerDataInfo.chosenCus = false;
                    CommonClient.post(sys.rootPath + "/ofc/queryWarehouseByCustomerCode", {"customerCode":vueObj.orderForm.custCode}, function(result) {
                        vueObj.wareHouseOptions = [];// 仓库下拉列表清空
                        vueObj.orderForm.wareHouse = '';       // 清空仓库
                        vueObj.supplierDataInfo.supplierData = [];    // 供应商列表清空
                        vueObj.orderForm.supportName = '';    // 清空供应商
                        var data=result.result;
                        if (data == undefined || data == null || data.length ==0) {
                            layer.msg("暂时未查询到该客户下的仓库信息！");
                        } else if (result.code == 200) {
                            $.each(data,function (index,rmcWarehouseRespDto) {
                                var warehouse={};
                                warehouse.label=rmcWarehouseRespDto.warehouseName;
                                warehouse.value= rmcWarehouseRespDto.warehouseCode;
                                vueObj.wareHouseOptions.push(warehouse);
                            });
                        } else if (result.code == 403) {
                            vueObj.promptInfo("没有权限",'error');
                        } else {
                            if(result.message==null||result.message==""){
                                vueObj.promptInfo("查询到该客户下的仓库信息",'error');
                            }else{
                                vueObj.promptInfo(result.message,'error');
                            }
                        }
                    },"json");
                } else {
                    vueObj.promptInfo("请选择客户信息!",'warning');
                }
            },
            loadWareHouseByCustomerCode:function(){
                var vueObj=this;
                CommonClient.syncpost(sys.rootPath + "/ofc/queryWarehouseByCustomerCode", {"customerCode":vueObj.customerCode}, function(result) {
                    var data=result.result;
                    if (data == undefined || data == null || data.length ==0) {
                        layer.msg("暂时未查询到该客户下的仓库信息！！");
                    } else if (result.code == 200) {
                        $.each(data,function (index,rmcWarehouseRespDto) {
                            var warehouse={};
                            warehouse.label=rmcWarehouseRespDto.warehouseName;
                            warehouse.value= rmcWarehouseRespDto.warehouseCode;
                            vueObj.wareHouseOptions.push(warehouse);
                        });
                    } else if (result.code == 403) {
                        vueObj.promptInfo("没有权限",'error');
                    } else {
                        if(result.message==null||result.message==""){
                            vueObj.promptInfo("查询到该客户下的仓库信息",'error');
                        }else{
                            vueObj.promptInfo(result.message,'error');
                        }
                    }
                },"json");
            },
            consignorHandleCurrentChange:function(val) {
                this.consignorDataInfo.consignorCurrentRow=val;
            },
            handleCustomerSizeChange:function(val) {
                this.customerDataInfo.customerPageSize=val;
                this.selectCustomer();
            },
            handleCustomerCurrentPage:function(val) {
                this.customerDataInfo.currentCustomerPage = val;
                this.selectCustomer();
            },
            addGoods:function(){
                if(StringUtil.isEmpty(this.orderForm.custName)&&StringUtil.isEmpty(this.orderForm.custCode)){
                    this.promptInfo("请选择客户!",'warning');
                    return;
                }
                this.goodDataInfo.chosenGoodCode = true;
                var vueObj=this;
                CommonClient.syncpost(sys.rootPath + "/ofc/getCscGoodsTypeList",{"pid":null},function(result) {
                    var data=eval(result);
                    vueObj.goodsMsgOptions=[];
                    $.each(data,function (index,CscGoodsTypeVo) {
                        var good={};
                        good.label=CscGoodsTypeVo.goodsTypeName;
                        good.value=CscGoodsTypeVo.id;
                        vueObj.goodsMsgOptions.push(good);
                    });
                });
            },
            deleteRow:function(index, rows) {
                rows.splice(index, 1);
            },
            getGoodsCategory:function(val) {
                var vueObj=this;
                var typeId=val.goodsType;
                this.goodsType=typeId;
                CommonClient.syncpost(sys.rootPath + "/ofc/getCscGoodsTypeList",{"cscGoodsType":typeId},function(data) {
                    data=eval(data);
                    vueObj.goodsCategoryOptions=[];
                    $.each(data,function (index,CscGoodsTypeVo) {
                        var goodClass={};
                        goodClass.label=CscGoodsTypeVo.goodsTypeName;
                        goodClass.value=CscGoodsTypeVo.goodsTypeName;
                        vueObj.goodsCategoryOptions.push(goodClass);
                    });
                });
            },
            GoodsCurrentChange:function(val) {
                this.goodsCurrentRow = val;
            },
            goodsCodeClick:function() {
                console.log('弹窗');
            },
            selectSupplier:function(){
                if(StringUtil.isEmpty(this.orderForm.custName) &&StringUtil.isEmpty(this.orderForm.custCode)){
                    this.promptInfo("请选择客户!",'warning');
                    this.supplierDataInfo.chosenSupplier=false;
                    return;
                }
                this.supplierDataInfo.supplierData=[];
                var vueObj=this;
                var param = {};
                param = vueObj.supplierDataInfo.supplierForm;
                param.customerCode = vueObj.orderForm.custCode;
                param.pNum = vueObj.supplierDataInfo.currentSupplierPage;
                param.pSize=vueObj.supplierDataInfo.supplierPageSize;
                CommonClient.post(sys.rootPath + "/ofc/supplierSelect",param, function(result) {
                    vueObj.supplierDataInfo.supplierData = [];
                    vueObj.orderForm.supportName = '';
                    var data = eval(result);
                    if (data == undefined || data == null || data.result == undefined || data.result ==null || data.result.size == 0) {
                        layer.msg("暂时未查询到供应商信息！！");
                    } else if (data.code == 200) {
                        $.each(data.result.list,function (index,CscSupplierInfoDto) {
                            var supplier={};
                            supplier.supportName=StringUtil.nullToEmpty(CscSupplierInfoDto.supplierName);
                            supplier.contactName=StringUtil.nullToEmpty(CscSupplierInfoDto.contactName);
                            supplier.contactPhone=StringUtil.nullToEmpty(CscSupplierInfoDto.contactPhone);
                            supplier.fax=StringUtil.nullToEmpty(CscSupplierInfoDto.fax);
                            supplier.email=StringUtil.nullToEmpty(CscSupplierInfoDto.email);
                            supplier.postCode=StringUtil.nullToEmpty(CscSupplierInfoDto.postCode);
                            supplier.supportCode=StringUtil.nullToEmpty(CscSupplierInfoDto.supplierCode);
                            supplier.completeAddress=StringUtil.nullToEmpty(CscSupplierInfoDto.completeAddress);

                            vueObj.supplierDataInfo.supplierData.push(supplier);

                        });
                        vueObj.supplierDataInfo.totalSupplier=data.result.total;
                    } else if (result.code == 403) {
                        vueObj.promptInfo("没有权限",'error');
                    }
                },"json");

            },
            handleSupplierSizeChange:function(val){
                this.supplierDataInfo.supplierPageSize = val;
                this.selectSupplier();
            },
            handleSupplierCurrentPage:function(val){
                this.supplierDataInfo.currentSupplierPage = val;
                this.selectSupplier();
            },

            setCurrentSupplierInfo:function(val){
                if (val != null) {
                    this.orderForm.supportName=val.supportName;
                    this.orderForm.supportCode=val.supportCode;
                    this.supplierDataInfo.chosenSupplier=false;
                } else {
                    this.promptInfo("请选择供应商!",'warning');
                }
            },
            setCurrentGoodsInfo:function(val){
                this.goodDataInfo.chosenGoodCode = false;
                var newData = {
                    goodsType: val.goodsType,
                    goodsCategory: val.goodsCategory,
                    goodsCategory: val.goodsCategory,
                    goodsCode: val.goodsCode,
                    goodsName: val.goodsName,
                    goodsSpec: val.goodsSpec,
                    unit: val.unit,
                    quantity: '',
                    unitPrice:'',
                    productionBatch:'',
                    productionTime:'',
                    invalidTime:''
                };
                this.goodsData.push(newData);
            },
            cancelSelectSupplier:function(){
                this.supplierDataInfo.supplierData=[];
                this.supplierDataInfo.chosenSupplier=false;
            },
            selectConsignor:function(){
                if(!this.orderForm.custName){
                    this.promptInfo("请选择客户",'warning');
                    this.consignorDataInfo.chosenSend=false;
                    return;
                }
                this.consignorDataInfo.consignorData=[];
                var vueObj=this;
                var cscContactDto = {};
                var cscContactCompanyDto = {};
                var cscContantAndCompanyDto={};
                cscContactCompanyDto.contactCompanyName = this.consignorDataInfo.consignorForm.consignorName;
                cscContactDto.purpose = "2";
                cscContactDto.contactName = this.consignorDataInfo.consignorForm.consignorContactName;
                cscContactDto.phone = this.consignorDataInfo.consignorForm.consignorContactPhone;
                cscContantAndCompanyDto.cscContactDto = cscContactDto;
                cscContantAndCompanyDto.cscContactCompanyDto = cscContactCompanyDto;
                var customerCode = this.orderForm.custCode;
                cscContantAndCompanyDto.pageNum=this.consignorDataInfo.currentConsignorPage;
                cscContantAndCompanyDto.pageSize=this.consignorDataInfo.consignorPageSize;
                cscContantAndCompanyDto = JSON.stringify(cscContantAndCompanyDto);
                CommonClient.post(sys.rootPath + "/ofc/contactSelectForPage",{"cscContantAndCompanyDto":cscContantAndCompanyDto,"customerCode":customerCode}, function(result) {
                    if (result == undefined || result == null || result.result ==null || result.result.size == 0 || result.result.list == null) {
                        layer.msg("暂时未查询到发货方信息！！");
                    } else if (result.code == 200) {
                        $.each(result.result.list,function (index,CscContantAndCompanyDto) {
                            var consignor={};
                            if(CscContantAndCompanyDto.type=="1"){
                                consignor.consignorType="公司";
                            }else{
                                CscContantAndCompanyDto.consignorType="个人";
                            }
                            consignor.consignorName=CscContantAndCompanyDto.contactCompanyName;
                            consignor.consignorContactName=CscContantAndCompanyDto.contactName;
                            consignor.consignorContactPhone=CscContantAndCompanyDto.phone;
                            consignor.departurePlace=CscContantAndCompanyDto.provinceName+","+CscContantAndCompanyDto.cityName+","+CscContantAndCompanyDto.areaName;
                            if(CscContantAndCompanyDto.streetName!=null){
                                consignor.departurePlace=consignor.departurePlace+","+CscContantAndCompanyDto.streetName;
                            }
                            consignor.consignorContactCode=CscContantAndCompanyDto.contactCode;
                            consignor.consignorCode=CscContantAndCompanyDto.contactCompanyCode;
                            consignor.departurePlaceCode=CscContantAndCompanyDto.province+","+CscContantAndCompanyDto.city+","+CscContantAndCompanyDto.area;
                            if(CscContantAndCompanyDto.street!=null){
                                consignor.departurePlaceCode=consignor.departurePlaceCode+","+CscContantAndCompanyDto.street;
                            }
                            vueObj.consignorDataInfo.consignorData.push(consignor);
                        });
                        vueObj.consignorDataInfo.totalConsignor=result.result.total;
                    } else if (result.code == 403) {
                        vueObj.promptInfo("没有权限",'error');
                    }
                },"json");

            },

            handleConsignorSizeChange:function(val){
                this.consignorDataInfo.consignorPageSize=val;
                this.selectConsignor();
            },
            handleConsignorCurrentPage:function(val){
                this.consignorDataInfo.currentConsignorPage=val;
                this.selectConsignor();
            },
            cancelSelectConsignor:function(){
                this.consignorDataInfo.consignorData=[];
                this.consignorDataInfo.consignorPageSize=10;
                this.consignorDataInfo.totalConsignor=0;
                this.consignorDataInfo.chosenSend=false;
            },
            setCurrentConsignorInfo:function(val){
                this.orderForm.consignorName=val.consignorName;
                this.orderForm.consignorContactPhone=val.consignorContactPhone;
                this.orderForm.consignorContactName=val.consignorContactName;
                this.orderForm.departurePlace=val.departurePlace;
                this.orderForm.consignorType=val.type;
                this.orderForm.consignorCode=val.consignorCode;
                this.orderForm.consignorContactCode=val.consignorContactCode;
                this.orderForm.departurePlaceCode=val.departurePlaceCode;
                this.consignorDataInfo.chosenSend = false;
            },
            selectGoods:function(){
                this.goodDataInfo.goodsCodeData=[];
                var vueObj=this;
                var cscGoods = {};
                var customerCode = vueObj.orderForm.custCode;
                cscGoods.goodsName = vueObj.goodDataInfo.goodsForm.goodsName;
                cscGoods.goodsTypeId=vueObj.goodDataInfo.goodsForm.goodsTypeId;
                cscGoods.goodsTypeSonId=vueObj.goodDataInfo.goodsForm.goodsTypeSonId;
                cscGoods.barCode=vueObj.goodDataInfo.goodsForm.barCode;
                cscGoods.pNum=vueObj.goodDataInfo.currentGoodPage;
                cscGoods.pSize =vueObj.goodDataInfo.goodPageSize;
                var param = JSON.stringify(cscGoods);
                CommonClient.post(sys.rootPath + "/ofc/goodsSelects", {"cscGoods":param,"customerCode":customerCode}, function(data) {
                    if (data == undefined || data == null || data.result ==null || data.result.size == 0 || data.result.list == null) {
                        layer.msg("暂时未查询到货品信息！！");
                    } else if (data.code == 200) {
                        $.each(data.result.list,function (index,cscGoodsVo) {
                            var goodCode={};
                            goodCode.goodsType=cscGoodsVo.goodsTypeParentName;
                            goodCode.goodsCategory=cscGoodsVo.goodsTypeName;
                            goodCode.goodsCode=cscGoodsVo.goodsCode;
                            goodCode.goodsName=cscGoodsVo.goodsName;
                            goodCode.goodsBrand=cscGoodsVo.brand;
                            goodCode.goodsSpec=cscGoodsVo.specification;
                            goodCode.unit=cscGoodsVo.unit;
                            goodCode.barCode=cscGoodsVo.barCode;
                            vueObj.goodDataInfo.goodsCodeData.push(goodCode);
                        });
                        vueObj.goodDataInfo.totalGoods=data.result.total;
                    } else if (data.code == 403) {
                        vueObj.promptInfo("没有权限",'error');
                    }
                },"json");
            },
            handleGoodSizeChange:function(val){
                this.goodDataInfo.goodPageSize=val;
                this.selectGoods();
            },
            handleGoodCurrentPage:function(val){
                this.goodDataInfo.currentGoodPage=val;
                this.selectGoods();
            },
            cancelSelectGood:function(){
                this.goodDataInfo.goodsCodeData=[];
                this.goodDataInfo.goodPageSize=10;
                this.goodDataInfo.totalGoods=0;
                this.goodDataInfo.chosenGoodCode=false;
                this.goodDataInfo.goodsForm.goodsTypeId="";
                this.goodDataInfo.goodsForm.goodsTypeSonId="";
                this.goodDataInfo.goodsForm.goodsName="";
                this.goodDataInfo.goodsForm.barCode="";
            },
            cancelSelectCustomer:function(){
                this.customerDataInfo.customerData=[];
                this.customerDataInfo.customerPageSize=10;
                this.customerDataInfo.total=0;
                this.customerDataInfo.chosenCus=false;
            },
            selectCustomer:function(){
                var param = {};
                param.pageNum = this.customerDataInfo.currentCustomerPage;
                param.pageSize=this.customerDataInfo.customerPageSize;
                param.custName = this.customerDataInfo.chosenCusForm.name;
                this.customerDataInfo.customerData=[];
                var vueObj=this;
                CommonClient.post(sys.rootPath + "/ofc/distributing/queryCustomerByName", param,
                        function(result) {
                            if (result == undefined || result == null || result.result == null ||  result.result.size == 0 || result.result.list == null) {
                                layer.msg("暂时未查询到客户信息！！");
                            } else if (result.code == 200) {
                                $.each(result.result.list,function (index,cscCustomerVo) {
                                    var channel = cscCustomerVo.channel;
                                    if(null == channel){
                                        channel = "";
                                    }
                                    var customer={};
                                    customer.custCode=cscCustomerVo.customerCode;
                                    var custType = StringUtil.nullToEmpty(cscCustomerVo.type);
                                    if(custType == '1'){
                                        customer.type="公司";
                                    }else if (custType == '2'){
                                        customer.type="个人";
                                    }else{
                                        customer.type=custType;
                                    }
                                    customer.custName=cscCustomerVo.customerName;
                                    customer.channel=channel;
                                    customer.productType=cscCustomerVo.productType;
                                    vueObj.customerDataInfo.customerData.push(customer);
                                });
                                vueObj.customerDataInfo.total=result.result.total;
                            } else if (result.code == 403) {
                                vueObj.promptInfo("没有权限",'error');
                            }
                        },"json");
            },
            submitForm:function(formName) {
                var _this = this;
              this.$refs[formName].validate(function(valid){
                    if (valid) {
                       _this.saveStorage();
                    } else {
                        return false;
                    }
                });
            },
            saveStorage:function(){
                //订单基本信息
                var ofcOrderDTOStr = {};
                //发货方信息
                var cscContantAndCompanyDtoConsignorStr = {};
                //收货方信息
                var cscContantAndCompanyDtoConsigneeStr={};

                var cscContactDto={};
                //供应商信息
                var cscSupplierInfoDtoStr={};
                ofcOrderDTOStr=this.orderForm;
                //是否提供运输
                if(this.orderForm.isNeedTransport){
                    ofcOrderDTOStr.provideTransport="1";
                    if(!this.orderForm.consignorName){
                        this.promptInfo("提供运输时,发货方不能为空，请选择发货方!",'warning');
                        return;
                    }
                }else{
                    ofcOrderDTOStr.provideTransport="0";
                }
                //订单基本信息
                if(this.orderForm.orderDate){
                    ofcOrderDTOStr.orderTime=DateUtil.format(this.orderForm.orderDate, "yyyy-MM-dd HH:mm:ss");
                }
                //订单基本信息
                ofcOrderDTOStr.orderCode=this.orderCode;
                //仓库信息
                ofcOrderDTOStr.warehouseName=this.getWareHouseNameByCode(this.orderForm.wareHouse);//仓库名称
                ofcOrderDTOStr.warehouseCode=this.orderForm.wareHouse;//仓库编码
                if(!ofcOrderDTOStr.warehouseCode){
                    ofcOrderDTOStr.warehouseCode=this.orderForm.wareHouseCode;
                }
                if(this.orderForm.arriveTime){
                    ofcOrderDTOStr.arriveTime=DateUtil.format(this.orderForm.arriveTime, "yyyy-MM-dd HH:mm:ss");
                }
                cscContantAndCompanyDtoConsignorStr=this.getCscContantAndCompanyDtoConsignorStr();
                //出发地
                var consignorAddressNameMessage =this.orderForm.departurePlace.split(',');
                ofcOrderDTOStr.departureProvince=consignorAddressNameMessage[0];
                ofcOrderDTOStr.departureCity=consignorAddressNameMessage[1];
                ofcOrderDTOStr.departureDistrict=consignorAddressNameMessage[2];
                if(!StringUtil.isEmpty(consignorAddressNameMessage[3])){
                    ofcOrderDTOStr.departureTowns=consignorAddressNameMessage[3];
                }
                var goodsTable =this.goodsData;
                var goodDetail=[];

                if(goodsTable.length <1){
                    this.promptInfo("请添加至少一条货品!",'warning');
                    return;
                }
                //校验金额和格式化日期时间
                for(var i=0;i<goodsTable.length;i++){
                    var good=goodsTable[i];

                    if(good.unitPrice!=""){
                        if(isNaN(good.unitPrice)){
                            this.promptInfo("货品单价必须为数字",'error');
                            return;
                        }
                        if(good.unitPrice>99999.99||good.unitPrice<0){
                            this.promptInfo("货品单价不能大于99999.99或小于0",'warning');
                            return;
                        }
                        if(isNaN(good.unitPrice)){
                            this.promptInfo("货品数量必须为数字",'error');
                            return;
                        }
                    }
                    if(good.quantity>99999.999||good.quantity<0||good.quantity!=""||good.quantity==0){
                        if(!good.quantity){
                            this.promptInfo("货品入库数量不能为空",'warning');
                            return;
                        }
                        if(isNaN(good.quantity)){
                            this.promptInfo("货品入库数量必须为数字",'error');
                            return;
                        }
                        if(good.quantity>99999.999){
                            this.promptInfo("货品数量不能大于99999.999",'warning');
                            return;
                        }
                        if(good.quantity<0){
                            this.promptInfo("货品数量不能小于0",'error');
                            return;
                        }
                        if(good.quantity==0){
                            this.promptInfo("货品数量不能小于0",'error');
                            return;
                        }
                    }else{
                        this.promptInfo("货品数量不能为空",'warning');
                        return;
                    }
                    if( good.productionTime&& good.invalidTime){
                        if( good.productionTime.getTime()> good.invalidTime.getTime()){
                            this.promptInfo("生产日期不能大于失效日期",'error');
                            return;
                        }
                    }
                    goodDetail.push(good);
                }
                if(goodDetail.length <1){
                    this.promptInfo("请添加至少一条货品!",'warning');
                    return;
                }
                var ofcOrderDto = JSON.stringify(ofcOrderDTOStr);
                var orderGoodsListStr = JSON.stringify(goodDetail);
                var tag="edit";
                xescm.common.submit("/ofc/saveStorage"
                        ,{"ofcOrderDTOStr":ofcOrderDto
                            ,"orderGoodsListStr":orderGoodsListStr
                            ,"cscContantAndCompanyDtoConsignorStr":cscContantAndCompanyDtoConsignorStr
                            ,"cscContantAndCompanyDtoConsigneeStr":cscContantAndCompanyDtoConsigneeStr
                            ,"cscSupplierInfoDtoStr":cscSupplierInfoDtoStr
                            ,"tag":tag
                        }
                        ,"您确认提交订单吗?"
                        ,function () {
                            location.reload();
                            var url=window.location.href;
                            if(url.indexOf("?")!=-1){
                                var param=url.split("?")[1].split("=");
                            }
                            if(param[1]=="manager"){
                                var newurl = "/ofc/orderStorageInManager/";
                                var html = window.location.href;
                                var index = html.indexOf("/index#");
                                window.open(html.substring(0,index) + "/index#" + newurl);
                            }
                        });
            },
            getCscContantAndCompanyDtoConsignorStr:function(){
                var paramConsignor = {};
                var cscContactDto = {};
                var cscContactCompanyDto = {};
                cscContactCompanyDto.contactCompanyName = this.orderForm.consignorName;
                cscContactDto.contactName = this.orderForm.consignorContactName;
                cscContactDto.purpose = "2";
                cscContactDto.phone =this.orderForm.consignorContactPhone;
                cscContactDto.contactCompanyName = this.orderForm.consignorName;
                var consignorAddressCodeMessage = this.orderForm.departurePlaceCode.split(',');
                var consignorAddressNameMessage =this.orderForm.departurePlace.split(',');
                cscContactDto.province = consignorAddressCodeMessage[0];
                cscContactDto.city = consignorAddressCodeMessage[1];
                cscContactDto.area = consignorAddressCodeMessage[2];
                if(!StringUtil.isEmpty(consignorAddressCodeMessage[3])){
                    cscContactDto.street = consignorAddressCodeMessage[3];
                }
                cscContactDto.provinceName = consignorAddressNameMessage[0];
                cscContactDto.cityName = consignorAddressNameMessage[1];
                cscContactDto.areaName = consignorAddressNameMessage[2];
                if(!StringUtil.isEmpty(consignorAddressNameMessage[3])){
                    cscContactDto.streetName = consignorAddressNameMessage[3];
                }

                cscContactDto.address=this.orderForm.consignorAddress;
                paramConsignor.cscContactDto = cscContactDto;
                paramConsignor.cscContactCompanyDto = cscContactCompanyDto;
                var cscContantAndCompanyDtoConsignorStr = JSON.stringify(paramConsignor);
                return cscContantAndCompanyDtoConsignorStr;
            },
            openGoodsList: function(currentRowData) {
                this.goodDataInfo.chosenGoodCode = true;
                this.currentRowData = currentRowData;
            },
            getWareHouseNameByCode:function(val){
                for(var i=0;i<this.wareHouseOptions.length;i++){
                    var option=this.wareHouseOptions[i];
                    if(option.value==val){
                        return option.label;
                    }
                }
            },
            promptInfo:function(message,type){
                this.$message({
                    message: message,
                    type: type
                });
            }
        }
    });
</script>
</html>