package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.IBudgetOrderFeeAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.BudgetOrderFee;
import com.cdkj.loan.dto.req.XN632165Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 手续费分页查询
 * @author: jiafr 
 * @since: 2018年5月30日 下午9:16:46 
 * @history:
 */
public class XN632165 extends AProcessor {

    private IBudgetOrderFeeAO budgetOrderFeeAO = SpringContextHolder
        .getBean(IBudgetOrderFeeAO.class);

    private XN632165Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BudgetOrderFee condition = new BudgetOrderFee();
        condition.setUserId(req.getUserId());
        condition.setCustomerName(req.getCustomerName());
        condition.setIsSettled(req.getIsSettled());
        condition.setBudgetOrder(req.getBudgetOrder());
        condition.setCurNodeCode(req.getCurNodeCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBudgetOrderFeeAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return budgetOrderFeeAO.queryBudgetOrderFeePage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN632165Req.class);
        ObjValidater.validateReq(req);
    }
}
