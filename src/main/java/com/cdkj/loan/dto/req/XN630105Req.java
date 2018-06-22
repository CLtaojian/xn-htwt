package com.cdkj.loan.dto.req;

import java.util.List;

public class XN630105Req extends APageReq {

    private static final long serialVersionUID = 5138736221155343722L;

    private List<String> typeList; // 类型列表

    private String leadUserId;// 负责人编号

    private String parentCode;// 上级部门编号

    private String status;// 状态

    private String keyword;// 关键字

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public String getLeadUserId() {
        return leadUserId;
    }

    public void setLeadUserId(String leadUserId) {
        this.leadUserId = leadUserId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
