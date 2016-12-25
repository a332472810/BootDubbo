package com.xescm.ofc.service;

import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * Created by hujintao on 2016/12/18.
 */
public interface OfcOssManagerService {
    public URL getFileURL(String filePath) ;

    public void deleteFile(String filePath);

    public String operateImage(String operate,String serialNo) throws UnsupportedEncodingException;
}