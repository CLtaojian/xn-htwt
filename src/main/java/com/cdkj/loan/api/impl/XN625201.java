/**
 * @Title XN625200.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:12:21 
 * @version V1.0   
 */
package com.cdkj.loan.api.impl;

import java.util.Date;

import com.cdkj.loan.ao.IEthAddressAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.dto.req.XN625201Req;
import com.cdkj.loan.dto.req.XN625201ReqList;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/** 
 * 导入归集地址（无私钥）
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:12:21 
 * @history:
 */
public class XN625201 extends AProcessor {

    private IEthAddressAO ethAddressAO = SpringContextHolder
        .getBean(IEthAddressAO.class);

    private XN625201ReqList req = null;

    /** 
     * @see com.cdkj.loan.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {

        for (XN625201Req req : req.getwAddressList()) {
            // 想使用的日期 2017-09-01 至 2017-09-03
            // 数据库存储为 2017-09-01 00:00:00 至 2017-09-03 23:59:59
            Date start = DateUtil.getStartDatetime(req
                .getAvailableDatetimeStart());
            Date end = DateUtil.getEndDatetime(req.getAvailableDatetimeEnd());
            ethAddressAO.importWAddress(req.getAddress(), start, end);
        }

        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.loan.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625201ReqList.class);
    }

}
