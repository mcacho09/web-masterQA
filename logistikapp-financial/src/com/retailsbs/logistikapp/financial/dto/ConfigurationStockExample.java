package com.retailsbs.logistikapp.financial.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationStockExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public ConfigurationStockExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table lgk_configuration_stock
     *
     * @abatorgenerated Thu Aug 31 11:31:38 CDT 2017
     */
    public static class Criteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andId_configuration_stockIsNull() {
            addCriterion("id_configuration_stock is null");
            return this;
        }

        public Criteria andId_configuration_stockIsNotNull() {
            addCriterion("id_configuration_stock is not null");
            return this;
        }

        public Criteria andId_configuration_stockEqualTo(Long value) {
            addCriterion("id_configuration_stock =", value, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockNotEqualTo(Long value) {
            addCriterion("id_configuration_stock <>", value, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockGreaterThan(Long value) {
            addCriterion("id_configuration_stock >", value, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockGreaterThanOrEqualTo(Long value) {
            addCriterion("id_configuration_stock >=", value, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockLessThan(Long value) {
            addCriterion("id_configuration_stock <", value, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockLessThanOrEqualTo(Long value) {
            addCriterion("id_configuration_stock <=", value, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockIn(List<Long> values) {
            addCriterion("id_configuration_stock in", values, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockNotIn(List<Long> values) {
            addCriterion("id_configuration_stock not in", values, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockBetween(Long value1, Long value2) {
            addCriterion("id_configuration_stock between", value1, value2, "id_configuration_stock");
            return this;
        }

        public Criteria andId_configuration_stockNotBetween(Long value1, Long value2) {
            addCriterion("id_configuration_stock not between", value1, value2, "id_configuration_stock");
            return this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("active is null");
            return this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("active is not null");
            return this;
        }

        public Criteria andActiveEqualTo(String value) {
            addCriterion("active =", value, "active");
            return this;
        }

        public Criteria andActiveNotEqualTo(String value) {
            addCriterion("active <>", value, "active");
            return this;
        }

        public Criteria andActiveGreaterThan(String value) {
            addCriterion("active >", value, "active");
            return this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(String value) {
            addCriterion("active >=", value, "active");
            return this;
        }

        public Criteria andActiveLessThan(String value) {
            addCriterion("active <", value, "active");
            return this;
        }

        public Criteria andActiveLessThanOrEqualTo(String value) {
            addCriterion("active <=", value, "active");
            return this;
        }

        public Criteria andActiveLike(String value) {
            addCriterion("active like", value, "active");
            return this;
        }

        public Criteria andActiveNotLike(String value) {
            addCriterion("active not like", value, "active");
            return this;
        }

        public Criteria andActiveIn(List<String> values) {
            addCriterion("active in", values, "active");
            return this;
        }

        public Criteria andActiveNotIn(List<String> values) {
            addCriterion("active not in", values, "active");
            return this;
        }

        public Criteria andActiveBetween(String value1, String value2) {
            addCriterion("active between", value1, value2, "active");
            return this;
        }

        public Criteria andActiveNotBetween(String value1, String value2) {
            addCriterion("active not between", value1, value2, "active");
            return this;
        }

        public Criteria andId_supplierIsNull() {
            addCriterion("id_supplier is null");
            return this;
        }

        public Criteria andId_supplierIsNotNull() {
            addCriterion("id_supplier is not null");
            return this;
        }

        public Criteria andId_supplierEqualTo(Long value) {
            addCriterion("id_supplier =", value, "id_supplier");
            return this;
        }

        public Criteria andId_supplierNotEqualTo(Long value) {
            addCriterion("id_supplier <>", value, "id_supplier");
            return this;
        }

        public Criteria andId_supplierGreaterThan(Long value) {
            addCriterion("id_supplier >", value, "id_supplier");
            return this;
        }

        public Criteria andId_supplierGreaterThanOrEqualTo(Long value) {
            addCriterion("id_supplier >=", value, "id_supplier");
            return this;
        }

        public Criteria andId_supplierLessThan(Long value) {
            addCriterion("id_supplier <", value, "id_supplier");
            return this;
        }

        public Criteria andId_supplierLessThanOrEqualTo(Long value) {
            addCriterion("id_supplier <=", value, "id_supplier");
            return this;
        }

        public Criteria andId_supplierIn(List<Long> values) {
            addCriterion("id_supplier in", values, "id_supplier");
            return this;
        }

        public Criteria andId_supplierNotIn(List<Long> values) {
            addCriterion("id_supplier not in", values, "id_supplier");
            return this;
        }

        public Criteria andId_supplierBetween(Long value1, Long value2) {
            addCriterion("id_supplier between", value1, value2, "id_supplier");
            return this;
        }

        public Criteria andId_supplierNotBetween(Long value1, Long value2) {
            addCriterion("id_supplier not between", value1, value2, "id_supplier");
            return this;
        }
    }
}