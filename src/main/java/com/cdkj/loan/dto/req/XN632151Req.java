package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 资料传递收件并审核通过
 * @author: silver 
 * @since: 2018年5月29日 下午11:02:18 
 * @history:
 */
public class XN632151Req {
    // 编号
    @NotBlank
    private String code;

    // 备注
    private String remark;

    // 操作人
    @NotBlank
    private String operater;

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
