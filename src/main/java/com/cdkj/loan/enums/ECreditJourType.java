package com.cdkj.loan.enums;

/**
 * 征信人员表 与借款人关系
 * @author: jiafr 
 * @since: 2018年5月30日 下午1:26:59 
 * @history:
 */
public enum ECreditJourType {

    SELF("1", "贷款人本人"), HUSBAND("2", "丈夫"), WIFE("3", "妻子"), FATHER("4", "父亲"), MOTHER(
            "5", "母亲"), FATHERINLAW("6", "岳父"), MOTHERINLAW("7", "岳母"), FRIEND(
            "8", "朋友");

    ECreditJourType(String code, String value) {
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
