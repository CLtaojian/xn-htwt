package com.cdkj.loan.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修改公告
 * @author: silver 
 * @since: 2018年6月8日 上午10:18:00 
 * @history:
 */
public class XN632722Req {
    // 编号
    @NotBlank
    private String code;

    // 标题
    @NotBlank
    private String title;

    // 类型
    @NotBlank
    private String type;

    // 紧急状态
    @NotBlank
    private String urgentStatus;

    // 发布部门
    @NotBlank
    private String publishDepartmentCode;

    // 范围
    private String scope;

    // 内容
    @NotBlank
    private String content;

    // 附件
    private String enclosure;

    // 备注
    private String remark;

    // 更新人
    @NotBlank
    private String updater;

    // 范围
    @NotEmpty
    private List<XN632720ReqScope> scopePeopleList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrgentStatus() {
        return urgentStatus;
    }

    public void setUrgentStatus(String urgentStatus) {
        this.urgentStatus = urgentStatus;
    }

    public String getPublishDepartmentCode() {
        return publishDepartmentCode;
    }

    public void setPublishDepartmentCode(String publishDepartmentCode) {
        this.publishDepartmentCode = publishDepartmentCode;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<XN632720ReqScope> getScopePeopleList() {
        return scopePeopleList;
    }

    public void setScopePeopleList(List<XN632720ReqScope> scopePeopleList) {
        this.scopePeopleList = scopePeopleList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
