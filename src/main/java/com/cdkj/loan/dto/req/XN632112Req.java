package com.cdkj.loan.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改征信
 * @author: jiafr 
 * @since: 2018年5月29日 下午7:36:06 
 * @history:
 */
public class XN632112Req {

    // 征信单编号
    @NotBlank
    private String bizCode;

    // 贷款银行
    private String loanBankCode;

    // 贷款金额
    private String loanAmount;

    // 业务种类
    private String bizType;

    // 二手车评估报告
    private String secondCarReport;

    private String xszFront;

    private String xszReverse;

    private List<XN632112ReqCreditUser> creditUserList;

    // 操作按钮
    @NotBlank
    private String buttonCode;

    // 操作人
    @NotBlank
    private String operator;

    public String getXszFront() {
        return xszFront;
    }

    public void setXszFront(String xszFront) {
        this.xszFront = xszFront;
    }

    public String getXszReverse() {
        return xszReverse;
    }

    public void setXszReverse(String xszReverse) {
        this.xszReverse = xszReverse;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getLoanBankCode() {
        return loanBankCode;
    }

    public void setLoanBankCode(String loanBankCode) {
        this.loanBankCode = loanBankCode;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSecondCarReport() {
        return secondCarReport;
    }

    public void setSecondCarReport(String secondCarReport) {
        this.secondCarReport = secondCarReport;
    }

    public List<XN632112ReqCreditUser> getCreditUserList() {
        return creditUserList;
    }

    public void setCreditUserList(List<XN632112ReqCreditUser> creditUserList) {
        this.creditUserList = creditUserList;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

}
