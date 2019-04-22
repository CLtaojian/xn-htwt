package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN630052Req {

    // userId
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // 新手机号
    @NotBlank(message = "新手机号不能为空")
    private String newMobile;

    // 验证码
    @NotBlank(message = "验证码不能为空")
    private String newCaptcha;

    private String oldMobile;

    private String oldCaptcha;

    // 备注（选填）
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    public String getNewCaptcha() {
        return newCaptcha;
    }

    public void setNewCaptcha(String newCaptcha) {
        this.newCaptcha = newCaptcha;
    }

    public String getOldMobile() {
        return oldMobile;
    }

    public void setOldMobile(String oldMobile) {
        this.oldMobile = oldMobile;
    }

    public String getOldCaptcha() {
        return oldCaptcha;
    }

    public void setOldCaptcha(String oldCaptcha) {
        this.oldCaptcha = oldCaptcha;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
