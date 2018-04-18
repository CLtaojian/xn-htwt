/**
 * @Title ECurrency.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午4:41:06 
 * @version V1.0   
 */
package com.cdkj.loan.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.loan.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:51:38 
 * @history:
 */
public enum ECoin {
    ETH("ETH", "以太币"), BTC("BTC", "比特币");

    public static Map<String, ECoin> getCurrencyMap() {
        Map<String, ECoin> map = new HashMap<String, ECoin>();
        for (ECoin currency : ECoin.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static ECoin getCurrency(String code) {
        Map<String, ECoin> map = getCurrencyMap();
        ECoin result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的coin不存在");
        }
        return result;
    }

    ECoin(String code, String value) {
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
