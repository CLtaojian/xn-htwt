/**
 * @Title ProductImpl.java 
 * @Package com.xnjr.mall.dao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年5月16日 下午8:58:50 
 * @version V1.0   
 */
package com.cdkj.loan.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.loan.dao.IProductDAO;
import com.cdkj.loan.dao.base.support.AMybatisTemplate;
import com.cdkj.loan.domain.Product;

/** 
 * @author: haiqingzheng 
 * @since: 2016年5月16日 下午8:58:51 
 * @history:
 */
@Repository("productDAOImpl")
public class ProductDAOImpl extends AMybatisTemplate implements IProductDAO {

    /** 
     * @see com.xnjr.mall.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Product data) {
        return super.insert(NAMESPACE.concat("insert_product"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Product data) {
        return super.delete(NAMESPACE.concat("delete_product"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Product select(Product condition) {
        return super.select(NAMESPACE.concat("select_product"), condition,
            Product.class);
    }

    /** 
     * @see com.xnjr.mall.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(Product condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_product_count"),
            condition);
    }

    /** 
     * @see com.xnjr.mall.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Product> selectList(Product condition) {
        return super.selectList(NAMESPACE.concat("select_product"), condition,
            Product.class);
    }

    /** 
     * @see com.xnjr.mall.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Product> selectList(Product condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_product"), start,
            count, condition, Product.class);
    }

    /** 
     * @see com.xnjr.mall.dao.IProductDAO#updateProduct(com.xnjr.mall.domain.Product)
     */
    @Override
    public int updateProduct(Product product) {
        return super.update(NAMESPACE.concat("update_product"), product);
    }

    @Override
    public int updateStatus(Product product) {
        return super.update(NAMESPACE.concat("update_productStatus"), product);
    }

    @Override
    public int updatePutOnProduct(Product product) {
        return super.update(NAMESPACE.concat("update_putOn"), product);
    }

    @Override
    public int updateBoughtCount(Product data) {
        return super.update(NAMESPACE.concat("update_boughtCount"), data);
    }

}
