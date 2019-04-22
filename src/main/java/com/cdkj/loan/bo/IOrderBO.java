package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.Order;
import com.cdkj.loan.enums.EPayType;

/** 
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:23:54 
 * @history:
 */
public interface IOrderBO extends IPaginableBO<Order> {

    public int saveOrder(Order order);

    public int userCancel(String code, String userId, String remark);

    public int platCancel(String code, String updater, String remark,
            String status);

    public int promptToSend(Order data);

    public int refreshPayYESuccess(Order order, Long payAmount, String payType);

    public int refreshPaySuccess(Order order, Long payAmount, String payCode);

    public int refreshRelationPaySuccess(Order order, Long payAmount,
            String payType, String payGroup, String payCode);

    public int refreshYunfei(Order order, Long yunfei);

    public int comment(Order order);

    public List<Order> queryOrderList(Order data);

    public Order getOrder(String code);

    public String addPayGroup(String code, EPayType payType);

    public String addPayGroup(String code, EPayType payType, Long dkCnyAmount,
            Long dkJfAmount);

    public List<Order> queryOrderListByPayGroup(String payGroup);

    public Long selectXFAmount(String userId);

    public void removeOrder(String code);

    public int refreshStatus(Order order);
}
