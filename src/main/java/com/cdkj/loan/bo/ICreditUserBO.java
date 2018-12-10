package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.CreditUser;
import com.cdkj.loan.enums.ELoanRole;

public interface ICreditUserBO extends IPaginableBO<CreditUser> {

    // 新增征信人员
    public void saveCreditUser(CreditUser creditUser);

    // 删除征信人员
    public void removeCreditUserByCreditCode(String creditCode);

    // 修改贷款角色
    public void refreshCreditUserLoanRole(CreditUser creditUser);

    // 查询征信人员
    public CreditUser getCreditUser(String code);

    // 录入银行征信结果
    public void inputBankCreditResult(CreditUser creditUser);

    // 批量查询征信人员
    public List<CreditUser> queryCreditUserList(CreditUser condition);

    // 查询对应的征信人员信息
    public List<CreditUser> queryCreditUserList(String creditCode);

    // 查询征信单共还人，担保人，本人
    public CreditUser getCreditUserByCreditCode(String creditCode,
            ELoanRole creditUserRelation);

}
