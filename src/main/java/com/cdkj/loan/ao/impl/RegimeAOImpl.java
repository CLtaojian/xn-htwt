package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.loan.ao.IRegimeAO;
import com.cdkj.loan.bo.IRegimeBO;
import com.cdkj.loan.bo.IScopePeopleBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.Regime;
import com.cdkj.loan.domain.ScopePeople;
import com.cdkj.loan.dto.req.XN632730Req;
import com.cdkj.loan.dto.req.XN632731Req;
import com.cdkj.loan.enums.ENoticeRegime;
import com.cdkj.loan.exception.BizException;

/**
 * 公司制度
 * @author: silver 
 * @since: 2018年6月4日 下午8:41:44 
 * @history:
 */
@Service
public class RegimeAOImpl implements IRegimeAO {
    @Autowired
    private IRegimeBO regimeBO;

    @Autowired
    private IScopePeopleBO scopePeopleBO;

    @Override
    @Transactional
    public String addRegime(XN632730Req req) {
        Regime condition = new Regime();
        condition.setRegimeCode(req.getRegimeCode());
        if (regimeBO.getTotalCount(condition) != 0) {
            throw new BizException("xn0000", "制度编号已存在，请重新输入！");
        }

        Regime data = new Regime();
        data.setName(req.getName());
        data.setRegimeCode(req.getRegimeCode());
        data.setType(req.getType());
        data.setContent(req.getContent());
        data.setUpdateDatetime(new Date());
        String regimeCode = regimeBO.saveRegime(data);

        // 添加制度范围
        scopePeopleBO.saveScopePeople(regimeCode,
            ENoticeRegime.REMIGE.getCode(), req.getScopePeopleList());
        return regimeCode;
    }

    @Override
    public Paginable<Regime> queryRegimePage(int start, int limit,
            Regime condition) {
        return regimeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Regime> queryRegimeList(Regime condition) {
        return regimeBO.queryRegimeList(condition);
    }

    @Override
    public Regime getRegime(String code) {
        Regime regime = regimeBO.getRegime(code);
        ScopePeople scopePeople = new ScopePeople();
        scopePeople.setRefCode(code);
        regime.setScopePeopleList(
            scopePeopleBO.queryScopePeopleList(scopePeople));

        return regime;
    }

    @Override
    @Transactional
    public void editRegime(XN632731Req req) {
        Regime data = new Regime();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setRegimeCode(req.getRegimeCode());
        data.setType(req.getType());
        data.setContent(req.getContent());

        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        regimeBO.editRegime(data);

        // 添加制度范围
        scopePeopleBO.dropScopePeopleByRef(req.getCode());
        scopePeopleBO.saveScopePeople(req.getCode(),
            ENoticeRegime.REMIGE.getCode(), req.getScopePeopleList());
    }

    @Override
    public void publishRegime(String code, String updater, String remark) {
        regimeBO.publishRegime(code, updater, remark);
    }

    @Override
    public void removeRegime(String code, String updater, String remark) {
        regimeBO.removeRegime(code, updater, remark);
    }
}
