package com.cdkj.loan.domain;

import com.cdkj.loan.dao.base.ABaseDO;

/**
* GPS安装
* @author: CYL
* @since: 2018-05-30 17:38:43
* @history:
*/
public class BudgetOrderGps extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // gps设备号
    private String gpsDevNo;

    // gps类型
    private String gpsType;

    // 安装位置
    private String azLocation;

    // 安装时间
    private String azDatetime;

    // 安装人员
    private String azUser;

    // 设备图片
    private String devPhotos;

    // 安装图片
    private String azPhotos;

    // 备注
    private String remark;

    // 预算单编号
    private String budgetOrder;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDevPhotos() {
        return devPhotos;
    }

    public void setDevPhotos(String devPhotos) {
        this.devPhotos = devPhotos;
    }

    public String getAzPhotos() {
        return azPhotos;
    }

    public void setAzPhotos(String azPhotos) {
        this.azPhotos = azPhotos;
    }

    public void setGpsDevNo(String gpsDevNo) {
        this.gpsDevNo = gpsDevNo;
    }

    public String getGpsDevNo() {
        return gpsDevNo;
    }

    public void setGpsType(String gpsType) {
        this.gpsType = gpsType;
    }

    public String getGpsType() {
        return gpsType;
    }

    public void setAzLocation(String azLocation) {
        this.azLocation = azLocation;
    }

    public String getAzLocation() {
        return azLocation;
    }

    public void setAzDatetime(String azDatetime) {
        this.azDatetime = azDatetime;
    }

    public String getAzDatetime() {
        return azDatetime;
    }

    public void setAzUser(String azUser) {
        this.azUser = azUser;
    }

    public String getAzUser() {
        return azUser;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setBudgetOrder(String budgetOrder) {
        this.budgetOrder = budgetOrder;
    }

    public String getBudgetOrder() {
        return budgetOrder;
    }

}
