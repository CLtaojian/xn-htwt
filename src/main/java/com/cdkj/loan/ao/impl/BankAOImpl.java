package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.loan.ao.IBankAO;
import com.cdkj.loan.bo.IBankBO;
import com.cdkj.loan.bo.IBankRateBO;
import com.cdkj.loan.bo.IBankSubbranchBO;
import com.cdkj.loan.bo.ISYSUserBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.creditCommon.StringUtils;
import com.cdkj.loan.domain.Bank;
import com.cdkj.loan.domain.SYSUser;
import com.cdkj.loan.dto.req.XN632030Req;
import com.cdkj.loan.dto.req.XN632032Req;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.enums.EBoolean;
import com.cdkj.loan.exception.BizException;

/**
 * 银行信息
 * @author: silver 
 * @since: 2018年5月27日 下午4:51:22 
 * @history:
 */
@Service
public class BankAOImpl implements IBankAO {

    @Autowired
    private IBankBO bankBO;

    @Autowired
    private IBankRateBO bankRateBO;

    @Autowired
    private IBankSubbranchBO bankSubbranchBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    @Transactional
    public String addBank(XN632030Req req) {
        Bank bank = new Bank();
        bank.setBankCode(req.getBankCode());
        List<Bank> bankList = bankBO.queryBankList(bank);
        if (CollectionUtils.isNotEmpty(bankList)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "银行信息已存在，请勿重复添加!");
        }
        Bank data = new Bank();
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setSubbranch(req.getSubbranch());
        data.setRate12(StringValidater.toDouble(req.getRate12()));
        data.setRate18(StringValidater.toDouble(req.getRate18()));
        data.setRate24(StringValidater.toDouble(req.getRate24()));

        data.setRate36(StringValidater.toDouble(req.getRate36()));
        data.setStatus(EBoolean.YES.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        String code = bankBO.saveBank(data);

        // 保存利率明细
        // bankRateBO.saveBankRate(req.getBankRateList(), code);
        return code;
    }

    @Override
    @Transactional
    public void dropBank(String code) {
        bankBO.dropBank(code);

        // 删除利率明细
        // BankRate rateCondition = new BankRate();
        // rateCondition.setBankCode(code);
        // bankRateBO.dropBankRate(rateCondition);

        // 删除支行信息
        // BankSubbranch subCondition = new BankSubbranch();
        // subCondition.setBankCode(code);
        // bankSubbranchBO.dropBankSubbranch(subCondition);
    }

    @Override
    @Transactional
    public int editBank(XN632032Req req) {
        // // 删除利率明细
        // BankRate rateCondition = new BankRate();
        // rateCondition.setBankCode(req.getCode());
        // bankRateBO.dropBankRate(rateCondition);

        // 编辑银行信息
        Bank data = bankBO.getBank(req.getCode());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setSubbranch(req.getSubbranch());

        data.setRate12(StringValidater.toDouble(req.getRate12()));
        data.setRate18(StringValidater.toDouble(req.getRate18()));
        data.setRate24(StringValidater.toDouble(req.getRate24()));
        data.setRate36(StringValidater.toDouble(req.getRate36()));

        data.setStatus(req.getStatus());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        // 保存利率明细
        // bankRateBO.saveBankRate(req.getBankRateList(), req.getCode());
        return bankBO.editBank(data);
    }

    @Override
    public Bank getBank(String code) {
        Bank data = bankBO.getBank(code);
        //
        // BankRate rateCondition = new BankRate();
        // rateCondition.setBankCode(code);
        // data.setBankRateList(bankRateBO.queryBankRateList(rateCondition));
        return data;
    }

    @Override
    public Paginable<Bank> queryBankPage(int start, int limit, Bank condition) {
        Paginable<Bank> page = bankBO.getPaginable(start, limit, condition);
        // List<Bank> bankList = page.getList();
        // List<BankRate> bankRateList = null;
        // BankRate rateCondition = new BankRate();
        //
        // // 添加利率明细信息
        // for (Bank data : bankList) {
        // rateCondition.setBankCode(condition.getCode());
        // bankRateList = bankRateBO.queryBankRateList(rateCondition);
        // data.setBankRateList(bankRateList);
        // }
        for (Bank bank : page.getList()) {
            if (StringUtils.isNotBlank(bank.getUpdater())) {
                SYSUser user = sysUserBO.getUser(bank.getUpdater());
                bank.setUpdaterName(user.getRealName());
            }
        }
        return page;
    }

    @Override
    public List<Bank> queryBankList(Bank condition) {
        List<Bank> bankList = bankBO.queryBankList(condition);
        // BankRate rateCondition = new BankRate();
        // List<BankRate> bankRateList = null;
        // // 添加利率明细信息
        // for (Bank data : bankList) {
        // rateCondition.setBankCode(condition.getCode());
        // bankRateList = bankRateBO.queryBankRateList(rateCondition);
        // data.setBankRateList(bankRateList);
        // }

        return bankList;
    }
}
