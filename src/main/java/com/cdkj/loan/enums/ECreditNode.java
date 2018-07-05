package com.cdkj.loan.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 征信节点
 * @author: jiafr 
 * @since: 2018年5月25日 下午3:01:56 
 * @history:
 */
public enum ECreditNode {

    FILLIN_CREDIT("001_01", "填写征信单"), INPUT_CREDIT_RESULT("001_02", "录入征信结果"), AUDIT(
            "001_03", "风控专员审核"), AUDIT_NO_PASS("001_04", "风控专员审核不通过"), BACK(
            "001_05", "征信退回"), ACHIEVE("001_09", "征信单完成"), CANCEL("001_10",
            "征信撤回");

    public static Map<String, ECreditNode> getMap() {
        Map<String, ECreditNode> map = new HashMap<String, ECreditNode>();
        for (ECreditNode node : ECreditNode.values()) {
            map.put(node.getCode(), node);
        }
        return map;
    }

    private ECreditNode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
