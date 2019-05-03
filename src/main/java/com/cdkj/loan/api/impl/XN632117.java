package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ICdbizAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN632117Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 征信详情查询
 *
 * @author: jiafr
 * @since: 2018年5月26日 下午2:33:29
 * @history:
 */
public class XN632117 extends AProcessor {

    private ICdbizAO cdbizAO = SpringContextHolder.getBean(ICdbizAO.class);

    private XN632117Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return cdbizAO.getCdbizAll(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN632117Req.class);
        ObjValidater.validateReq(req);

    }

}
