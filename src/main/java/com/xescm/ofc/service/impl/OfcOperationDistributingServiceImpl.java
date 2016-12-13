package com.xescm.ofc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xescm.ofc.domain.OfcFundamentalInformation;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.feign.client.FeignCscContactAPIClient;
import com.xescm.ofc.feign.client.FeignCscCustomerAPIClient;
import com.xescm.ofc.feign.client.FeignCscGoodsAPIClient;
import com.xescm.ofc.model.dto.csc.CscContantAndCompanyDto;
import com.xescm.ofc.model.dto.csc.CscContantAndCompanyResponseDto;
import com.xescm.ofc.model.dto.csc.CscGoodsApiDto;
import com.xescm.ofc.model.dto.csc.domain.CscContact;
import com.xescm.ofc.model.dto.csc.domain.CscContactCompany;
import com.xescm.ofc.model.dto.ofc.OfcOrderDTO;
import com.xescm.ofc.model.vo.csc.CscGoodsApiVo;
import com.xescm.ofc.service.OfcFundamentalInformationService;
import com.xescm.ofc.service.OfcOperationDistributingService;
import com.xescm.ofc.utils.JSONUtils;
import com.xescm.ofc.utils.JsonUtil;
import com.xescm.ofc.utils.PubUtils;
import com.xescm.uam.domain.dto.AuthResDto;
import com.xescm.uam.utils.wrap.WrapMapper;
import com.xescm.uam.utils.wrap.Wrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * Created by lyh on 2016/11/30.
 */
@Service
public class OfcOperationDistributingServiceImpl implements OfcOperationDistributingService {

    @Autowired
    private OfcFundamentalInformationService ofcFundamentalInformationService;
    @Autowired
    private FeignCscCustomerAPIClient feignCscCustomerAPIClient;
    @Autowired
    private FeignCscGoodsAPIClient feignCscGoodsAPIClient;
    @Autowired
    private FeignCscContactAPIClient feignCscContactAPIClient;

