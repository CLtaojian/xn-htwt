package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630422Req {

    @NotBlank(message = "编号不能为空")
    private String code; // 编号

    @NotBlank(message = "名称不能为空")
    private String name; // 名称

    @NotBlank(message = "原价不能为空")
    private String originalPrice; // 原价

    @NotBlank(message = "参考价不能为空")
    private String salePrice; // 参考价

    @NotBlank(message = "首付金额不能为空")
    private String sfAmount; // 首付金额

    @NotBlank(message = "广告语不能为空")
    private String slogan; // 广告语

    @NotBlank(message = "广告图不能为空")
    private String advPic; // 广告图

    @NotBlank(message = "缩略图不能为空")
    private String pic; // 缩略图

    @NotBlank(message = "图文描述不能为空")
    private String description; // 图文描述

    @NotBlank(message = "最新修改人不能为空")
    private String updater; // 最新修改人

    private String remark;// 备注

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSfAmount() {
        return sfAmount;
    }

    public void setSfAmount(String sfAmount) {
        this.sfAmount = sfAmount;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
