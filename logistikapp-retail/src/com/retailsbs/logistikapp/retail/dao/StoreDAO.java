package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.dto.StoreExample;

public interface StoreDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store
     *
     * @abatorgenerated Mon Mar 28 13:23:45 CST 2016
     */
    Long insert(Store record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store
     *
     * @abatorgenerated Mon Mar 28 13:23:45 CST 2016
     */
    int updateByPrimaryKey(Store record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store
     *
     * @abatorgenerated Mon Mar 28 13:23:45 CST 2016
     */
    int updateByPrimaryKeySelective(Store record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store
     *
     * @abatorgenerated Mon Mar 28 13:23:45 CST 2016
     */
    List<Store> selectByExample(StoreExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store
     *
     * @abatorgenerated Mon Mar 28 13:23:45 CST 2016
     */
    Store selectByPrimaryKey(Long id_store);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store
     *
     * @abatorgenerated Mon Mar 28 13:23:45 CST 2016
     */
    int deleteByPrimaryKey(Long id_store);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store
     *
     * @abatorgenerated Mon Mar 28 13:23:45 CST 2016
     */
    int countByExample(StoreExample example);
}