    @Override
    /**
     * 转换页面DTO为CSCCUSTOMERDTO以便复用原有下单逻辑
     * @param ofcOrderDTO
     * @param purpose
     * @return
     */
    public CscContantAndCompanyDto switchOrderDtoToCscCAndCDto(OfcOrderDTO ofcOrderDTO,String purpose) {
        CscContantAndCompanyDto cscContantAndCompanyDto = new CscContantAndCompanyDto();
        cscContantAndCompanyDto.setCscContactCompany(new CscContactCompany());
        cscContantAndCompanyDto.setCscContact(new CscContact());
        //发货方
        if(StringUtils.equals("2",purpose)){
            cscContantAndCompanyDto.getCscContactCompany().setContactCompanyName(ofcOrderDTO.getConsignorName());
            cscContantAndCompanyDto.getCscContactCompany().setType(ofcOrderDTO.getConsignorType());
            cscContantAndCompanyDto.getCscContact().setPurpose(purpose);
            cscContantAndCompanyDto.getCscContact().setContactName(ofcOrderDTO.getConsignorContactName());
            cscContantAndCompanyDto.getCscContact().setPhone(ofcOrderDTO.getConsignorContactPhone());
//            cscContantAndCompanyDto.getCscContact().setContactCompanyId(ofcOrderDTO.getConsignorCode());
            cscContantAndCompanyDto.getCscContact().setContactCode(ofcOrderDTO.getConsignorContactCode());
            cscContantAndCompanyDto.getCscContact().setProvinceName(ofcOrderDTO.getDepartureProvince());
            cscContantAndCompanyDto.getCscContact().setCityName(ofcOrderDTO.getDepartureCity());
            cscContantAndCompanyDto.getCscContact().setAreaName(ofcOrderDTO.getDepartureDistrict());
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDepartureTowns())){
                cscContantAndCompanyDto.getCscContact().setStreetName(ofcOrderDTO.getDepartureTowns());
            }
            if(PubUtils.isSEmptyOrNull(ofcOrderDTO.getDeparturePlaceCode())){
                throw new BusinessException("四级地址编码为空");
            }
            String[] departureCode = ofcOrderDTO.getDeparturePlaceCode().split(",");
            cscContantAndCompanyDto.getCscContact().setProvince(departureCode[0]);
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDepartureCity()) && departureCode.length > 0){
                cscContantAndCompanyDto.getCscContact().setCity(departureCode[1]);
            }
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDepartureDistrict()) && departureCode.length > 1){
                cscContantAndCompanyDto.getCscContact().setArea(departureCode[2]);
            }
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDepartureTowns()) && departureCode.length > 2){
                cscContantAndCompanyDto.getCscContact().setStreet(departureCode[3]);
            }
            cscContantAndCompanyDto.getCscContact().setAddress(ofcOrderDTO.getDeparturePlace());
        }else if(StringUtils.equals("1",purpose)){
            cscContantAndCompanyDto.getCscContactCompany().setContactCompanyName(ofcOrderDTO.getConsigneeName());
            cscContantAndCompanyDto.getCscContactCompany().setType(ofcOrderDTO.getConsigneeType());
            cscContantAndCompanyDto.getCscContact().setPurpose(purpose);
            cscContantAndCompanyDto.getCscContact().setContactName(ofcOrderDTO.getConsigneeContactName());
            cscContantAndCompanyDto.getCscContact().setPhone(ofcOrderDTO.getConsigneeContactPhone());
//            cscContantAndCompanyDto.getCscContact().setContactCompanyId(ofcOrderDTO.getConsigneeCode());
            cscContantAndCompanyDto.getCscContact().setContactCode(ofcOrderDTO.getConsigneeContactCode());
            cscContantAndCompanyDto.getCscContact().setProvinceName(ofcOrderDTO.getDestinationProvince());
            cscContantAndCompanyDto.getCscContact().setCityName(ofcOrderDTO.getDestinationCity());
            cscContantAndCompanyDto.getCscContact().setAreaName(ofcOrderDTO.getDestinationDistrict());
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDestinationTowns())){
                cscContantAndCompanyDto.getCscContact().setStreetName(ofcOrderDTO.getDestinationTowns());
            }
            if(PubUtils.isSEmptyOrNull(ofcOrderDTO.getDeparturePlaceCode())){
                throw new BusinessException("四级地址编码为空");
            }
            String[] departureCode = ofcOrderDTO.getDeparturePlaceCode().split(",");
            cscContantAndCompanyDto.getCscContact().setProvince(departureCode[0]);
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDepartureCity()) && departureCode.length > 0){
                cscContantAndCompanyDto.getCscContact().setCity(departureCode[1]);
            }
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDepartureDistrict()) && departureCode.length > 1){
                cscContantAndCompanyDto.getCscContact().setArea(departureCode[2]);
            }
            if(!PubUtils.isSEmptyOrNull(ofcOrderDTO.getDepartureTowns()) && departureCode.length > 2){
                cscContantAndCompanyDto.getCscContact().setStreet(departureCode[3]);
            }
            cscContantAndCompanyDto.getCscContact().setAddress(ofcOrderDTO.getDestination());
        }


        return cscContantAndCompanyDto;
    }
    /**
     * 校验客户订单编号
     * @param jsonArray
     * @return
     * @throws Exception
     */
    @Override
    public Wrapper<?> validateCustOrderCode(JSONArray jsonArray) {
        List<String> custOrderCodeList = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i ++) {
            String json = jsonArray.get(i).toString();
            OfcOrderDTO ofcOrderDTO = null;
            try {
                ofcOrderDTO = (OfcOrderDTO) JsonUtil.json2Object(json, OfcOrderDTO.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("校验客户订单编号转换异常",e);
            }
            String custOrderCode = ofcOrderDTO.getCustOrderCode();
            if(!PubUtils.isSEmptyOrNull(custOrderCode)){
                if(custOrderCodeList.contains(custOrderCode)){
                    return WrapMapper.wrap(Wrapper.ERROR_CODE, "收货方列表中第" + (i + 1) + "行,收货方名称为【" + ofcOrderDTO.getConsigneeName() + "】的客户订单编号重复！请检查！");
                }
                custOrderCodeList.add(custOrderCode);
            }
        }
        /*for(String custOrderCode : custOrderCodeList){

        }*/
        /*String pageCustOrderCode = "";

        for(int i = 0; i < jsonArray.size(); i ++) {
            String json = jsonArray.get(i).toString();
            OfcOrderDTO ofcOrderDTO = null;
            try {
                ofcOrderDTO = (OfcOrderDTO) JsonUtil.json2Object(json, OfcOrderDTO.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("校验客户订单编号转换异常",e);
            }
            String custOrderCode = ofcOrderDTO.getCustOrderCode();
            if(!PubUtils.isSEmptyOrNull(custOrderCode)){
                if(pageCustOrderCode.equals(custOrderCode)){
                    return WrapMapper.wrap(Wrapper.ERROR_CODE, "收货方列表中第" + (i + 1) + "行,收货方名称为【" + ofcOrderDTO.getConsigneeName() + "】的客户订单编号重复！请检查！");
                }
                pageCustOrderCode = custOrderCode;
                OfcFundamentalInformation ofcFundamentalInformation = new OfcFundamentalInformation();
                ofcFundamentalInformation.setCustOrderCode(custOrderCode);
                int checkCustOrderCodeResult = ofcFundamentalInformationService.checkCustOrderCode(ofcFundamentalInformation);
                if (checkCustOrderCodeResult > 0) {
                    return WrapMapper.wrap(Wrapper.ERROR_CODE, "收货方列表中第" + (i + 1) + "行,收货方名称为【" + ofcOrderDTO.getConsigneeName() + "】的客户订单编号重复！请检查！");
                }
            }

        }*/

        return WrapMapper.wrap(Wrapper.SUCCESS_CODE);
    }


    /**
     * 获取用户上传的Excel的sheet页
     * @param uploadFile
     * @param suffix
     * @return
     */
    @Override
    public List<String> getExcelSheet(MultipartFile uploadFile, String suffix) {
        List<String> sheetMsgList = new ArrayList<>();
        if("xls".equals(suffix)){
            HSSFWorkbook hssfWorkbook = null;
            try {
                hssfWorkbook = new HSSFWorkbook(uploadFile.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException("获取Sheet页内部异常",e);
            }
            int activeSheetIndex = hssfWorkbook.getActiveSheetIndex();
            int numberOfSheets = hssfWorkbook.getNumberOfSheets();
            for(int sheetNum = 0; sheetNum < numberOfSheets;  sheetNum ++){
                if(sheetNum == activeSheetIndex){
                    sheetMsgList.add(hssfWorkbook.getSheetName(sheetNum) + "@active");
                }else{
                    sheetMsgList.add(hssfWorkbook.getSheetName(sheetNum) + "@");
                }
            }
        }else if("xlsx".equals(suffix)){
            XSSFWorkbook xssfWorkbook = null;
            try {
                xssfWorkbook = new XSSFWorkbook(uploadFile.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException("获取Sheet页内部异常",e);
            }
            int activeSheetIndex = xssfWorkbook.getActiveSheetIndex();
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            for(int sheetNum = 0; sheetNum < numberOfSheets;  sheetNum ++){
                if(sheetNum == activeSheetIndex){
                    sheetMsgList.add(xssfWorkbook.getSheetName(sheetNum) + "@active");
                }else{
                    sheetMsgList.add(xssfWorkbook.getSheetName(sheetNum) + "@");
                }
            }
        }else{
            throw new BusinessException("您上传的文档格式不正确!");
        }
        return sheetMsgList;
    }

    /**
     * 校验用户上传的Excel是否符合我们的格式
     * @param uploadFile
     * @param suffix
     * @param authResDto
     * @param customerCode
     * @param staticCell 固定列
     * @return
     * */
    @Override
    public Wrapper<?> checkExcel(MultipartFile uploadFile, String suffix, String sheetNumChosen, AuthResDto authResDto, String customerCode, Integer staticCell) {
        try {
            if("xls".equals(suffix)){
                return checkXls(uploadFile,sheetNumChosen,customerCode,staticCell);
            }else if("xlsx".equals(suffix)){
                return checkXlsx(uploadFile,sheetNumChosen,customerCode,staticCell);
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(),e);
        }
        return WrapMapper.wrap(Wrapper.ERROR_CODE,"上传文档格式不正确!");
    }

    /**
     * 校验XLS格式
     * @param uploadFile
     * @param sheetNumChosen
     * @param customerCode
     * @param staticCell
     * @return
     */
    private Wrapper<?> checkXls(MultipartFile uploadFile,String sheetNumChosen, String customerCode, Integer staticCell){
        boolean checkPass = true;
        Map<String,JSONArray> resultMap = null;
        List<CscContantAndCompanyResponseDto> consigneeNameList = null;
        List<String> consigneeNameListForCheck = null;
        List<CscGoodsApiVo> goodsApiVoList = null;
        List<String> goodsCodeListForCheck = null;
        List<String> xlsErrorMsg = null;
        HSSFWorkbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(uploadFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("校验Excel读取内部异常");
        }
        int numberOfSheets = hssfWorkbook.getNumberOfSheets();

        //遍历sheet
        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum ++){
            if(sheetNum != Integer.valueOf(sheetNumChosen)){
                continue;
            }
            HSSFSheet sheet = hssfWorkbook.getSheetAt(sheetNum);
            resultMap = new LinkedHashMap<>();
            consigneeNameList = new ArrayList<>();
            consigneeNameListForCheck = new ArrayList<>();
            goodsApiVoList = new ArrayList<>();
            goodsCodeListForCheck = new ArrayList<>();
            xlsErrorMsg = new ArrayList<>();
            //遍历row
            if(sheet.getLastRowNum() == 0){
                throw new BusinessException("请先上传Excel导入数据，再加载后执行导入！");
            }
            for (int rowNum = 0; rowNum < sheet.getLastRowNum() + 1; rowNum ++){
                HSSFRow hssfRow = sheet.getRow(rowNum);
                String mapKey = "";
                JSONArray jsonArray = new JSONArray();
                //空行
                HSSFCell cell = hssfRow.getCell(0);
                if(null == hssfRow || PubUtils.isSEmptyOrNull(cell.getStringCellValue()) ){
                    //标记当前行出错,并跳出当前循环
                    break;
                }
                //遍历cell
                for(int cellNum = 0; cellNum < hssfRow.getLastCellNum() + 1; cellNum ++){
                    HSSFCell hssfCell = hssfRow.getCell(cellNum);
                    //空列
                    if(null == hssfCell){
                        //标记当前列出错, 并跳过当前循环
                        break;
                    }else if(HSSFCell.CELL_TYPE_BLANK == hssfCell.getCellType()){
                        continue;
                    }
                    //校验第一行,包括固定内容和收货人列表
                    if(rowNum == 0){
                        //第一行全为字符串
//                            String cellValue = PubUtils.trimAndNullAsEmpty(hssfCell.getStringCellValue());
                        String cellValue = "";
                        if(HSSFCell.CELL_TYPE_STRING == hssfCell.getCellType()){
                            cellValue = PubUtils.trimAndNullAsEmpty(hssfCell.getStringCellValue());
                        }else if(HSSFCell.CELL_TYPE_NUMERIC == hssfCell.getCellType()){
                            cellValue = PubUtils.trimAndNullAsEmpty(String.valueOf(hssfCell.getNumericCellValue()));
                        }
                        //校验模板第一行前5列的固定名称是否被改变
                        if(cellNum >= 0 && cellNum <= (staticCell -1)){
                            String[] cellName = {"货品编码","货品名称","规格","单位","单价"};
                            //如果校验失败,就标记该单元格
                            if(!cellName[cellNum].equals(cellValue)){
                                //模板固定内容
                                checkPass = false;
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!建议:" + cellName[cellNum]);

                            }/*else{
                                        //如果校验成功,就不用变
                                    }*/
                            //校验5列之后的收货人名称是否在客户中心收发货档案中维护了
                        }else if(cellNum > (staticCell -1)){//   第一个收货人cellNum是5 > 5 - 1
                            if(PubUtils.isSEmptyOrNull(cellValue)){
                                consigneeNameList.add(new CscContantAndCompanyResponseDto());
                                consigneeNameListForCheck.add("");
                                continue;
                            }
                            //如果校验失败,就标记该单元格
                            CscContantAndCompanyDto cscContantAndCompanyDto = new CscContantAndCompanyDto();
                            CscContactCompany cscContactCompany = new CscContactCompany();
                            CscContact cscContact = new CscContact();
                            cscContantAndCompanyDto.setCustomerCode(customerCode);
                            cscContactCompany.setContactCompanyName(cellValue);
                            cscContact.setPurpose("1");//用途为收货方
                            cscContantAndCompanyDto.setCscContact(cscContact);
                            cscContantAndCompanyDto.setCscContactCompany(cscContactCompany);
                            Wrapper<List<CscContantAndCompanyResponseDto>> queryCscCustomerResult = feignCscContactAPIClient.queryCscReceivingInfoList(cscContantAndCompanyDto);
                            if(Wrapper.ERROR_CODE == queryCscCustomerResult.getCode()){
                                throw new BusinessException(queryCscCustomerResult.getMessage());
                            }
                            List<CscContantAndCompanyResponseDto> result = queryCscCustomerResult.getResult();
                            if(null != result && result.size() > 0){
                                //如果能在客户中心查到,就将该收货人名称记录下来,往consigneeNameList里放
                                consigneeNameList.add(result.get(0));
                                consigneeNameListForCheck.add(result.get(0).getContactCompanyName());
                            }else{
                                //收货人列表不在联系人档案中,则需要对当前格子进行报错处理
                                checkPass = false;
                                consigneeNameList.add(new CscContantAndCompanyResponseDto());
                                consigneeNameListForCheck.add("");

                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该收货方名称在联系人档案中不存在!");
                            }
                        }
                        //校验从第二行开始的体
                    }else if(rowNum > 0){
                        //校验第一列即货品编码是否已经在货品档案中进行了维护

                        JSONObject jsonObject = new JSONObject();
                        if(cellNum == 0){
                            String goodsCode = hssfCell.getStringCellValue();
                            if(PubUtils.isSEmptyOrNull(goodsCode)){
                                continue;
                            }
                            CscGoodsApiDto cscGoodsApiDto = new CscGoodsApiDto();
                            cscGoodsApiDto.setGoodsCode(goodsCode);
                            cscGoodsApiDto.setCustomerCode(customerCode);
                            Wrapper<List<CscGoodsApiVo>> queryCscGoodsList = feignCscGoodsAPIClient.queryCscGoodsList(cscGoodsApiDto);
                            if(Wrapper.ERROR_CODE == queryCscGoodsList.getCode()){
                                checkPass = false;
                                goodsApiVoList.add(new CscGoodsApiVo());
                                goodsCodeListForCheck.add("");
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品在货品档案中不存在!");
                                break;
                            }
                            List<CscGoodsApiVo> result = queryCscGoodsList.getResult();
                            if(null != result && result.size() > 0){
                                //如果校验成功,就往结果集里堆
                                CscGoodsApiVo cscGoodsApiVo = result.get(0);
                                mapKey =cscGoodsApiVo.getGoodsCode() + "@" + rowNum;
                                goodsApiVoList.add(cscGoodsApiVo); //
                                goodsCodeListForCheck.add(cscGoodsApiVo.getGoodsName());
                            }else{
                                //如果校验失败,就标记该单元格
                                checkPass = false;
                                goodsApiVoList.add(new CscGoodsApiVo());
                                goodsCodeListForCheck.add("");
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品名称在货品档案中不存在!");

                            }
                            //货品名称, 规格, 单位, 单价的数据暂时不需校验
                        }else if(cellNum > 0 && cellNum <= (staticCell -1)){

                            //收货人的货品需求数量
                        }else if(cellNum > (staticCell -1)){
                            try{
                                //校验是否数字
                                Double goodsAmount = 0.0;
                                Double goodsAndConsigneeNum = hssfCell.getNumericCellValue();
                                //使用正则对数字进行校验
                                boolean matches = goodsAndConsigneeNum.toString().matches("\\d{1,6}\\.\\d{1,3}");
                                //如果校验成功,就往结果集里堆
                                if(matches){
                                    CscContantAndCompanyResponseDto cscContantAndCompanyVo = consigneeNameList.get(cellNum - staticCell);
                                    CscGoodsApiVo cscGoodsApiVo = goodsApiVoList.get(rowNum - 1);
                                    goodsAmount = cscGoodsApiVo.getGoodsAmount() + goodsAndConsigneeNum;
                                    cscGoodsApiVo.setGoodsAmount(goodsAmount);
                                    goodsApiVoList.remove(rowNum - 1);
                                    goodsApiVoList.add(rowNum-1,cscGoodsApiVo);
                                    String consigneeCode = cscContantAndCompanyVo.getContactCompanySerialNo();
                                    String consigneeContactCode = cscContantAndCompanyVo.getContactSerialNo();
                                    if(PubUtils.isSEmptyOrNull(consigneeCode) || PubUtils.isSEmptyOrNull(consigneeContactCode)){
                                        throw new BusinessException("收货方编码或收货方联系人编码为空!");
                                        //xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!收货方编码或收货方联系人编码为空!");
                                        //throw new BusinessException("收货方编码或收货方联系人编码为空!");
                                    }
                                    String consigneeMsg = consigneeCode + "@" + consigneeContactCode;
                                    jsonObject.put(consigneeMsg,goodsAndConsigneeNum);
                                    jsonArray.add(cscGoodsApiVo);
                                    jsonArray.add(jsonObject);
                                    jsonArray.add(cscContantAndCompanyVo);

                                    //如果数字格式不对,就标记该单元格
                                }else{
                                    checkPass = false;
                                    xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品数量格式不正确!");
                                }
                                //这里只抓不是数字的情况
                            }catch (IllegalStateException ex){
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品数量不是数字格式!");
                            }catch (Exception ex){//这里的Exception再放小点, 等报错的时候看看报的是什么异常
                                checkPass = false;
                                throw new BusinessException(ex.getMessage(), ex);
                            }
                        }
                    }
                }
                if(rowNum > 0){//避免第一行
                    resultMap.put(mapKey,jsonArray);//一条结果
                }
            }
        }
        Wrapper<List<String>> consigneeRepeatCheckResult = checkRepeat(consigneeNameListForCheck,Integer.valueOf(sheetNumChosen),"consignee");
        if(consigneeRepeatCheckResult.getCode() == Wrapper.ERROR_CODE){
            checkPass = false;
            xlsErrorMsg.addAll(consigneeRepeatCheckResult.getResult());
        }
        Wrapper<List<String>> goodsRepeatCheckResult = checkRepeat(goodsCodeListForCheck,Integer.valueOf(sheetNumChosen),"goods");
        if(goodsRepeatCheckResult.getCode() == Wrapper.ERROR_CODE){
            checkPass = false;
            xlsErrorMsg.addAll(goodsRepeatCheckResult.getResult());
        }
        if(checkPass){
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE,"校验成功!",resultMap);
        }else{
            return WrapMapper.wrap(Wrapper.ERROR_CODE,"校验失败!我们已为您显示校验结果,请改正后重新上传!",xlsErrorMsg);
        }

    }

    /**
     * 校验XLSX格式
     * @param uploadFile
     * @param sheetNumChosen
     * @param custId
     * @param staticCell
     * @return
     */
    private Wrapper<?> checkXlsx(MultipartFile uploadFile,String sheetNumChosen, String custId, Integer staticCell){
        boolean checkPass = true;
        Map<String,JSONArray> resultMap = null;
        List<CscContantAndCompanyResponseDto> consigneeNameList = null;
        List<String> consigneeNameListForCheck = null;
        List<CscGoodsApiVo> goodsApiVoList = null;
        List<String> goodsCodeListForCheck = null;
        List<String> xlsErrorMsg = null;
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(uploadFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("校验Excel读取内部异常",e);
        }
        int numberOfSheets = xssfWorkbook.getNumberOfSheets();

        //遍历sheet
        for (int sheetNum = 0; sheetNum < numberOfSheets; sheetNum ++){
            if(sheetNum != Integer.valueOf(sheetNumChosen)){
                continue;
            }
            XSSFSheet sheet = xssfWorkbook.getSheetAt(sheetNum);
            resultMap = new LinkedHashMap<>();
            consigneeNameList = new ArrayList<>();
            consigneeNameListForCheck = new ArrayList<>();
            goodsApiVoList = new ArrayList<>();
            goodsCodeListForCheck = new ArrayList<>();
            xlsErrorMsg = new ArrayList<>();
            //遍历row
            if(sheet.getLastRowNum() == 0){
                throw new BusinessException("请先上传Excel导入数据，再加载后执行导入！");
            }
            for (int rowNum = 0; rowNum < sheet.getLastRowNum() + 1; rowNum ++){
                XSSFRow xssfRow = sheet.getRow(rowNum);
                String mapKey = "";
                JSONArray jsonArray = new JSONArray();
                //空行
                XSSFCell cell = xssfRow.getCell(0);
                if(null == xssfRow || PubUtils.isSEmptyOrNull(cell.getStringCellValue()) ){
                    //标记当前行出错,并跳出当前循环
                    break;
                }
                //遍历cell
                for(int cellNum = 0; cellNum < xssfRow.getLastCellNum() + 1; cellNum ++){
                    XSSFCell xssfCell = xssfRow.getCell(cellNum);
                    //空列
                    if(null == xssfCell){
                        //标记当前列出错, 并跳过当前循环
                        break;
                    }else if(HSSFCell.CELL_TYPE_BLANK == xssfCell.getCellType()){
                        continue;
                    }
                    //校验第一行,包括固定内容和收货人列表
                    if(rowNum == 0){
                        //第一行全为字符串
                        String cellValue = "";
                        if(HSSFCell.CELL_TYPE_STRING == xssfCell.getCellType()){
                            cellValue = PubUtils.trimAndNullAsEmpty(xssfCell.getStringCellValue());
                        }else if(HSSFCell.CELL_TYPE_NUMERIC == xssfCell.getCellType()){
                            cellValue = PubUtils.trimAndNullAsEmpty(String.valueOf(xssfCell.getNumericCellValue()));
                        }
                        //校验模板第一行前5列的固定名称是否被改变
                        if(cellNum >= 0 && cellNum <= (staticCell -1)){
                            String[] cellName = {"货品编码","货品名称","规格","单位","单价"};
                            //如果校验失败,就标记该单元格
                            if(!cellName[cellNum].equals(cellValue)){
                                //模板固定内容
                                checkPass = false;
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!建议:" + cellName[cellNum]);

                            }/*else{
                                        //如果校验成功,就不用变
                                    }*/
                            //校验5列之后的收货人名称是否在客户中心收发货档案中维护了
                        }else if(cellNum > (staticCell -1)){//   第一个收货人cellNum是5 > 5 - 1
                            if(PubUtils.isSEmptyOrNull(cellValue)){
                                consigneeNameList.add(new CscContantAndCompanyResponseDto());
                                consigneeNameListForCheck.add("");
                                continue;
                            }
                            //如果校验失败,就标记该单元格
                            CscContantAndCompanyDto cscContantAndCompanyDto = new CscContantAndCompanyDto();
                            CscContactCompany cscContactCompany = new CscContactCompany();
                            CscContact cscContact = new CscContact();
                            cscContantAndCompanyDto.setCustomerCode(custId);
                            cscContactCompany.setContactCompanyName(cellValue);
                            cscContact.setPurpose("1");//用途为收货方
                            cscContantAndCompanyDto.setCscContact(cscContact);
                            cscContantAndCompanyDto.setCscContactCompany(cscContactCompany);
                            Wrapper<List<CscContantAndCompanyResponseDto>> queryCscCustomerResult = feignCscContactAPIClient.queryCscReceivingInfoList(cscContantAndCompanyDto);
                            if(Wrapper.ERROR_CODE == queryCscCustomerResult.getCode()){
                                throw new BusinessException(queryCscCustomerResult.getMessage());
                            }
                            List<CscContantAndCompanyResponseDto> result = queryCscCustomerResult.getResult();
                            if(null != result && result.size() > 0){
                                //如果能在客户中心查到,就将该收货人名称记录下来,往consigneeNameList里放
                                CscContantAndCompanyResponseDto cscContantAndCompanyVo = result.get(0);
                                consigneeNameList.add(cscContantAndCompanyVo);
                                consigneeNameListForCheck.add(cscContantAndCompanyVo.getContactCompanyName());
                            }else{
                                //收货人列表不在联系人档案中,则需要对当前格子进行报错处理
                                checkPass = false;
                                consigneeNameList.add(new CscContantAndCompanyResponseDto());
                                consigneeNameListForCheck.add("");
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该收货方名称在联系人档案中不存在!");
                            }
                        }
                        //校验从第二行开始的体
                    }else if(rowNum > 0){
                        //校验第一列即货品编码是否已经在货品档案中进行了维护

                        JSONObject jsonObject = new JSONObject();
                        if(cellNum == 0){
                            String goodsCode = xssfCell.getStringCellValue();
                            if(PubUtils.isSEmptyOrNull(goodsCode)){
                                continue;
                            }
                            CscGoodsApiDto cscGoodsApiDto = new CscGoodsApiDto();
                            cscGoodsApiDto.setGoodsCode(goodsCode);
                            cscGoodsApiDto.setCustomerCode(custId);
                            Wrapper<List<CscGoodsApiVo>> queryCscGoodsList = feignCscGoodsAPIClient.queryCscGoodsList(cscGoodsApiDto);
                            if(Wrapper.ERROR_CODE == queryCscGoodsList.getCode()){
                                checkPass = false;
                                goodsApiVoList.add(new CscGoodsApiVo());
                                goodsCodeListForCheck.add("");
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品在货品档案中不存在!");
                                break;
                            }
                            List<CscGoodsApiVo> result = queryCscGoodsList.getResult();
                            if(null != result && result.size() > 0){
                                //如果校验成功,就往结果集里堆
                                CscGoodsApiVo cscGoodsApiVo = result.get(0);
                                mapKey =cscGoodsApiVo.getGoodsCode() + "@" + rowNum;
                                goodsApiVoList.add(cscGoodsApiVo); //
                                goodsCodeListForCheck.add(cscGoodsApiVo.getGoodsName());
                            }else{
                                //如果校验失败,就标记该单元格
                                checkPass = false;
                                goodsApiVoList.add(new CscGoodsApiVo());
                                goodsCodeListForCheck.add("");
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品名称在货品档案中不存在!");

                            }
                            //货品名称, 规格, 单位, 单价的数据暂时不需校验
                        }else if(cellNum > 0 && cellNum <= (staticCell -1)){

                            //收货人的货品需求数量
                        }else if(cellNum > (staticCell -1)){
                            try{
                                //校验是否数字
                                Double goodsAmount = 0.0;
                                Double goodsAndConsigneeNum = xssfCell.getNumericCellValue();
                                //使用正则对数字进行校验
                                boolean matches = goodsAndConsigneeNum.toString().matches("\\d{1,6}\\.\\d{1,3}");
                                //如果校验成功,就往结果集里堆
                                if(matches){
                                    CscContantAndCompanyResponseDto cscContantAndCompanyVo = consigneeNameList.get(cellNum - staticCell);
                                    CscGoodsApiVo cscGoodsApiVo = goodsApiVoList.get(rowNum - 1);
                                    goodsAmount = cscGoodsApiVo.getGoodsAmount() + goodsAndConsigneeNum;
                                    cscGoodsApiVo.setGoodsAmount(goodsAmount);
                                    goodsApiVoList.remove(rowNum - 1);
                                    goodsApiVoList.add(rowNum-1,cscGoodsApiVo);
                                    String consigneeCode = cscContantAndCompanyVo.getContactCompanySerialNo();
                                    String consigneeContactCode = cscContantAndCompanyVo.getContactSerialNo();
                                    if(PubUtils.isSEmptyOrNull(consigneeCode) || PubUtils.isSEmptyOrNull(consigneeContactCode)){
                                        throw new BusinessException("收货方编码或收货方联系人编码为空!");
//                                            checkPass = false;
//                                            xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!收货方编码或收货方联系人编码为空!");
//                                            continue;
                                    }
                                    String consigneeMsg = consigneeCode + "@" + consigneeContactCode;
                                    jsonObject.put(consigneeMsg,goodsAndConsigneeNum);
                                    jsonArray.add(cscGoodsApiVo);
                                    jsonArray.add(jsonObject);
                                    jsonArray.add(cscContantAndCompanyVo);

                                    //如果数字格式不对,就标记该单元格
                                }else{
                                    checkPass = false;
                                    xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品数量格式不正确!最大999999.999!");
                                }
                                //这里只抓不是数字的情况
                            }catch (IllegalStateException ex){
                                xlsErrorMsg.add("sheet页第" + (sheetNum + 1) + "页,第" + (rowNum + 1) + "行,第" + (cellNum + 1) + "列的值不符合规范!该货品数量不是数字格式!");
                            }catch (Exception ex){//这里的Exception再放小点, 等报错的时候看看报的是什么异常
                                checkPass = false;
                                throw new BusinessException(ex.getMessage(), ex);

                            }
                        }
                    }
                }
                if(rowNum > 0){             //避免第一行
                    resultMap.put(mapKey,jsonArray);//一条结果
                }
            }

        }
        Wrapper<List<String>> consigneeRepeatCheckResult = checkRepeat(consigneeNameListForCheck,Integer.valueOf(sheetNumChosen),"consignee");
        if(consigneeRepeatCheckResult.getCode() == Wrapper.ERROR_CODE){
            checkPass = false;
            xlsErrorMsg.addAll(consigneeRepeatCheckResult.getResult());
        }
        Wrapper<List<String>> goodsRepeatCheckResult = checkRepeat(goodsCodeListForCheck,Integer.valueOf(sheetNumChosen),"goods");
        if(goodsRepeatCheckResult.getCode() == Wrapper.ERROR_CODE){
            checkPass = false;
            xlsErrorMsg.addAll(goodsRepeatCheckResult.getResult());
        }
        if(checkPass){
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE,"校验成功!",resultMap);
        }else{
            return WrapMapper.wrap(Wrapper.ERROR_CODE,"校验失败!我们已为您显示校验结果,请改正后重新上传!",xlsErrorMsg);
        }
    }

    /**
     * 校验收货方或货品出现重复
     * @param consigneeOrGoodsList
     * @param sheetNum
     * @param tag
     * @return
     */
    private Wrapper<List<String>> checkRepeat(List<String> consigneeOrGoodsList, int sheetNum, String tag){
        List<String> errorConsigneeOrGoodsList = new ArrayList<>();
        Map<String,String> consigneeOrGoodsRepeatMap = new HashMap<>();
        Integer consigneeOrGoodsNum = null;
        if("goods".equals(tag)){
            consigneeOrGoodsNum = 2;
        }else if("consignee".equals(tag)){
            consigneeOrGoodsNum = 5;
        }
        for(String consigneeOrGoodsName : consigneeOrGoodsList){
            if(PubUtils.isSEmptyOrNull(consigneeOrGoodsName)){
                consigneeOrGoodsNum ++;
                continue;
            }
            String repeatStr = consigneeOrGoodsRepeatMap.get(consigneeOrGoodsName);
            if(!PubUtils.isSEmptyOrNull(repeatStr)){
                repeatStr += "," + consigneeOrGoodsNum;
                consigneeOrGoodsRepeatMap.put(consigneeOrGoodsName, repeatStr);
            }else{
                consigneeOrGoodsRepeatMap.put(consigneeOrGoodsName, consigneeOrGoodsNum.toString());
            }
            consigneeOrGoodsNum ++;
        }

        if("goods".equals(tag)){
            for(String goodsCode : consigneeOrGoodsRepeatMap.keySet()){
                String repeatStr = consigneeOrGoodsRepeatMap.get(goodsCode);
                if(repeatStr.split("\\,").length > 1){
                    errorConsigneeOrGoodsList.add("sheet页第" + sheetNum + "页,第1列,第" + repeatStr + "行的值不符合规范!原因:货品编码重复!");
                }
            }
        }else if("consignee".equals(tag)){
            for(String consigneeName : consigneeOrGoodsRepeatMap.keySet()){
                String repeatStr = consigneeOrGoodsRepeatMap.get(consigneeName);
                if(consigneeOrGoodsRepeatMap.get(consigneeName).split("\\,").length > 1){
                    errorConsigneeOrGoodsList.add("sheet页第" + sheetNum + "页,第1行,第" + repeatStr + "列的值不符合规范!原因:收货人名称重复!");
                }
            }
        }
        if(errorConsigneeOrGoodsList.size() > 0){
            return WrapMapper.wrap(Wrapper.ERROR_CODE,"Excel导入收货人或货品出现重复",errorConsigneeOrGoodsList);
        }else{
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE);
        }
    }

    /**
     * 城配开单必填项后端校验
     */




}
