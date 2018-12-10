package com.cdkj.loan.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.ICreditUserBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.ICreditUserDAO;
import com.cdkj.loan.domain.CreditUser;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.enums.ELoanRole;
import com.cdkj.loan.exception.BizException;

/**
 * 
 * @author: jiafr 
 * @since: 2018年5月25日 下午2:04:01 
 * @history:
 */
@Component
public class CreditUserBOImpl extends PaginableBOImpl<CreditUser>
        implements ICreditUserBO {

    @Autowired
    private ICreditUserDAO creditUserDAO;

    @Override
    public void saveCreditUser(CreditUser creditUser) {
        String code = null;
        if (creditUser != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.CREDITUSER.getCode());
            creditUser.setCode(code);
            creditUserDAO.insert(creditUser);
        }

    }

    @Override
    public void removeCreditUserByCreditCode(String creditCode) {
        CreditUser creditUser = new CreditUser();
        creditUser.setCreditCode(creditCode);
        List<CreditUser> list = creditUserDAO.selectList(creditUser);
        for (CreditUser data : list) {
            creditUserDAO.delete(data);
        }
    }

    @Override
    public void refreshCreditUserLoanRole(CreditUser creditUser) {
        creditUserDAO.updateCreditUser(creditUser);
    }

    @Override
    public CreditUser getCreditUser(String code) {

        CreditUser data = null;
        if (StringUtils.isNotBlank(code)) {
            CreditUser creditUser = new CreditUser();
            creditUser.setCode(code);
            data = creditUserDAO.select(creditUser);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "征信人员不存在!");
            }
        }
        return data;
    }

    @Override
    public void inputBankCreditResult(CreditUser creditUser) {
        if (StringUtils.isNotBlank(creditUser.getCode())) {
            creditUserDAO.inputBankCreditResult(creditUser);
        }

    }

    @Override
    public List<CreditUser> queryCreditUserList(CreditUser condition) {

        return creditUserDAO.selectList(condition);
    }

    @Override
    public List<CreditUser> queryCreditUserList(String creditCode) {
        CreditUser condition = new CreditUser();
        condition.setCreditCode(creditCode);
        return creditUserDAO.selectList(condition);
    }

    @Override
    public CreditUser getCreditUserByCreditCode(String creditCode,
            ELoanRole creditUserLoanRole) {
        CreditUser creditUser = null;
        CreditUser condition = new CreditUser();
        condition.setCreditCode(creditCode);
        condition.setLoanRole(creditUserLoanRole.getCode());

        List<CreditUser> list = creditUserDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            creditUser = list.get(0);
        }
        return creditUser;
    }

}
