package com.retailsbs.logistikapp.user.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGroupExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    public UserGroupExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
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
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table lgk_user_group
     *
     * @abatorgenerated Mon Aug 03 13:53:45 CDT 2015
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

        public Criteria andId_user_groupIsNull() {
            addCriterion("id_user_group is null");
            return this;
        }

        public Criteria andId_user_groupIsNotNull() {
            addCriterion("id_user_group is not null");
            return this;
        }

        public Criteria andId_user_groupEqualTo(Long value) {
            addCriterion("id_user_group =", value, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupNotEqualTo(Long value) {
            addCriterion("id_user_group <>", value, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupGreaterThan(Long value) {
            addCriterion("id_user_group >", value, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupGreaterThanOrEqualTo(Long value) {
            addCriterion("id_user_group >=", value, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupLessThan(Long value) {
            addCriterion("id_user_group <", value, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupLessThanOrEqualTo(Long value) {
            addCriterion("id_user_group <=", value, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupIn(List<Long> values) {
            addCriterion("id_user_group in", values, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupNotIn(List<Long> values) {
            addCriterion("id_user_group not in", values, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupBetween(Long value1, Long value2) {
            addCriterion("id_user_group between", value1, value2, "id_user_group");
            return this;
        }

        public Criteria andId_user_groupNotBetween(Long value1, Long value2) {
            addCriterion("id_user_group not between", value1, value2, "id_user_group");
            return this;
        }

        public Criteria andId_groupIsNull() {
            addCriterion("id_group is null");
            return this;
        }

        public Criteria andId_groupIsNotNull() {
            addCriterion("id_group is not null");
            return this;
        }

        public Criteria andId_groupEqualTo(Long value) {
            addCriterion("id_group =", value, "id_group");
            return this;
        }

        public Criteria andId_groupNotEqualTo(Long value) {
            addCriterion("id_group <>", value, "id_group");
            return this;
        }

        public Criteria andId_groupGreaterThan(Long value) {
            addCriterion("id_group >", value, "id_group");
            return this;
        }

        public Criteria andId_groupGreaterThanOrEqualTo(Long value) {
            addCriterion("id_group >=", value, "id_group");
            return this;
        }

        public Criteria andId_groupLessThan(Long value) {
            addCriterion("id_group <", value, "id_group");
            return this;
        }

        public Criteria andId_groupLessThanOrEqualTo(Long value) {
            addCriterion("id_group <=", value, "id_group");
            return this;
        }

        public Criteria andId_groupIn(List<Long> values) {
            addCriterion("id_group in", values, "id_group");
            return this;
        }

        public Criteria andId_groupNotIn(List<Long> values) {
            addCriterion("id_group not in", values, "id_group");
            return this;
        }

        public Criteria andId_groupBetween(Long value1, Long value2) {
            addCriterion("id_group between", value1, value2, "id_group");
            return this;
        }

        public Criteria andId_groupNotBetween(Long value1, Long value2) {
            addCriterion("id_group not between", value1, value2, "id_group");
            return this;
        }

        public Criteria andId_userIsNull() {
            addCriterion("id_user is null");
            return this;
        }

        public Criteria andId_userIsNotNull() {
            addCriterion("id_user is not null");
            return this;
        }

        public Criteria andId_userEqualTo(Long value) {
            addCriterion("id_user =", value, "id_user");
            return this;
        }

        public Criteria andId_userNotEqualTo(Long value) {
            addCriterion("id_user <>", value, "id_user");
            return this;
        }

        public Criteria andId_userGreaterThan(Long value) {
            addCriterion("id_user >", value, "id_user");
            return this;
        }

        public Criteria andId_userGreaterThanOrEqualTo(Long value) {
            addCriterion("id_user >=", value, "id_user");
            return this;
        }

        public Criteria andId_userLessThan(Long value) {
            addCriterion("id_user <", value, "id_user");
            return this;
        }

        public Criteria andId_userLessThanOrEqualTo(Long value) {
            addCriterion("id_user <=", value, "id_user");
            return this;
        }

        public Criteria andId_userIn(List<Long> values) {
            addCriterion("id_user in", values, "id_user");
            return this;
        }

        public Criteria andId_userNotIn(List<Long> values) {
            addCriterion("id_user not in", values, "id_user");
            return this;
        }

        public Criteria andId_userBetween(Long value1, Long value2) {
            addCriterion("id_user between", value1, value2, "id_user");
            return this;
        }

        public Criteria andId_userNotBetween(Long value1, Long value2) {
            addCriterion("id_user not between", value1, value2, "id_user");
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
    }
}