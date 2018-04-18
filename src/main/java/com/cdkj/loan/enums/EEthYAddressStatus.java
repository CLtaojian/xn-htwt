package com.cdkj.loan.enums;

public enum EEthYAddressStatus {
    NORMAL("0", "正常"), CERTI("1", "已认证"), INVALID("2", "已弃用");

    EEthYAddressStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
