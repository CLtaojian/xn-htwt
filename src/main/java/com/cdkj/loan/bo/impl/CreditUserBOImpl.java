package com.cdkj.loan.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IAttachmentBO;
import com.cdkj.loan.bo.ICreditUserBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.ICreditUserDAO;
import com.cdkj.loan.domain.CreditUser;
import com.cdkj.loan.dto.req.XN632110ReqCreditUser;
import com.cdkj.loan.dto.req.XN632112ReqCreditUser;
import com.cdkj.loan.enums.EAttachName;
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
public class CreditUserBOImpl extends PaginableBOImpl<CreditUser> implements
        ICreditUserBO {

    @Autowired
    private ICreditUserDAO creditUserDAO;

    @Autowired
    private IAttachmentBO attachmentBO;

    @Override
    public void saveCreditUser(XN632110ReqCreditUser child, String creditCode,
            String bizCode) {
        String code = null;
        CreditUser creditUser = new CreditUser();
        code = OrderNoGenerater.generate(EGeneratePrefix.CREDITUSER.getCode());
        creditUser.setCode(code);

        creditUser.setCreditCode(creditCode);
        creditUser.setRelation(child.getRelation());
        creditUser.setUserName(child.getUserName());
        creditUser.setLoanRole(child.getLoanRole());
        creditUser.setMobile(child.getMobile());
        creditUser.setIdNoFront(child.getIdNoFront());
        creditUser.setIdNoReverse(child.getIdNoReverse());
        creditUser.setAuthPdf(child.getAuthPdf());
        creditUser.setInterviewPic(child.getInterviewPic());

        creditUser.setIdNo(child.getIdNo());
        // 主贷人
        if (ELoanRole.APPLY_USER.getCode().equals(child.getLoanRole())) {
            // 身份证正面
            EAttachName attachName = EAttachName.getMap().get(
                EAttachName.mainLoaner_id_front.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoFront());
            // 身份证反面
            attachName = EAttachName.getMap().get(
                EAttachName.mainLoaner_id_reverse.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoReverse());
            // 征信查询授权
            attachName = EAttachName.getMap().get(
                EAttachName.mainLoaner_auth_pdf.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getAuthPdf());
            // 面签照片
            attachName = EAttachName.getMap().get(
                EAttachName.mainloaner_interview_pic.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getInterviewPic());
            // 共还人信息
        } else if (ELoanRole.GHR.getCode().equals(child.getLoanRole())) {
            // 身份证正面
            EAttachName attachName = EAttachName.getMap().get(
                EAttachName.replier_id_front.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoFront());
            // 身份证反面
            attachName = EAttachName.getMap().get(
                EAttachName.replier_id_reverse.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoReverse());
            // 征信查询授权
            attachName = EAttachName.getMap().get(
                EAttachName.replier_auth_pdf.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getAuthPdf());
            // 面签照片
            attachName = EAttachName.getMap().get(
                EAttachName.replier_interview_pic.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getInterviewPic());

            // 担保人
        } else if (ELoanRole.GUARANTOR.getCode().equals(child.getLoanRole())) {
            // 身份证正面
            EAttachName attachName = EAttachName.getMap().get(
                EAttachName.assurance_id_front.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoFront());
            // 身份证反面
            attachName = EAttachName.getMap().get(
                EAttachName.assurance_id_reverse.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoReverse());
            // 征信查询授权
            attachName = EAttachName.getMap().get(
                EAttachName.assurance_auth_pdf.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getAuthPdf());
            // 面签照片
            attachName = EAttachName.getMap().get(
                EAttachName.assurance_interview_pic.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getInterviewPic());
        }
        creditUserDAO.insert(creditUser);

    }

    @Override
    public void saveCreditUser(XN632112ReqCreditUser child, String creditCode,
            String bizCode) {
        String code = null;
        CreditUser creditUser = new CreditUser();
        code = OrderNoGenerater.generate(EGeneratePrefix.CREDITUSER.getCode());
        creditUser.setCode(code);

        creditUser.setCreditCode(creditCode);
        creditUser.setRelation(child.getRelation());
        creditUser.setUserName(child.getUserName());
        creditUser.setLoanRole(child.getLoanRole());
        creditUser.setMobile(child.getMobile());
        creditUser.setIdNoFront(child.getIdNoFront());
        creditUser.setIdNoReverse(child.getIdNoReverse());
        creditUser.setAuthPdf(child.getAuthPdf());
        creditUser.setInterviewPic(child.getInterviewPic());

        creditUser.setIdNo(child.getIdNo());
        // 主贷人
        if (ELoanRole.APPLY_USER.getCode().equals(child.getLoanRole())) {
            // 身份证正面
            EAttachName attachName = EAttachName.getMap().get(
                EAttachName.mainLoaner_id_front.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoFront());
            // 身份证反面
            attachName = EAttachName.getMap().get(
                EAttachName.mainLoaner_id_reverse.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoReverse());
            // 征信查询授权
            attachName = EAttachName.getMap().get(
                EAttachName.mainLoaner_auth_pdf.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getAuthPdf());
            // 面签照片
            attachName = EAttachName.getMap().get(
                EAttachName.mainloaner_interview_pic.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getInterviewPic());
            // 共还人信息
        } else if (ELoanRole.GHR.getCode().equals(child.getLoanRole())) {
            // 身份证正面
            EAttachName attachName = EAttachName.getMap().get(
                EAttachName.replier_id_front.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoFront());
            // 身份证反面
            attachName = EAttachName.getMap().get(
                EAttachName.replier_id_reverse.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoReverse());
            // 征信查询授权
            attachName = EAttachName.getMap().get(
                EAttachName.replier_auth_pdf.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getAuthPdf());
            // 面签照片
            attachName = EAttachName.getMap().get(
                EAttachName.replier_interview_pic.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getInterviewPic());

            // 担保人
        } else if (ELoanRole.GUARANTOR.getCode().equals(child.getLoanRole())) {
            // 身份证正面
            EAttachName attachName = EAttachName.getMap().get(
                EAttachName.assurance_id_front.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoFront());
            // 身份证反面
            attachName = EAttachName.getMap().get(
                EAttachName.assurance_id_reverse.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getIdNoReverse());
            // 征信查询授权
            attachName = EAttachName.getMap().get(
                EAttachName.assurance_auth_pdf.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getAuthPdf());
            // 面签照片
            attachName = EAttachName.getMap().get(
                EAttachName.assurance_interview_pic.getCode());
            attachmentBO.saveAttachment(bizCode, attachName.getCode(),
                attachName.getValue(), child.getInterviewPic());
        }
        creditUserDAO.insert(creditUser);

    }

    @Override
    public void removeCreditUser(String code) {
        CreditUser creditUser = getCreditUser(code);
        creditUserDAO.delete(creditUser);
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
    public void inputBankCreditResult(CreditUser creditUser, String bankReport,
            String dataReport, String result, String note) {
        if (StringUtils.isNotBlank(creditUser.getCode())) {
            creditUser.setBankCreditResultPdf(result);
            creditUser.setBankCreditResultRemark(note);
            creditUser.setBankReport(bankReport);
            creditUser.setDataReport(dataReport);

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
