package com.cdkj.loan.common;

import org.apache.commons.lang3.StringUtils;

public class ProvinceUtil {

    public static String getProvince(String address) {
        String result = "";
        if (StringUtils.isNotBlank(address)) {
            String[] provinceArr = { "安徽省", "北京市", "福建省", "甘肃省", "广东省",
                    "广西壮族自治区", "贵州省", "海南省", "河北省", "河南省", "黑龙江省", "湖北省",
                    "湖南省", "吉林省", "江苏省", "江西省", "辽宁省", "内蒙古自治区", "宁夏回族自治区",
                    "青海省", "山东省", "山西省", "陕西省", "上海市", "四川省", "天津市", "西藏自治区",
                    "新疆维吾尔自治区", "云南省", "浙江省", "重庆市", "香港", "澳门", "台湾" };

            for (String province : provinceArr) {
                if (address.startsWith(province)) {
                    result = province;
                    break;
                }

            }
        }
        return result;
    }
}
