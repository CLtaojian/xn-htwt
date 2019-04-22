package com.cdkj.loan.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.cdkj.loan.core.CalculationUtil;

public class AmountUtil {

    public static Double mulAB(double A, double B) {
        BigDecimal a = new BigDecimal(Double.toString(A));
        BigDecimal b = new BigDecimal(Double.toString(B));
        return a.multiply(b).doubleValue();
    }

    public static Long mul(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b).longValue();
    }

    public static BigDecimal mul(BigDecimal amount, double number) {
        BigDecimal a = amount;
        BigDecimal b = new BigDecimal(Double.toString(number));
        return a.multiply(b);
    }

    public static double div(Double amount, Long number) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(number));
        return a.divide(b, 10, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    public static long div(Long amount, double rate) {
        BigDecimal a = new BigDecimal(amount);
        BigDecimal b = new BigDecimal(rate);
        return a.divide(b, 2, RoundingMode.DOWN).longValue();
    }

    public static long divLL(Long amount, Long period) {
        BigDecimal a = new BigDecimal(amount);
        BigDecimal b = new BigDecimal(period);
        return a.divide(b, 0, RoundingMode.DOWN).longValue();
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiUp(Long amount) {
        String amountString = CalculationUtil.diviUp(amount);
        return Long.valueOf(CalculationUtil.multUp(amountString));
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiDown(Long amount) {
        String amountString = CalculationUtil.diviDown(amount);
        return Long.valueOf(CalculationUtil.multDown(amountString));
    }

}
