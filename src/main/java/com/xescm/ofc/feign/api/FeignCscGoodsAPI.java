package com.xescm.ofc.feign.api;

import com.xescm.ofc.domain.dto.CscGoods;
import com.xescm.uam.utils.wrap.Wrapper;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

/**
 * Created by wang on 2016/10/18.
 */
public interface FeignCscGoodsAPI {
    @RequestLine("POST /api/csc/goods/queryCscGoodsList")
    @Headers("Content-Type: application/json")
    public Wrapper<List<CscGoods>> queryCscGoodsList(CscGoods cscGoods);

}
