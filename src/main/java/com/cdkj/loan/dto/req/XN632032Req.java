package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改银行信息
 * @author: silver 
 * @since: 2018年5月27日 下午4:46:11 
 * @history:
 */
public class XN632032Req {
    // 编号
    @NotBlank
    private String code;

    // 银行编号
    @NotBlank
    private String bankCode;

    // 银行名称
    @NotBlank
    private String bankName;

    // 支行名称
    @NotBlank
    private String subbranch;

    // 12期
    private String rate12;

    // 18期
    private String rate18;

    // 24期
    private String rate24;

    // 36期
    private String rate36;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRate12() {
        return rate12;
    }

    public void setRate12(String rate12) {
        this.rate12 = rate12;
    }

    public String getRate18() {
        return rate18;
    }

    public void setRate18(String rate18) {
        this.rate18 = rate18;
    }

    public String getRate24() {
        return rate24;
    }

    public void setRate24(String rate24) {
        this.rate24 = rate24;
    }

    public String getRate36() {
        return rate36;
    }

    public void setRate36(String rate36) {
        this.rate36 = rate36;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
