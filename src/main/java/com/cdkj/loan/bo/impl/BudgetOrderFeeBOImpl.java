package com.cdkj.loan.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IBudgetOrderFeeBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.IBudgetOrderFeeDAO;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.BudgetOrderFee;
import com.cdkj.loan.enums.EBoolean;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.exception.BizException;

@Component
public class BudgetOrderFeeBOImpl extends PaginableBOImpl<BudgetOrderFee>
        implements IBudgetOrderFeeBO {

    @Autowired
    private IBudgetOrderFeeDAO budgetOrderFeeDAO;

    @Override
    public String saveBudgetOrderFee(BudgetOrder budgetOrder, String operator) {
        String code = null;
        BudgetOrderFee data = new BudgetOrderFee();
        code = OrderNoGenerater.generate(EGeneratePrefix.BUDGET_ORDER_FEE
            .getCode());
        data.setCode(code);
        data.setCompanyCode(budgetOrder.getCompanyCode());
        data.setUserId(budgetOrder.getSaleUserId());
        data.setCustomerName(budgetOrder.getApplyUserName());
        // 应收手续费=银行服务费+公证费+gps费+月供保证金+公司服务费+服务费
        data.setShouldAmount(budgetOrder.getBankFee()
                + budgetOrder.getAuthFee() + budgetOrder.getGpsFee()
                + budgetOrder.getMonthDeposit() + budgetOrder.getCompanyFee()
                + budgetOrder.getTeamFee());
        data.setRealAmount(0L);
        data.setIsSettled(EBoolean.NO.getCode());
        data.setUpdater(operator);
        data.setUpdateDatetime(new Date());
        data.setBudgetOrder(budgetOrder.getCode());
        budgetOrderFeeDAO.insert(data);
        return code;
    }

    @Override
    public List<BudgetOrderFee> queryBudgetOrderFeeList(BudgetOrderFee condition) {
        return budgetOrderFeeDAO.selectList(condition);
    }

    @Override
    public BudgetOrderFee getBudgetOrderFee(String code) {
        BudgetOrderFee data = null;
        if (StringUtils.isNotBlank(code)) {
            BudgetOrderFee condition = new BudgetOrderFee();
            condition.setCode(code);
            data = budgetOrderFeeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "手续费不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshBudgetOrderFee(BudgetOrderFee budgetOrderFee) {
        if (null != budgetOrderFee) {
            budgetOrderFeeDAO.updateBudgetOrderFeeRealAmount(budgetOrderFee);
        }
    }

    @Override
    public BudgetOrderFee getBudgetOrderFeeByBudget(String budgetOrderCode) {
        BudgetOrderFee condition = new BudgetOrderFee();
        condition.setBudgetOrder(budgetOrderCode);
        return budgetOrderFeeDAO.select(condition);
    }
}
