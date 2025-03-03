package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.Brand;

public interface IBrandBO extends IPaginableBO<Brand> {

    public String saveBrand(Brand data);

    public Brand getBrand(String code);

    public int editBrand(Brand data);

    public void upBrand(Brand data);

    public void downBrand(Brand data);

    public List<Brand> queryBrand(Brand condition);
}
