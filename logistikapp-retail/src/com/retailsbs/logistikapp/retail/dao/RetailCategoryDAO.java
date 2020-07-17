package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryExample;

public interface RetailCategoryDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_category
     *
     * @abatorgenerated Thu Jan 15 12:56:46 CST 2015
     */
    Long insert(RetailCategory record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_category
     *
     * @abatorgenerated Thu Jan 15 12:56:46 CST 2015
     */
    int updateByPrimaryKey(RetailCategory record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_category
     *
     * @abatorgenerated Thu Jan 15 12:56:46 CST 2015
     */
    int updateByPrimaryKeySelective(RetailCategory record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_category
     *
     * @abatorgenerated Thu Jan 15 12:56:46 CST 2015
     */
    List<RetailCategory> selectByExample(RetailCategoryExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_category
     *
     * @abatorgenerated Thu Jan 15 12:56:46 CST 2015
     */
    RetailCategory selectByPrimaryKey(Long id_retail_category);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_category
     *
     * @abatorgenerated Thu Jan 15 12:56:46 CST 2015
     */
    int deleteByPrimaryKey(Long id_retail_category);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_category
     *
     * @abatorgenerated Thu Jan 15 12:56:46 CST 2015
     */
    int countByExample(RetailCategoryExample example);
}