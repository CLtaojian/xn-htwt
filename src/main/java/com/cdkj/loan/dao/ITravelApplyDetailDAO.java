package com.cdkj.loan.dao;

import com.cdkj.loan.dao.base.IBaseDAO;
import com.cdkj.loan.domain.TravelApplyDetail;

public interface ITravelApplyDetailDAO extends IBaseDAO<TravelApplyDetail> {
    String NAMESPACE = ITravelApplyDetailDAO.class.getName().concat(".");
}
