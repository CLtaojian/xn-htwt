/**
 * @Title GoogleAuthBOImpl.java 
 * @Package com.cdkj.coin.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月6日 下午4:55:49 
 * @version V1.0   
 */
package com.cdkj.loan.bo.impl;

import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IGoogleAuthBO;
import com.cdkj.loan.core.GoogleAuthenticator;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.EBizErrorCode;

/** 
 * @author: haiqingzheng 
 * @since: 2017年12月6日 下午4:55:49 
 * @history:
 */
@Component
public class GoogleAuthBOImpl implements IGoogleAuthBO {

    /** 
     * @see com.cdkj.coin.ao.IGoogleAuthAO#generateSecretKey()
     */
    @Override
    public String generateSecretKey() {
        return GoogleAuthenticator.generateSecretKey();
    }

    /** 
     * @see com.cdkj.coin.ao.IGoogleAuthAO#checkCode(java.lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public void checkCode(String secret, String googleCaptcha, Long timeMsec) {
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5); // should give 5 * 30 seconds of grace...
        boolean result = ga.check_code(secret,
            StringValidater.toLong(googleCaptcha), timeMsec);
        if (!result) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "谷歌验证码校验失败，请仔细核对！");
        }
    }

}
