package com.cdkj.loan.dao;

import com.cdkj.loan.dao.base.IBaseDAO;
import com.cdkj.loan.domain.CreditUser;

/**
 * 
 * @author: jiafr 
 * @since: 2018年5月25日 下午1:46:20 
 * @history:
 */
public interface ICreditUserDAO extends IBaseDAO<CreditUser> {

    String NAMESPACE = ICreditUserDAO.class.getName().concat(".");

    // 录入银行征信结果
    public void inputBankCreditResult(CreditUser creditUser);

    public void updateCreditUser(CreditUser creditUser);
}
