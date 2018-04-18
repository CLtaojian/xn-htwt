package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.ITradeOrderAO;
import com.cdkj.loan.ao.IUserAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.User;
import com.cdkj.loan.domain.UserStatistics;
import com.cdkj.loan.dto.req.XN805120Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

import java.util.List;

/**
 * 分页查询用户列表
 * @author: xieyj 
 * @since: 2017年7月16日 下午4:38:56 
 * @history:
 */
public class XN805120 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private ITradeOrderAO tradeOrderAO = SpringContextHolder
        .getBean(ITradeOrderAO.class);

    private XN805120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        User condition = new User();
        condition.setLoginName(req.getLoginName());
        condition.setMobileForQuery(req.getMobile());
        condition.setNicknameForQuery(req.getNickname());
        condition.setKind(req.getKind());
        condition.setLevel(req.getLevel());
        condition.setUserReferee(req.getUserReferee());

        condition.setIdKind(req.getIdKind());
        condition.setIdNo(req.getIdNo());
        condition.setRealName(req.getRealName());
        condition.setRoleCode(req.getRoleCode());
        condition.setStatus(req.getStatus());

        condition.setUnionId(req.getUnionId());
        condition.setH5OpenId(req.getH5OpenId());
        condition.setAppOpenId(req.getAppOpenId());
        condition.setGender(req.getGender());
        condition.setGender(req.getGender());
        condition.setBirthday(req.getBirthday());
        condition.setEmail(req.getEmail());

        condition.setDiploma(req.getDiploma());
        condition.setOccupation(req.getOccupation());

        condition.setWorkTime(req.getWorkTime());
        condition.setProvince(req.getProvince());
        condition.setCity(req.getCity());
        condition.setArea(req.getArea());
        condition.setLongitude(req.getLongitude());
        condition.setLatitude(req.getLatitude());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getCreateDatetimeStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(
            req.getCreateDatetimeEnd(), true));

        condition.setUpdater(req.getUpdater());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        Paginable<User> paginable = userAO.queryUserPage(start, limit,
            condition);
        List<User> userList = paginable.getList();
        for (User user : userList) {
            UserStatistics userStatistics = this.tradeOrderAO
                .userStatisticsInfoNotContainTradeCount(user.getUserId());
            user.setUserStatistics(userStatistics);

        }
        return paginable;
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805120Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater
            .validateBlank(req.getCompanyCode(), req.getSystemCode());
    }
}
