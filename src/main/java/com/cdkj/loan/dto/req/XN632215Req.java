package com.cdkj.loan.dto.req;

public class XN632215Req extends APageReq {

    private static final long serialVersionUID = 1436082124795324054L;

    // 序号
    private String id;

    // 名称
    private String kname;

    private String category;

    private String attachType;

    // 更新人
    private String updater;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

}
