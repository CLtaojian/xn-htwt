package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ICdbizAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Cdbiz;
import com.cdkj.loan.dto.req.XN632515Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 分页查业务
 * @author: taojian 
 * @since: 2019年4月2日 下午7:47:52 
 * @history:
 */
public class XN632515 extends AProcessor {
    private ICdbizAO cdbizAO = SpringContextHolder.getBean(ICdbizAO.class);

    private XN632515Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Cdbiz condition = new Cdbiz();
        condition.setCode(req.getCode());
        condition.setBizType(req.getBizType());
        condition.setBizCode(req.getBizCode());
        condition.setBankCode(req.getBankCode());
        condition.setStatus(req.getStatus());
        condition.setMqStatus(req.getMqStatus());
        condition.setFbhgpsStatus(req.getFbhgpsStatus());
        condition.setFircundangStatus(req.getFircundangStatus());
        condition.setSeccundangStatus(req.getSeccundangStatus());
        condition.setZfStatus(req.getZfStatus());
        condition.setYwyUser(req.getYwyUser());
        condition.setTeamCode(req.getTeamCode());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cdbizAO.queryCdbizPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN632515Req.class);
        ObjValidater.validateReq(req);
    }
}
