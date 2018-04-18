/**
 * @Title XNlh5010.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:00:02 
 * @version V1.0   
 */
package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.IEthNodeAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/** 
 * 获取节点监控信息
 * @author: myb858 
 * @since: 2017年3月26日 上午9:08:19 
 * @history:
 */
public class XN625800 extends AProcessor {
    private IEthNodeAO ethNodeAO = SpringContextHolder
        .getBean(IEthNodeAO.class);

    /** 
     * @see com.cdkj.loan.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return ethNodeAO.getNodeMonitorInfo();
    }

    /** 
     * @see com.cdkj.loan.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
    }
}
