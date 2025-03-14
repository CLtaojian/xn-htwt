package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.ISeriesAO;
import com.cdkj.loan.bo.ICarBO;
import com.cdkj.loan.bo.ISYSUserBO;
import com.cdkj.loan.bo.ISeriesBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Car;
import com.cdkj.loan.domain.Series;
import com.cdkj.loan.dto.req.XN630410Req;
import com.cdkj.loan.dto.req.XN630412Req;
import com.cdkj.loan.enums.EBrandStatus;
import com.cdkj.loan.exception.BizException;

@Service
public class SeriesAOImpl implements ISeriesAO {

    @Autowired
    private ISeriesBO seriesBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ICarBO carBO;

    @Override
    public String addSeries(XN630410Req req) {
        Series series = new Series();
        series.setBrandCode(req.getBrandCode());
        series.setName(req.getName());
        series.setSlogan(req.getSlogan());
        series.setAdvPic(req.getAdvPic());
        series.setPicNumber(StringValidater.toLong(req.getPicNumber()));
        series.setPrice(Long.valueOf(0));
        series.setHighest(Long.valueOf(0));
        series.setLowest(Long.valueOf(0));
        series.setLevel(req.getLevel());
        series.setIsReferee(req.getIsReferee());
        series.setStatus(EBrandStatus.TO_UP.getCode());
        series.setUpdater(req.getUpdater());
        series.setUpdateDatetime(new Date());
        series.setRemark(req.getRemark());
        return seriesBO.saveSeries(series);
    }

    @Override
    public void editSeries(XN630412Req req) {
        Series series = seriesBO.getSeries(req.getCode());
        if (EBrandStatus.UP.getCode().equals(series.getStatus())) {
            throw new BizException("xn0000", "品牌已上架，请在下架后修改");
        }
        series.setBrandCode(req.getBrandCode());
        series.setName(req.getName());
        series.setSlogan(req.getSlogan());
        series.setAdvPic(req.getAdvPic());
        series.setPicNumber(StringValidater.toLong(req.getPicNumber()));
        series.setLevel(req.getLevel());
        series.setIsReferee(req.getIsReferee());
        series.setUpdater(req.getUpdater());
        series.setUpdateDatetime(new Date());
        series.setRemark(req.getRemark());
        seriesBO.editSeries(series);
    }

    @Override
    public void upSeries(String code, String location, String orderNo,
            String updater, String remark) {
        Series series = seriesBO.getSeries(code);
        series.setLocation(location);
        series.setOrderNo(StringValidater.toInteger(orderNo));
        series.setStatus(EBrandStatus.UP.getCode());
        series.setUpdater(updater);
        series.setUpdateDatetime(new Date());
        series.setRemark(remark);
        seriesBO.upSeries(series);
    }

    @Override
    public void downSeries(String code, String updater, String remark) {
        Series series = seriesBO.getSeries(code);
        series.setStatus(EBrandStatus.DOWN.getCode());
        series.setUpdater(updater);
        series.setUpdateDatetime(new Date());
        series.setRemark(remark);
        seriesBO.downSeries(series);
    }

    @Override
    public Paginable<Series> querySeriesPage(int start, int limit,
            Series condition) {
        Paginable<Series> paginable = seriesBO.getPaginable(start, limit,
            condition);
        for (Series series : paginable.getList()) {
            init(series);
        }
        return paginable;
    }

    @Override
    public Series getSeries(String code) {
        Series series = seriesBO.getSeries(code);
        init(series);
        return series;
    }

    @Override
    public List<Series> querySeriesList(Series condition) {
        List<Series> querySeries = seriesBO.querySeries(condition);
        for (Series series : querySeries) {

            init(series);
        }
        return querySeries;
    }

    private void init(Series series) {
        String realName = sysUserBO.getUser(series.getUpdater()).getRealName();
        series.setUpdaterName(realName);
        Car condition = new Car();
        condition.setSeriesCode(series.getCode());
        condition.setStatus(EBrandStatus.UP.getCode());
        List<Car> cars = carBO.queryCar(condition);
        series.setCars(cars);
        series.setCarNumber(Long.valueOf(cars.size()));
    }
}
