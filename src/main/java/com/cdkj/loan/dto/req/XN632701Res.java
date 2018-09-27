package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN632701Res {

    // gps编号
    @NotBlank
    private String gpsDevNo;

    // gps类型
    @NotBlank
    private String gpsType;

    public String getGpsDevNo() {
        return gpsDevNo;
    }

    public void setGpsDevNo(String gpsDevNo) {
        this.gpsDevNo = gpsDevNo;
    }

    public String getGpsType() {
        return gpsType;
    }

    public void setGpsType(String gpsType) {
        this.gpsType = gpsType;
    }
}
