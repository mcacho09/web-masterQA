package com.retailsbs.logistikapp.logistic.dao;

import com.retailsbs.logistikapp.logistic.domain.RouteStore;
import com.retailsbs.logistikapp.logistic.dto.RouteStoreExample;
import java.util.List;

public interface RouteStoreDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    Long insert(RouteStore record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    int updateByPrimaryKey(RouteStore record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    int updateByPrimaryKeySelective(RouteStore record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    List<RouteStore> selectByExample(RouteStoreExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    RouteStore selectByPrimaryKey(Long id_route_store);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    int deleteByExample(RouteStoreExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    int deleteByPrimaryKey(Long id_route_store);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_route_store
     *
     * @abatorgenerated Thu Sep 03 10:12:15 CDT 2015
     */
    int countByExample(RouteStoreExample example);
}