package com.cdkj.loan.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.ICdbizAO;
import com.cdkj.loan.ao.ICreditAO;
import com.cdkj.loan.bo.IAttachmentBO;
import com.cdkj.loan.bo.IBizTaskBO;
import com.cdkj.loan.bo.IBudgetOrderBO;
import com.cdkj.loan.bo.ICdbizBO;
import com.cdkj.loan.bo.ICreditBO;
import com.cdkj.loan.bo.IMissionBO;
import com.cdkj.loan.bo.ISYSBizLogBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.Attachment;
import com.cdkj.loan.domain.BizTask;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.Cdbiz;
import com.cdkj.loan.domain.Credit;
import com.cdkj.loan.domain.SYSBizLog;

//CHECK ��鲢��ע�� 
@Service
public class CdbizAOImpl implements ICdbizAO {

    @Autowired
    private ICdbizBO cdbizBO;

    @Autowired
    private ICreditBO creditBO;

    @Autowired
    private IBudgetOrderBO budgetOrderBO;

    @Autowired
    private ICreditAO creditAO;

    @Autowired
    private IAttachmentBO attachmentBO;

    @Autowired
    private IBizTaskBO bizTaskBO;

    @Autowired
    private ISYSBizLogBO sysBizLogBO;

    @Autowired
    private IMissionBO missionBO;

    @Override
    public Paginable<Cdbiz> queryCdbizPage(int start, int limit, Cdbiz condition) {
        Paginable<Cdbiz> page = cdbizBO.getPaginable(start, limit, condition);
        for (Cdbiz cdbiz : page.getList()) {
            init(cdbiz);
        }
        return page;
    }

    @Override
    public List<Cdbiz> queryCdbizList(Cdbiz condition) {
        List<Cdbiz> list = cdbizBO.queryCdbizList(condition);
        for (Cdbiz cdbiz : list) {
            init(cdbiz);
        }
        return list;
    }

    @Override
    public Cdbiz getCdbiz(String code) {
        Cdbiz cdbiz = cdbizBO.getCdbiz(code);
        init(cdbiz);
        return cdbiz;
    }

    private void init(Cdbiz cdbiz) {
        Credit credit = creditBO.getCreditByBizCode(cdbiz.getCode());
        creditAO.initCredit(credit);
        cdbiz.setCredit(credit);
        BudgetOrder budgetOrder = budgetOrderBO.getBudgetOrderByBizCode(cdbiz
            .getCode());
        cdbiz.setBudgetOrder(budgetOrder);
        List<BizTask> bizTasks = bizTaskBO.queryBizTaskByBizCode(cdbiz
            .getCode());
        cdbiz.setBizTasks(bizTasks);
        List<SYSBizLog> bizLogs = sysBizLogBO.queryBizLogByBizCode(cdbiz
            .getCode());
        cdbiz.setBizLogs(bizLogs);
        List<Attachment> attachments = attachmentBO.queryBizAttachments(cdbiz
            .getCode());
        cdbiz.setAttachments(attachments);
    }
}
