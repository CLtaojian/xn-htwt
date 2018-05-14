package com.cdkj.loan.dto.req;

/**
 * 分页查询银行卡
 * @author: asus 
 * @since: 2016年12月22日 下午7:28:32 
 * @history:
 */
public class XN802015Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 银行卡号
    private String bankcardNumber;

    // 银行名称
    private String bankName;

    // 用户编号
    private String userId;

    // 用户姓名
    private String realName;

    // 状态(0 不可用 1可用)
    private String status;

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
