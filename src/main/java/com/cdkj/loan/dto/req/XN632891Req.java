package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 请假管理
 * @author: silver 
 * @since: 2018年6月5日 下午8:34:17 
 * @history:
 */
public class XN632891Req {
    // 编号
    @NotBlank
    private String code;

    // 更新人
    @NotBlank
    private String updater;

    // 审核结果(1 通过 2 不通过)
    @NotBlank
    private String result;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
