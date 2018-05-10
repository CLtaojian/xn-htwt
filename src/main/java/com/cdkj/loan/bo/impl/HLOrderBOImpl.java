package com.cdkj.loan.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IHLOrderBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.IHLOrderDAO;
import com.cdkj.loan.domain.Account;
import com.cdkj.loan.domain.HLOrder;
import com.cdkj.loan.domain.Jour;
import com.cdkj.loan.enums.EDirection;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.enums.EHLOrderStatus;
import com.cdkj.loan.exception.BizException;

@Component
public class HLOrderBOImpl extends PaginableBOImpl<HLOrder>
        implements IHLOrderBO {
    @Autowired
    private IHLOrderDAO hlOrderDAO;

    @Override
    public String applyOrder(Account account, Jour jour, BigDecimal applyAmount,
            String applyUser, String applyNote) {
        if (applyAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn000000", "红蓝订单的变动金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.HLORDER.getCode());
        HLOrder data = new HLOrder();
        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());
        data.setAccountName(account.getRealName());

        data.setCurrency(account.getCurrency());
        data.setJourCode(jour.getCode());
        data.setChannelType(jour.getChannelType());
        if (applyAmount.compareTo(BigDecimal.ZERO) == 1) {
            data.setDirection(EDirection.PLUS.getCode());
        } else {
            data.setDirection(EDirection.MINUS.getCode());
        }

        data.setAmount(applyAmount);
        data.setStatus(EHLOrderStatus.toApprove.getCode());
        data.setApplyUser(applyUser);
        data.setApplyNote(applyNote);
        data.setApplyDatetime(new Date());

        hlOrderDAO.insert(data);
        return code;
    }

    @Override
    public void approveOrder(HLOrder order, EHLOrderStatus status,
            String approveUser, String approveNote) {
        order.setStatus(status.getCode());
        order.setApproveUser(approveUser);
        order.setApproveNote(approveNote);
        order.setApproveDatetime(new Date());
        hlOrderDAO.approveOrder(order);
    }

    @Override
    public HLOrder getHLOrder(String code, String systemCode) {
        HLOrder order = null;
        if (StringUtils.isNotBlank(code)) {
            HLOrder condition = new HLOrder();
            condition.setCode(code);
            condition.setSystemCode(systemCode);
            order = hlOrderDAO.select(condition);
            if (order == null) {
                throw new BizException("xn000000", "单号不存在");
            }
        }
        return order;
    }

    @Override
    public List<HLOrder> queryHLOrderList(HLOrder condition) {
        return hlOrderDAO.selectList(condition);
    }
}
