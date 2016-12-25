package com.xescm.ofc.service.impl;

import com.xescm.ofc.domain.OfcAttachment;
import com.xescm.ofc.exception.BusinessException;
import com.xescm.ofc.mapper.OfcAttachmentMapper;
import com.xescm.ofc.service.OfcAttachmentService;
import com.xescm.ofc.service.OfcOssManagerService;
import com.xescm.ofc.utils.CodeGenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by hujintao on 2016/12/17.
 */
@Service
public class OfcAttachmentServiceImpl extends BaseService<OfcAttachment>  implements OfcAttachmentService {

    @Autowired
    private OfcAttachmentMapper ofcAttachmentMapper;

    @Autowired
    private CodeGenUtils codeGenUtils;

    @Autowired
    private OfcOssManagerService ofcOssManagerService;

    @Override
    public OfcAttachment saveAttachment(OfcAttachment attachment) {
        attachment.setSerialNo(codeGenUtils.getNewWaterCode("AT",6));
        save(attachment);
        return attachment;
    }

    @Override
    public void deleteAttachmentByserialNo(OfcAttachment attachment) {
        OfcAttachment result=selectOne(attachment);
        if(result!=null){
            if(StringUtils.isEmpty(result.getPath())||StringUtils.isEmpty(result.getName())){
                throw new BusinessException("附件文件路径或附件文件名为空");
            }else{
                ofcOssManagerService.deleteFile(result.getPath()+result.getName());
                delete(attachment);
            }
        }else{
            throw new BusinessException("附件不存在");
        }
    }

    public void updatePicParamByserialNo(OfcAttachment attachment){
        ofcAttachmentMapper.updatePicParamByserialNo(attachment);
    }

    @Override
    public String operateAttachMent(String style,String serialNo) throws UnsupportedEncodingException {
       return  ofcOssManagerService.operateImage(style,serialNo);
    }
}