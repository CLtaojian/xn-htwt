package com.cdkj.loan.dto.req;

public class XN632215Req extends APageReq {

    private static final long serialVersionUID = 1436082124795324054L;

    // 序号
    private String number;

    // 名称
    private String name;

    // 更新人
    private String updater;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
