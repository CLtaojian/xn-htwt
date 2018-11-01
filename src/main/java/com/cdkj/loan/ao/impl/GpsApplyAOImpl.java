package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.loan.ao.IGpsApplyAO;
import com.cdkj.loan.bo.IBizTeamBO;
import com.cdkj.loan.bo.IDepartmentBO;
import com.cdkj.loan.bo.IGpsApplyBO;
import com.cdkj.loan.bo.IGpsBO;
import com.cdkj.loan.bo.ILogisticsBO;
import com.cdkj.loan.bo.ISYSBizLogBO;
import com.cdkj.loan.bo.ISYSRoleBO;
import com.cdkj.loan.bo.ISYSUserBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.dao.IBudgetOrderDAO;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.Department;
import com.cdkj.loan.domain.Gps;
import com.cdkj.loan.domain.GpsApply;
import com.cdkj.loan.domain.SYSRole;
import com.cdkj.loan.domain.SYSUser;
import com.cdkj.loan.dto.req.XN632710Req;
import com.cdkj.loan.dto.req.XN632711Req;
import com.cdkj.loan.dto.req.XN632711ReqChild;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.enums.EBoolean;
import com.cdkj.loan.enums.EGpsApplyStatus;
import com.cdkj.loan.enums.ELogisticsType;
import com.cdkj.loan.exception.BizException;

/**
 * GPS申领
 * @author: silver 
 * @since: 2018年5月30日 下午11:19:42 
 * @history:
 */
@Service
public class GpsApplyAOImpl implements IGpsApplyAO {
    @Autowired
    private IGpsApplyBO gpsApplyBO;

    @Autowired
    private IGpsBO gpsBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IDepartmentBO departmentBO;

    @Autowired
    private ILogisticsBO logisticsBO;

    @Autowired
    private ISYSBizLogBO sysBizLogBO;

    @Autowired
    private IBizTeamBO bizTeamBO;

    @Autowired
    private ISYSRoleBO sysRoleBO;

    @Autowired
    private IBudgetOrderDAO budgetOrderDAO;

    @Override
    @Transactional
    public String addGpsApply(XN632710Req req) {
        // undo 待验证库存数量和申请数量
        GpsApply data = new GpsApply();
        data.setType(req.getType());
        SYSUser sysUser = sysUserBO.getUser(req.getApplyUser());
        if (StringUtils.isBlank(sysUser.getPostCode())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "申请人岗位为空，请先设置岗位");
        }
        data.setCompanyCode(sysUser.getCompanyCode());
        data.setApplyUser(req.getApplyUser());
        data.setApplyReason(req.getApplyReason());
        data.setApplyWiredCount(
            StringValidater.toInteger(req.getApplyWiredCount()));
        data.setApplyWirelessCount(
            StringValidater.toInteger(req.getApplyWirelessCount()));
        data.setApplyCount(
            data.getApplyWiredCount() + data.getApplyWirelessCount());
        if (StringUtils.isNotBlank(req.getBudgetOrderCode())) {
            // 验证预算单编号存不存在
            BudgetOrder budgetOrder = new BudgetOrder();
            budgetOrder.setCode(req.getBudgetOrderCode());
            BudgetOrder domain = budgetOrderDAO.select(budgetOrder);
            if (domain == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "请检查客户姓名是否从下拉框中选择!");
            }
            data.setBudgetOrderCode(req.getBudgetOrderCode());
        }
        data.setCustomerName(req.getCustomerName());
        data.setMobile(req.getMobile());
        data.setCarFrameNo(req.getCarFrameNo());
        data.setApplyDatetime(new Date());
        data.setStatus(EGpsApplyStatus.TO_APPROVE.getCode());
        return gpsApplyBO.saveGpsApply(data);
    }

    @Override
    @Transactional
    public void approveYesGpsApply(XN632711Req req) {
        GpsApply data = gpsApplyBO.getGpsApply(req.getCode());
        if (!EGpsApplyStatus.TO_APPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "GPS申领单不在待审核状态");
        }
        gpsApplyBO.approveGpsApply(req.getCode(), EGpsApplyStatus.APPROVE_YES,
            req.getRemark());

        for (XN632711ReqChild childReq : req.getGpsList()) {
            if (StringUtils.isBlank(childReq.getCode())) {
                throw new BizException("xn0000", "GPS编号不能为空");
            }
            Gps gps = new Gps();
            gps.setCode(childReq.getCode());
            gps.setApplyCode(data.getCode());
            gps.setCompanyCode(data.getCompanyCode());
            gps.setApplyUser(data.getApplyUser());
            gps.setApplyStatus(EBoolean.NO.getCode());
            gps.setApplyDatetime(data.getApplyDatetime());
            if (StringUtils.isNotBlank(data.getCustomerName())) {
                gps.setCustomerName(data.getCustomerName());
            }
            gpsBO.applyGps(gps);
        }
        // 产生物流单
        logisticsBO.saveLogisticsGps(ELogisticsType.GPS.getCode(),
            data.getCode(), data.getApplyUser(), "GPS物流传递",
            data.getApplyUser());
    }

    @Override
    public void approveNoGpsApply(String code, String remark) {
        GpsApply data = gpsApplyBO.getGpsApply(code);
        if (!EGpsApplyStatus.TO_APPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "GPS申领单不在待审核状态");
        }
        gpsApplyBO.approveGpsApply(code, EGpsApplyStatus.APPROVE_NO, remark);
    }

    @Override
    public Paginable<GpsApply> queryGpsApplyPage(int start, int limit,
            GpsApply condition) {
        Paginable<GpsApply> page = gpsApplyBO.getPaginable(start, limit,
            condition);
        List<GpsApply> gpsApplyList = page.getList();
        for (GpsApply gpsApply : gpsApplyList) {
            initGpsApply(gpsApply);
        }
        return page;
    }

    private void initGpsApply(GpsApply gpsApply) {
        SYSUser sysUser = sysUserBO.getUser(gpsApply.getApplyUser());
        gpsApply.setApplyUserName(sysUser.getRealName());
        Department department = departmentBO
            .getDepartment(gpsApply.getCompanyCode());
        if (department != null) {
            gpsApply.setCompanyName(department.getName());
        }
        if (StringUtils.isNotBlank(sysUser.getTeamCode())) {
            gpsApply.setTeamName(
                bizTeamBO.getBizTeam(sysUser.getTeamCode()).getName());
        }
        if (StringUtils.isNotBlank(sysUser.getRoleCode())) {
            SYSRole sysRole = sysRoleBO.getSYSRole(sysUser.getRoleCode());
            gpsApply.setRoleName(sysRole.getName());
        }

        // 审核时的gps列表
        Gps gps = new Gps();
        gps.setApplyCode(gpsApply.getCode());
        List<Gps> queryGpsList = gpsBO.queryGpsList(gps);
        gpsApply.setGpsList(queryGpsList);
    }

    @Override
    public List<GpsApply> queryGpsApplyList(GpsApply condition) {
        List<GpsApply> gpsApplyList = gpsApplyBO.queryGpsApplyList(condition);
        for (GpsApply gpsApply : gpsApplyList) {
            initGpsApply(gpsApply);
        }
        return gpsApplyList;
    }

    @Override
    public GpsApply getGpsApply(String code) {
        GpsApply gpsApply = gpsApplyBO.getGpsApply(code);
        initGpsApply(gpsApply);
        return gpsApply;
    }
}
