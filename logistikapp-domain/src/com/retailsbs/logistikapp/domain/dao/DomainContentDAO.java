package com.retailsbs.logistikapp.domain.dao;

import java.util.List;

import com.retailsbs.logistikapp.domain.domain.DomainContent;
import com.retailsbs.logistikapp.domain.dto.DomainContentExample;

public interface DomainContentDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_domain_content
     *
     * @abatorgenerated Mon Nov 24 09:51:26 CST 2014
     */
    Long insert(DomainContent record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_domain_content
     *
     * @abatorgenerated Mon Nov 24 09:51:26 CST 2014
     */
    int updateByPrimaryKey(DomainContent record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_domain_content
     *
     * @abatorgenerated Mon Nov 24 09:51:26 CST 2014
     */
    int updateByPrimaryKeySelective(DomainContent record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_domain_content
     *
     * @abatorgenerated Mon Nov 24 09:51:26 CST 2014
     */
    List<DomainContent> selectByExample(DomainContentExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_domain_content
     *
     * @abatorgenerated Mon Nov 24 09:51:26 CST 2014
     */
    DomainContent selectByPrimaryKey(Long id_domain_content);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_domain_content
     *
     * @abatorgenerated Mon Nov 24 09:51:26 CST 2014
     */
    int deleteByPrimaryKey(Long id_domain_content);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_domain_content
     *
     * @abatorgenerated Mon Nov 24 09:51:26 CST 2014
     */
    int countByExample(DomainContentExample example);
}