package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.ConfigurationStockProductDAO;
import com.retailsbs.logistikapp.financial.domain.ConfigurationStockProduct;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductExample;

public class ConfigurationStockProductDAOImpl extends SqlMapClientDaoSupport implements ConfigurationStockProductDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public ConfigurationStockProductDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public Long insert(ConfigurationStockProduct record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_configuration_stock_product.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public int updateByPrimaryKey(ConfigurationStockProduct record) {
        int rows = getSqlMapClientTemplate().update("lgk_configuration_stock_product.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public int updateByPrimaryKeySelective(ConfigurationStockProduct record) {
        int rows = getSqlMapClientTemplate().update("lgk_configuration_stock_product.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    @SuppressWarnings("unchecked")
    public List<ConfigurationStockProduct> selectByExample(ConfigurationStockProductExample example) {
        List<ConfigurationStockProduct> list = (List<ConfigurationStockProduct>) getSqlMapClientTemplate().queryForList("lgk_configuration_stock_product.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public ConfigurationStockProduct selectByPrimaryKey(Long id_configuration_stock_product) {
        ConfigurationStockProduct key = new ConfigurationStockProduct();
        key.setId_configuration_stock_product(id_configuration_stock_product);
        ConfigurationStockProduct record = (ConfigurationStockProduct) getSqlMapClientTemplate().queryForObject("lgk_configuration_stock_product.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public int deleteByPrimaryKey(Long id_configuration_stock_product) {
        ConfigurationStockProduct key = new ConfigurationStockProduct();
        key.setId_configuration_stock_product(id_configuration_stock_product);
        int rows = getSqlMapClientTemplate().delete("lgk_configuration_stock_product.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock_product
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public int countByExample(ConfigurationStockProductExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_configuration_stock_product.abatorgenerated_countByExample", example);
        return count;
    }
}