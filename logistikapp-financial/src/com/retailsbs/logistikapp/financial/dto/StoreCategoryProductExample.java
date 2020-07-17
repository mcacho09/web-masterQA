package com.retailsbs.logistikapp.financial.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreCategoryProductExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    public StoreCategoryProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
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
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table lgk_store_category_product
     *
     * @abatorgenerated Fri Mar 24 16:07:35 CST 2017
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

        public Criteria andId_store_category_productIsNull() {
            addCriterion("id_store_category_product is null");
            return this;
        }

        public Criteria andId_store_category_productIsNotNull() {
            addCriterion("id_store_category_product is not null");
            return this;
        }

        public Criteria andId_store_category_productEqualTo(Long value) {
            addCriterion("id_store_category_product =", value, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productNotEqualTo(Long value) {
            addCriterion("id_store_category_product <>", value, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productGreaterThan(Long value) {
            addCriterion("id_store_category_product >", value, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productGreaterThanOrEqualTo(Long value) {
            addCriterion("id_store_category_product >=", value, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productLessThan(Long value) {
            addCriterion("id_store_category_product <", value, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productLessThanOrEqualTo(Long value) {
            addCriterion("id_store_category_product <=", value, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productIn(List<Long> values) {
            addCriterion("id_store_category_product in", values, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productNotIn(List<Long> values) {
            addCriterion("id_store_category_product not in", values, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productBetween(Long value1, Long value2) {
            addCriterion("id_store_category_product between", value1, value2, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_category_productNotBetween(Long value1, Long value2) {
            addCriterion("id_store_category_product not between", value1, value2, "id_store_category_product");
            return this;
        }

        public Criteria andId_store_categoryIsNull() {
            addCriterion("id_store_category is null");
            return this;
        }

        public Criteria andId_store_categoryIsNotNull() {
            addCriterion("id_store_category is not null");
            return this;
        }

        public Criteria andId_store_categoryEqualTo(Long value) {
            addCriterion("id_store_category =", value, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryNotEqualTo(Long value) {
            addCriterion("id_store_category <>", value, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryGreaterThan(Long value) {
            addCriterion("id_store_category >", value, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryGreaterThanOrEqualTo(Long value) {
            addCriterion("id_store_category >=", value, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryLessThan(Long value) {
            addCriterion("id_store_category <", value, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryLessThanOrEqualTo(Long value) {
            addCriterion("id_store_category <=", value, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryIn(List<Long> values) {
            addCriterion("id_store_category in", values, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryNotIn(List<Long> values) {
            addCriterion("id_store_category not in", values, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryBetween(Long value1, Long value2) {
            addCriterion("id_store_category between", value1, value2, "id_store_category");
            return this;
        }

        public Criteria andId_store_categoryNotBetween(Long value1, Long value2) {
            addCriterion("id_store_category not between", value1, value2, "id_store_category");
            return this;
        }

        public Criteria andId_productIsNull() {
            addCriterion("id_product is null");
            return this;
        }

        public Criteria andId_productIsNotNull() {
            addCriterion("id_product is not null");
            return this;
        }

        public Criteria andId_productEqualTo(Long value) {
            addCriterion("id_product =", value, "id_product");
            return this;
        }

        public Criteria andId_productNotEqualTo(Long value) {
            addCriterion("id_product <>", value, "id_product");
            return this;
        }

        public Criteria andId_productGreaterThan(Long value) {
            addCriterion("id_product >", value, "id_product");
            return this;
        }

        public Criteria andId_productGreaterThanOrEqualTo(Long value) {
            addCriterion("id_product >=", value, "id_product");
            return this;
        }

        public Criteria andId_productLessThan(Long value) {
            addCriterion("id_product <", value, "id_product");
            return this;
        }

        public Criteria andId_productLessThanOrEqualTo(Long value) {
            addCriterion("id_product <=", value, "id_product");
            return this;
        }

        public Criteria andId_productIn(List<Long> values) {
            addCriterion("id_product in", values, "id_product");
            return this;
        }

        public Criteria andId_productNotIn(List<Long> values) {
            addCriterion("id_product not in", values, "id_product");
            return this;
        }

        public Criteria andId_productBetween(Long value1, Long value2) {
            addCriterion("id_product between", value1, value2, "id_product");
            return this;
        }

        public Criteria andId_productNotBetween(Long value1, Long value2) {
            addCriterion("id_product not between", value1, value2, "id_product");
            return this;
        }

        public Criteria andPrice_saleIsNull() {
            addCriterion("price_sale is null");
            return this;
        }

        public Criteria andPrice_saleIsNotNull() {
            addCriterion("price_sale is not null");
            return this;
        }

        public Criteria andPrice_saleEqualTo(Double value) {
            addCriterion("price_sale =", value, "price_sale");
            return this;
        }

        public Criteria andPrice_saleNotEqualTo(Double value) {
            addCriterion("price_sale <>", value, "price_sale");
            return this;
        }

        public Criteria andPrice_saleGreaterThan(Double value) {
            addCriterion("price_sale >", value, "price_sale");
            return this;
        }

        public Criteria andPrice_saleGreaterThanOrEqualTo(Double value) {
            addCriterion("price_sale >=", value, "price_sale");
            return this;
        }

        public Criteria andPrice_saleLessThan(Double value) {
            addCriterion("price_sale <", value, "price_sale");
            return this;
        }

        public Criteria andPrice_saleLessThanOrEqualTo(Double value) {
            addCriterion("price_sale <=", value, "price_sale");
            return this;
        }

        public Criteria andPrice_saleIn(List<Double> values) {
            addCriterion("price_sale in", values, "price_sale");
            return this;
        }

        public Criteria andPrice_saleNotIn(List<Double> values) {
            addCriterion("price_sale not in", values, "price_sale");
            return this;
        }

        public Criteria andPrice_saleBetween(Double value1, Double value2) {
            addCriterion("price_sale between", value1, value2, "price_sale");
            return this;
        }

        public Criteria andPrice_saleNotBetween(Double value1, Double value2) {
            addCriterion("price_sale not between", value1, value2, "price_sale");
            return this;
        }
    }
}