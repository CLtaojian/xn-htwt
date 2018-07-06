package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.ILoanProductAO;
import com.cdkj.loan.bo.IBankBO;
import com.cdkj.loan.bo.ILoanProductBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Bank;
import com.cdkj.loan.domain.LoanProduct;
import com.cdkj.loan.dto.req.XN632170Req;
import com.cdkj.loan.dto.req.XN632172Req;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.enums.EProductStatus;

/**
 * 贷款产品
 * @author: silver 
 * @since: 2018年5月30日 下午12:59:00 
 * @history:
 */
@Service
public class LoanProductAOImpl implements ILoanProductAO {
    @Autowired
    private ILoanProductBO loanProductBO;

    @Autowired
    private IBankBO bankBO;

    @Override
    public String saveLoanProduct(XN632170Req req) {
        // 验证产品名称是否重复
        String code = OrderNoGenerater.generate(EGeneratePrefix.LOAN_PRODUCT
            .getCode());
        LoanProduct data = new LoanProduct();
        data.setCode(code);
        data.setType(req.getType());
        data.setName(req.getName());
        data.setLoanBank(req.getLoanBank());
        data.setWanFactor(StringValidater.toLong(req.getWanFactor()));

        data.setYearRate(StringValidater.toDouble(req.getYearRate()));
        data.setGpsFee(StringValidater.toLong(req.getGpsFee()));
        data.setAuthRate(StringValidater.toDouble(req.getAuthRate()));
        data.setBackRate(StringValidater.toDouble(req.getBackRate()));
        data.setIsPre(req.getIsPre());
        data.setPreRate(StringValidater.toDouble(req.getPreRate()));

        data.setStatus(EProductStatus.TO_PUBLISH.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        loanProductBO.saveLoanProduct(data);
        return code;
    }

    @Override
    public void dropLoanProduct(String code) {
        loanProductBO.dropLoanProduct(code);
    }

    @Override
    public void editLoanProduct(XN632172Req req) {
        LoanProduct data = new LoanProduct();
        data.setCode(req.getCode());
        data.setType(req.getType());
        data.setName(req.getName());
        data.setLoanBank(req.getLoanBank());
        data.setWanFactor(StringValidater.toLong(req.getWanFactor()));

        data.setYearRate(StringValidater.toDouble(req.getYearRate()));
        data.setGpsFee(StringValidater.toLong(req.getGpsFee()));
        data.setAuthRate(StringValidater.toDouble(req.getAuthRate()));
        data.setBackRate(StringValidater.toDouble(req.getBackRate()));
        data.setIsPre(req.getIsPre());
        data.setPreRate(StringValidater.toDouble(req.getPreRate()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        loanProductBO.editLoanProduct(data);
    }

    @Override
    public void publishYesLoanProduct(String code, String updater) {
        loanProductBO.publishYesLoanProduct(code, updater);
    }

    @Override
    public void publishNoLoanProduct(String code, String updater) {
        loanProductBO.publishNoLoanProduct(code, updater);
    }

    @Override
    public Paginable<LoanProduct> queryLoanProductPage(int start, int limit,
            LoanProduct condition) {
        Paginable<LoanProduct> page = loanProductBO.getPaginable(start, limit,
            condition);
        List<LoanProduct> loanProductList = page.getList();
        for (LoanProduct loanProduct : loanProductList) {
            Bank bank = bankBO.getBank(loanProduct.getLoanBank());
            loanProduct.setLoanBankName(bank.getBankName());
        }

        return page;
    }

    @Override
    public List<LoanProduct> queryLoanProductList(LoanProduct condition) {
        List<LoanProduct> loanProductList = loanProductBO
            .queryLoanProductList(condition);
        for (LoanProduct loanProduct : loanProductList) {
            Bank bank = bankBO.getBank(loanProduct.getLoanBank());
            loanProduct.setLoanBankName(bank.getBankName());
        }

        return loanProductList;
    }

    @Override
    public LoanProduct getLoanProduct(String code) {
        LoanProduct loanProduct = loanProductBO.getLoanProduct(code);
        Bank bank = bankBO.getBank(loanProduct.getLoanBank());
        loanProduct.setLoanBankName(bank.getBankName());
        return loanProduct;
    }
}
