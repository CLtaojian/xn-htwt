package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.IBudgetOrderAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN632131Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.enums.EBoolean;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 预算单-录入抵押信息
 * @author: CYL 
 * @since: 2018年5月31日 上午3:20:09 
 * @history:
 */
public class XN632131 extends AProcessor {
    private IBudgetOrderAO budgetOrderAO = SpringContextHolder
        .getBean(IBudgetOrderAO.class);

    private XN632131Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        budgetOrderAO.entryMortgage(req.getCode(), req.getOperator(),
            req.getPledgeDatetime(), req.getGreenBigSmj());
        return new BooleanRes(true, EBoolean.YES.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN632131Req.class);
        ObjValidater.validateReq(req);
    }

}
