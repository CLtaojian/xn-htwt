package com.cdkj.loan.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IBankBO;
import com.cdkj.loan.bo.IBudgetOrderBO;
import com.cdkj.loan.bo.ILogisticsBO;
import com.cdkj.loan.bo.INodeFlowBO;
import com.cdkj.loan.bo.ISYSBizLogBO;
import com.cdkj.loan.bo.ISYSUserBO;
import com.cdkj.loan.bo.base.Page;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.IBudgetOrderDAO;
import com.cdkj.loan.domain.Bank;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.Credit;
import com.cdkj.loan.domain.CreditUser;
import com.cdkj.loan.domain.NodeFlow;
import com.cdkj.loan.domain.SYSUser;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.enums.EBizLogType;
import com.cdkj.loan.enums.EBoolean;
import com.cdkj.loan.enums.EBudgetOrderNode;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.enums.EIDKind;
import com.cdkj.loan.enums.ELoanRole;
import com.cdkj.loan.enums.ELogisticsType;
import com.cdkj.loan.exception.BizException;

@Component
public class BudgetOrderBOImpl extends PaginableBOImpl<BudgetOrder>
        implements IBudgetOrderBO {

    @Autowired
    private IBudgetOrderDAO budgetOrderDAO;

    @Autowired
    private INodeFlowBO nodeFlowBO;

    @Autowired
    private ILogisticsBO logisticsBO;

    @Autowired
    private ISYSBizLogBO sysBizLogBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IBankBO bankBO;

    @Override
    public String saveBudgetOrder(Credit credit) {
        List<CreditUser> creditUserList = credit.getCreditUserList();
        CreditUser applyCreditUser = null;
        CreditUser ghrCreditUser = null;
        CreditUser guaCreditUser = null;
        for (CreditUser creditUser : creditUserList) {
            if (applyCreditUser == null && ELoanRole.APPLY_USER.getCode()
                .equals(creditUser.getLoanRole())) {
                applyCreditUser = creditUser;
            }
            if (ghrCreditUser == null && ELoanRole.GHR.getCode()
                .equals(creditUser.getLoanRole())) {
                ghrCreditUser = creditUser;
            }
            if (guaCreditUser == null && ELoanRole.GUARANTOR.getCode()
                .equals(creditUser.getLoanRole())) {
                guaCreditUser = creditUser;
            }
        }

        String code = null;
        if (credit != null) {
            BudgetOrder data = new BudgetOrder();
            code = OrderNoGenerater
                .generate(EGeneratePrefix.BUDGETORDER.getCode());
            data.setCode(code);
            data.setCreditCode(credit.getCode());
            data.setBizType(credit.getBizType());

            data.setLoanAmount(credit.getLoanAmount());
            data.setLoanBank(credit.getLoanBankCode());
            Bank bank = bankBO.getBank(credit.getLoanBankCode());
            data.setRepayBankCode(bank.getCode());
            data.setRepayBankName(bank.getBankName());

            data.setRepaySubbranch(bank.getSubbranch());
            data.setApplyUserName(applyCreditUser.getUserName());
            data.setMobile(applyCreditUser.getMobile());
            data.setIdNo(applyCreditUser.getIdNo());
            data.setIdKind(EIDKind.IDCard.getCode());

            // 共还人=配偶
            if (ghrCreditUser != null) {
                data.setMateName(ghrCreditUser.getUserName());
                data.setMateMobile(ghrCreditUser.getMobile());
                data.setMateIdNo(ghrCreditUser.getIdNo());
            }

            if (guaCreditUser != null) {
                data.setGuaName(guaCreditUser.getUserName());
                data.setGuaMobile(guaCreditUser.getMobile());
                data.setGuaIdNo(guaCreditUser.getIdNo());
            }

            data.setApplyDatetime(new Date());
            data.setCompanyCode(credit.getCompanyCode());
            data.setSaleUserId(credit.getSaleUserId());
            data.setInsideJob(credit.getInsideJob());
            data.setCurNodeCode(EBudgetOrderNode.WRITE_BUDGET_ORDER.getCode());
            // 准入单插入团队编号 来自业务员的所属团队
            SYSUser user = sysUserBO.getUser(credit.getSaleUserId());
            data.setTeamCode(user.getTeamCode());
            data.setPledgeStatus(EBoolean.NO.getCode());
            data.setIsGpsAz(EBoolean.NO.getCode());
            data.setIsLogistics(EBoolean.NO.getCode());
            budgetOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public void refreshBudgetOrder(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.update(data);
        }
    }

    @Override
    public void refreshAreaApprove(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterAreaApprove(data);
        }
    }

    @Override
    public void refreshriskApprove(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterIskApprove(data);
        }
    }

    @Override
    public void refreshriskChargeApprove(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterIskChargeApprove(data);
        }
    }

    @Override
    public void interview(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterInterview(data);
        }
    }

    @Override
    public void refreshbizChargeApprove(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterBizChargeApprove(data);
        }
    }

    @Override
    public void advancefund(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterAdvancefund(data);
        }
    }

    @Override
    public void refreshGpsManagerApprove(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterGpsManagerApprove(data);
        }
    }

    @Override
    public void installGps(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterInstallGps(data);
        }
    }

    @Override
    public void carSettle(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterCarSettle(data);
        }
    }

    @Override
    public void refreshCommitBank(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterCommitBank(data);
        }
    }

    @Override
    public void refreshEntryFk(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterEntryFk(data);
        }
    }

    @Override
    public void refreshConfirmReceipt(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterConfirmReceipt(data);
        }
    }

    @Override
    public void entryMortgage(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterEntryMortgage(data);
        }
    }

    @Override
    public void refreshMortgageCommitBank(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterMortgageCommitBank(data);
        }
    }

    @Override
    public void refreshMortgageFinish(BudgetOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            budgetOrderDAO.updaterMortgageFinish(data);
        }
    }

    @Override
    public List<BudgetOrder> queryBudgetOrderList(BudgetOrder condition) {
        return budgetOrderDAO.selectList(condition);
    }

    @Override
    public BudgetOrder getBudgetOrder(String code) {
        BudgetOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            BudgetOrder condition = new BudgetOrder();
            condition.setCode(code);
            data = budgetOrderDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "预算单不存在！！");
            }
        }
        return data;
    }

    @Override
    public BudgetOrder getBudgetOrderByRepayBizCode(String repayBizCode) {
        BudgetOrder data = null;
        if (StringUtils.isNotBlank(repayBizCode)) {
            BudgetOrder condition = new BudgetOrder();
            condition.setRepayBizCode(repayBizCode);
            data = budgetOrderDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "预算单不存在！！");
            }
        }
        return data;
    }

    @Override
    public int archiveSuccess(BudgetOrder budgetOrder, String repayBizCode,
            String userId) {
        int count = 0;
        if (budgetOrder != null) {
            budgetOrder.setRepayBizCode(repayBizCode);
            budgetOrder.setApplyUserId(userId);
            count = budgetOrderDAO.updateArchiveSuccess(budgetOrder);
        }
        return count;
    }

    /** 
     * @see com.cdkj.loan.bo.IBudgetOrderBO#logicOrder(com.cdkj.loan.domain.BudgetOrder)
     */
    @Override
    public String logicOrder(String code, String operator) {
        String result = EBoolean.NO.getCode();

        BudgetOrder budgetOrder = getBudgetOrder(code);
        String preCurNodeCode = budgetOrder.getCurNodeCode();
        NodeFlow nodeFlow = nodeFlowBO.getNodeFlowByCurrentNode(preCurNodeCode);
        budgetOrder.setCurNodeCode(nodeFlow.getNextNode());
        // 状态为不在物流传递中
        budgetOrder.setIsLogistics(EBoolean.NO.getCode());
        // 日志
        sysBizLogBO.saveNewAndPreEndSYSBizLog(budgetOrder.getCode(),
            EBizLogType.BUDGET_ORDER, budgetOrder.getCode(), preCurNodeCode,
            budgetOrder.getCurNodeCode(), null, operator,
            budgetOrder.getTeamCode());
        if (EBudgetOrderNode.DHAPPROVEDATA.getCode()
            .equals(nodeFlow.getCurrentNode())) {
            String newLogisticsCode = logisticsBO.saveLogistics(
                ELogisticsType.BUDGET.getCode(), budgetOrder.getCode(),
                operator, nodeFlow.getCurrentNode(), nodeFlow.getNextNode(),
                null);
            // 产生物流单后改变状态为物流传递中
            budgetOrder.setIsLogistics(EBoolean.YES.getCode());

            // 资料传递日志
            sysBizLogBO.saveSYSBizLog(code, EBizLogType.LOGISTICS,
                newLogisticsCode, budgetOrder.getCurNodeCode(),
                budgetOrder.getTeamCode());
            result = EBoolean.YES.getCode();

        }
        budgetOrderDAO.updaterLogicNode(budgetOrder);

        return result;
    }

    @Override
    public Paginable<BudgetOrder> getPaginableByRoleCode(int start,
            int pageSize, BudgetOrder condition) {
        prepare(condition);
        long totalCount = budgetOrderDAO.selectTotalCountByRoleCode(condition);
        Paginable<BudgetOrder> page = new Page<BudgetOrder>(start, pageSize,
            totalCount);
        List<BudgetOrder> dataList = budgetOrderDAO
            .selectBudgetOrderByRoleCodeList(condition, page.getStart(),
                page.getPageSize());
        page.setList(dataList);
        return page;
    }

    @Override
    public List<BudgetOrder> getPaginableByDz(BudgetOrder condition) {
        return budgetOrderDAO.selectBudgetOrderByDzList(condition);
    }

    @Override
    public void saveBackAdvanceFund(BudgetOrder budgetOrder) {

        budgetOrderDAO.insertBackAdvanceFund(budgetOrder);

    }

    @Override
    public void confirmBackAdvanceFund(BudgetOrder budgetOrder) {
        budgetOrderDAO.confirmBackAdvanceFund(budgetOrder);
    }

    @Override
    public void applyCancel(BudgetOrder budgetOrder) {

        budgetOrderDAO.applyCancel(budgetOrder);

    }

    @Override
    public void cancelBizAudit(BudgetOrder budgetOrder) {
        budgetOrderDAO.cancelBizAudit(budgetOrder);
    }

    @Override
    public void cancelFinanceAudit(BudgetOrder budgetOrder) {
        budgetOrderDAO.cancelFinanceAudit(budgetOrder);
    }

    @Override
    public void dataSupplement(BudgetOrder budgetOrder) {
        budgetOrderDAO.dataSupplement(budgetOrder);
    }

    @Override
    public void updateIsLogistics(BudgetOrder budgetOrder) {
        budgetOrderDAO.updateIsLogistics(budgetOrder);
    }

    @Override
    public Paginable<BudgetOrder> getPaginableByTeamCode(int start, int limit,
            BudgetOrder condition) {
        prepare(condition);
        long totalCount = budgetOrderDAO.selectTotalCountByTeamCode(condition);
        Paginable<BudgetOrder> page = new Page<BudgetOrder>(start, limit,
            totalCount);
        List<BudgetOrder> dataList = budgetOrderDAO
            .selectBudgetOrderListByTeamCode(condition, page.getStart(),
                page.getPageSize());
        page.setList(dataList);
        return page;
    }

    @Override
    public List<BudgetOrder> queryBudgetOrderByApplyUserName(
            BudgetOrder condition) {
        return budgetOrderDAO.queryBudgetOrderByApplyUserName(condition);
    }

}
