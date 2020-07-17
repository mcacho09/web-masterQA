package com.retailsbs.logistikapp.retail.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetailDeptExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public RetailDeptExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
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
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
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

        public Criteria andId_retail_deptIsNull() {
            addCriterion("id_retail_dept is null");
            return this;
        }

        public Criteria andId_retail_deptIsNotNull() {
            addCriterion("id_retail_dept is not null");
            return this;
        }

        public Criteria andId_retail_deptEqualTo(Long value) {
            addCriterion("id_retail_dept =", value, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptNotEqualTo(Long value) {
            addCriterion("id_retail_dept <>", value, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptGreaterThan(Long value) {
            addCriterion("id_retail_dept >", value, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptGreaterThanOrEqualTo(Long value) {
            addCriterion("id_retail_dept >=", value, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptLessThan(Long value) {
            addCriterion("id_retail_dept <", value, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptLessThanOrEqualTo(Long value) {
            addCriterion("id_retail_dept <=", value, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptIn(List<Long> values) {
            addCriterion("id_retail_dept in", values, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptNotIn(List<Long> values) {
            addCriterion("id_retail_dept not in", values, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptBetween(Long value1, Long value2) {
            addCriterion("id_retail_dept between", value1, value2, "id_retail_dept");
            return this;
        }

        public Criteria andId_retail_deptNotBetween(Long value1, Long value2) {
            addCriterion("id_retail_dept not between", value1, value2, "id_retail_dept");
            return this;
        }

        public Criteria andId_retailIsNull() {
            addCriterion("id_retail is null");
            return this;
        }

        public Criteria andId_retailIsNotNull() {
            addCriterion("id_retail is not null");
            return this;
        }

        public Criteria andId_retailEqualTo(Long value) {
            addCriterion("id_retail =", value, "id_retail");
            return this;
        }

        public Criteria andId_retailNotEqualTo(Long value) {
            addCriterion("id_retail <>", value, "id_retail");
            return this;
        }

        public Criteria andId_retailGreaterThan(Long value) {
            addCriterion("id_retail >", value, "id_retail");
            return this;
        }

        public Criteria andId_retailGreaterThanOrEqualTo(Long value) {
            addCriterion("id_retail >=", value, "id_retail");
            return this;
        }

        public Criteria andId_retailLessThan(Long value) {
            addCriterion("id_retail <", value, "id_retail");
            return this;
        }

        public Criteria andId_retailLessThanOrEqualTo(Long value) {
            addCriterion("id_retail <=", value, "id_retail");
            return this;
        }

        public Criteria andId_retailIn(List<Long> values) {
            addCriterion("id_retail in", values, "id_retail");
            return this;
        }

        public Criteria andId_retailNotIn(List<Long> values) {
            addCriterion("id_retail not in", values, "id_retail");
            return this;
        }

        public Criteria andId_retailBetween(Long value1, Long value2) {
            addCriterion("id_retail between", value1, value2, "id_retail");
            return this;
        }

        public Criteria andId_retailNotBetween(Long value1, Long value2) {
            addCriterion("id_retail not between", value1, value2, "id_retail");
            return this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return this;
        }

        public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return this;
        }

        public Criteria andModifiedEqualTo(Date value) {
            addCriterion("modified =", value, "modified");
            return this;
        }

        public Criteria andModifiedNotEqualTo(Date value) {
            addCriterion("modified <>", value, "modified");
            return this;
        }

        public Criteria andModifiedGreaterThan(Date value) {
            addCriterion("modified >", value, "modified");
            return this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("modified >=", value, "modified");
            return this;
        }

        public Criteria andModifiedLessThan(Date value) {
            addCriterion("modified <", value, "modified");
            return this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Date value) {
            addCriterion("modified <=", value, "modified");
            return this;
        }

        public Criteria andModifiedIn(List<Date> values) {
            addCriterion("modified in", values, "modified");
            return this;
        }

        public Criteria andModifiedNotIn(List<Date> values) {
            addCriterion("modified not in", values, "modified");
            return this;
        }

        public Criteria andModifiedBetween(Date value1, Date value2) {
            addCriterion("modified between", value1, value2, "modified");
            return this;
        }

        public Criteria andModifiedNotBetween(Date value1, Date value2) {
            addCriterion("modified not between", value1, value2, "modified");
            return this;
        }

        public Criteria andLoginIsNull() {
            addCriterion("login is null");
            return this;
        }

        public Criteria andLoginIsNotNull() {
            addCriterion("login is not null");
            return this;
        }

        public Criteria andLoginEqualTo(String value) {
            addCriterion("login =", value, "login");
            return this;
        }

        public Criteria andLoginNotEqualTo(String value) {
            addCriterion("login <>", value, "login");
            return this;
        }

        public Criteria andLoginGreaterThan(String value) {
            addCriterion("login >", value, "login");
            return this;
        }

        public Criteria andLoginGreaterThanOrEqualTo(String value) {
            addCriterion("login >=", value, "login");
            return this;
        }

        public Criteria andLoginLessThan(String value) {
            addCriterion("login <", value, "login");
            return this;
        }

        public Criteria andLoginLessThanOrEqualTo(String value) {
            addCriterion("login <=", value, "login");
            return this;
        }

        public Criteria andLoginLike(String value) {
            addCriterion("login like", value, "login");
            return this;
        }

        public Criteria andLoginNotLike(String value) {
            addCriterion("login not like", value, "login");
            return this;
        }

        public Criteria andLoginIn(List<String> values) {
            addCriterion("login in", values, "login");
            return this;
        }

        public Criteria andLoginNotIn(List<String> values) {
            addCriterion("login not in", values, "login");
            return this;
        }

        public Criteria andLoginBetween(String value1, String value2) {
            addCriterion("login between", value1, value2, "login");
            return this;
        }

        public Criteria andLoginNotBetween(String value1, String value2) {
            addCriterion("login not between", value1, value2, "login");
            return this;
        }

        public Criteria andOrderbyIsNull() {
            addCriterion("orderby is null");
            return this;
        }

        public Criteria andOrderbyIsNotNull() {
            addCriterion("orderby is not null");
            return this;
        }

        public Criteria andOrderbyEqualTo(Integer value) {
            addCriterion("orderby =", value, "orderby");
            return this;
        }

        public Criteria andOrderbyNotEqualTo(Integer value) {
            addCriterion("orderby <>", value, "orderby");
            return this;
        }

        public Criteria andOrderbyGreaterThan(Integer value) {
            addCriterion("orderby >", value, "orderby");
            return this;
        }

        public Criteria andOrderbyGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderby >=", value, "orderby");
            return this;
        }

        public Criteria andOrderbyLessThan(Integer value) {
            addCriterion("orderby <", value, "orderby");
            return this;
        }

        public Criteria andOrderbyLessThanOrEqualTo(Integer value) {
            addCriterion("orderby <=", value, "orderby");
            return this;
        }

        public Criteria andOrderbyIn(List<Integer> values) {
            addCriterion("orderby in", values, "orderby");
            return this;
        }

        public Criteria andOrderbyNotIn(List<Integer> values) {
            addCriterion("orderby not in", values, "orderby");
            return this;
        }

        public Criteria andOrderbyBetween(Integer value1, Integer value2) {
            addCriterion("orderby between", value1, value2, "orderby");
            return this;
        }

        public Criteria andOrderbyNotBetween(Integer value1, Integer value2) {
            addCriterion("orderby not between", value1, value2, "orderby");
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

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return this;
        }
    }
}