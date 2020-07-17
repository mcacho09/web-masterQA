package com.retailsbs.logistikapp.logistic.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TravelExample {
	/**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public TravelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
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
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table lgk_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andId_travelIsNull() {
            addCriterion("id_travel is null");
            return this;
        }

        public Criteria andId_travelIsNotNull() {
            addCriterion("id_travel is not null");
            return this;
        }

        public Criteria andId_travelEqualTo(Long value) {
            addCriterion("id_travel =", value, "id_travel");
            return this;
        }

        public Criteria andId_travelNotEqualTo(Long value) {
            addCriterion("id_travel <>", value, "id_travel");
            return this;
        }

        public Criteria andId_travelGreaterThan(Long value) {
            addCriterion("id_travel >", value, "id_travel");
            return this;
        }

        public Criteria andId_travelGreaterThanOrEqualTo(Long value) {
            addCriterion("id_travel >=", value, "id_travel");
            return this;
        }

        public Criteria andId_travelLessThan(Long value) {
            addCriterion("id_travel <", value, "id_travel");
            return this;
        }

        public Criteria andId_travelLessThanOrEqualTo(Long value) {
            addCriterion("id_travel <=", value, "id_travel");
            return this;
        }

        public Criteria andId_travelIn(List<Long> values) {
            addCriterion("id_travel in", values, "id_travel");
            return this;
        }

        public Criteria andId_travelNotIn(List<Long> values) {
            addCriterion("id_travel not in", values, "id_travel");
            return this;
        }

        public Criteria andId_travelBetween(Long value1, Long value2) {
            addCriterion("id_travel between", value1, value2, "id_travel");
            return this;
        }

        public Criteria andId_travelNotBetween(Long value1, Long value2) {
            addCriterion("id_travel not between", value1, value2, "id_travel");
            return this;
        }

        public Criteria andId_routeIsNull() {
            addCriterion("id_route is null");
            return this;
        }

        public Criteria andId_routeIsNotNull() {
            addCriterion("id_route is not null");
            return this;
        }

        public Criteria andId_routeEqualTo(Long value) {
            addCriterion("id_route =", value, "id_route");
            return this;
        }

        public Criteria andId_routeNotEqualTo(Long value) {
            addCriterion("id_route <>", value, "id_route");
            return this;
        }

        public Criteria andId_routeGreaterThan(Long value) {
            addCriterion("id_route >", value, "id_route");
            return this;
        }

        public Criteria andId_routeGreaterThanOrEqualTo(Long value) {
            addCriterion("id_route >=", value, "id_route");
            return this;
        }

        public Criteria andId_routeLessThan(Long value) {
            addCriterion("id_route <", value, "id_route");
            return this;
        }

        public Criteria andId_routeLessThanOrEqualTo(Long value) {
            addCriterion("id_route <=", value, "id_route");
            return this;
        }

        public Criteria andId_routeIn(List<Long> values) {
            addCriterion("id_route in", values, "id_route");
            return this;
        }

        public Criteria andId_routeNotIn(List<Long> values) {
            addCriterion("id_route not in", values, "id_route");
            return this;
        }

        public Criteria andId_routeBetween(Long value1, Long value2) {
            addCriterion("id_route between", value1, value2, "id_route");
            return this;
        }

        public Criteria andId_routeNotBetween(Long value1, Long value2) {
            addCriterion("id_route not between", value1, value2, "id_route");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andScheduleIsNull() {
            addCriterion("schedule is null");
            return this;
        }

        public Criteria andScheduleIsNotNull() {
            addCriterion("schedule is not null");
            return this;
        }

        public Criteria andScheduleEqualTo(Date value) {
            addCriterionForJDBCDate("schedule =", value, "schedule");
            return this;
        }

        public Criteria andScheduleNotEqualTo(Date value) {
            addCriterionForJDBCDate("schedule <>", value, "schedule");
            return this;
        }

        public Criteria andScheduleGreaterThan(Date value) {
            addCriterionForJDBCDate("schedule >", value, "schedule");
            return this;
        }

        public Criteria andScheduleGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("schedule >=", value, "schedule");
            return this;
        }

        public Criteria andScheduleLessThan(Date value) {
            addCriterionForJDBCDate("schedule <", value, "schedule");
            return this;
        }

        public Criteria andScheduleLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("schedule <=", value, "schedule");
            return this;
        }

        public Criteria andScheduleIn(List<Date> values) {
            addCriterionForJDBCDate("schedule in", values, "schedule");
            return this;
        }

        public Criteria andScheduleNotIn(List<Date> values) {
            addCriterionForJDBCDate("schedule not in", values, "schedule");
            return this;
        }

        public Criteria andScheduleBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("schedule between", value1, value2, "schedule");
            return this;
        }

        public Criteria andScheduleNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("schedule not between", value1, value2, "schedule");
            return this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return this;
        }

        public Criteria andStartedIsNull() {
            addCriterion("started is null");
            return this;
        }

        public Criteria andStartedIsNotNull() {
            addCriterion("started is not null");
            return this;
        }

        public Criteria andStartedEqualTo(Date value) {
            addCriterion("started =", value, "started");
            return this;
        }

        public Criteria andStartedNotEqualTo(Date value) {
            addCriterion("started <>", value, "started");
            return this;
        }

        public Criteria andStartedGreaterThan(Date value) {
            addCriterion("started >", value, "started");
            return this;
        }

        public Criteria andStartedGreaterThanOrEqualTo(Date value) {
            addCriterion("started >=", value, "started");
            return this;
        }

        public Criteria andStartedLessThan(Date value) {
            addCriterion("started <", value, "started");
            return this;
        }

        public Criteria andStartedLessThanOrEqualTo(Date value) {
            addCriterion("started <=", value, "started");
            return this;
        }

        public Criteria andStartedIn(List<Date> values) {
            addCriterion("started in", values, "started");
            return this;
        }

        public Criteria andStartedNotIn(List<Date> values) {
            addCriterion("started not in", values, "started");
            return this;
        }

        public Criteria andStartedBetween(Date value1, Date value2) {
            addCriterion("started between", value1, value2, "started");
            return this;
        }

        public Criteria andStartedNotBetween(Date value1, Date value2) {
            addCriterion("started not between", value1, value2, "started");
            return this;
        }

        public Criteria andFinishedIsNull() {
            addCriterion("finished is null");
            return this;
        }

        public Criteria andFinishedIsNotNull() {
            addCriterion("finished is not null");
            return this;
        }

        public Criteria andFinishedEqualTo(Date value) {
            addCriterion("finished =", value, "finished");
            return this;
        }

        public Criteria andFinishedNotEqualTo(Date value) {
            addCriterion("finished <>", value, "finished");
            return this;
        }

        public Criteria andFinishedGreaterThan(Date value) {
            addCriterion("finished >", value, "finished");
            return this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Date value) {
            addCriterion("finished >=", value, "finished");
            return this;
        }

        public Criteria andFinishedLessThan(Date value) {
            addCriterion("finished <", value, "finished");
            return this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Date value) {
            addCriterion("finished <=", value, "finished");
            return this;
        }

        public Criteria andFinishedIn(List<Date> values) {
            addCriterion("finished in", values, "finished");
            return this;
        }

        public Criteria andFinishedNotIn(List<Date> values) {
            addCriterion("finished not in", values, "finished");
            return this;
        }

        public Criteria andFinishedBetween(Date value1, Date value2) {
            addCriterion("finished between", value1, value2, "finished");
            return this;
        }

        public Criteria andFinishedNotBetween(Date value1, Date value2) {
            addCriterion("finished not between", value1, value2, "finished");
            return this;
        }

        public Criteria andLog_createdIsNull() {
            addCriterion("log_created is null");
            return this;
        }

        public Criteria andLog_createdIsNotNull() {
            addCriterion("log_created is not null");
            return this;
        }

        public Criteria andLog_createdEqualTo(Date value) {
            addCriterion("log_created =", value, "log_created");
            return this;
        }

        public Criteria andLog_createdNotEqualTo(Date value) {
            addCriterion("log_created <>", value, "log_created");
            return this;
        }

        public Criteria andLog_createdGreaterThan(Date value) {
            addCriterion("log_created >", value, "log_created");
            return this;
        }

        public Criteria andLog_createdGreaterThanOrEqualTo(Date value) {
            addCriterion("log_created >=", value, "log_created");
            return this;
        }

        public Criteria andLog_createdLessThan(Date value) {
            addCriterion("log_created <", value, "log_created");
            return this;
        }

        public Criteria andLog_createdLessThanOrEqualTo(Date value) {
            addCriterion("log_created <=", value, "log_created");
            return this;
        }

        public Criteria andLog_createdIn(List<Date> values) {
            addCriterion("log_created in", values, "log_created");
            return this;
        }

        public Criteria andLog_createdNotIn(List<Date> values) {
            addCriterion("log_created not in", values, "log_created");
            return this;
        }

        public Criteria andLog_createdBetween(Date value1, Date value2) {
            addCriterion("log_created between", value1, value2, "log_created");
            return this;
        }

        public Criteria andLog_createdNotBetween(Date value1, Date value2) {
            addCriterion("log_created not between", value1, value2, "log_created");
            return this;
        }

        public Criteria andLog_created_loginIsNull() {
            addCriterion("log_created_login is null");
            return this;
        }

        public Criteria andLog_created_loginIsNotNull() {
            addCriterion("log_created_login is not null");
            return this;
        }

        public Criteria andLog_created_loginEqualTo(String value) {
            addCriterion("log_created_login =", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginNotEqualTo(String value) {
            addCriterion("log_created_login <>", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginGreaterThan(String value) {
            addCriterion("log_created_login >", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginGreaterThanOrEqualTo(String value) {
            addCriterion("log_created_login >=", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginLessThan(String value) {
            addCriterion("log_created_login <", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginLessThanOrEqualTo(String value) {
            addCriterion("log_created_login <=", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginLike(String value) {
            addCriterion("log_created_login like", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginNotLike(String value) {
            addCriterion("log_created_login not like", value, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginIn(List<String> values) {
            addCriterion("log_created_login in", values, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginNotIn(List<String> values) {
            addCriterion("log_created_login not in", values, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginBetween(String value1, String value2) {
            addCriterion("log_created_login between", value1, value2, "log_created_login");
            return this;
        }

        public Criteria andLog_created_loginNotBetween(String value1, String value2) {
            addCriterion("log_created_login not between", value1, value2, "log_created_login");
            return this;
        }

        public Criteria andLog_modifiedIsNull() {
            addCriterion("log_modified is null");
            return this;
        }

        public Criteria andLog_modifiedIsNotNull() {
            addCriterion("log_modified is not null");
            return this;
        }

        public Criteria andLog_modifiedEqualTo(Date value) {
            addCriterion("log_modified =", value, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedNotEqualTo(Date value) {
            addCriterion("log_modified <>", value, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedGreaterThan(Date value) {
            addCriterion("log_modified >", value, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("log_modified >=", value, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedLessThan(Date value) {
            addCriterion("log_modified <", value, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedLessThanOrEqualTo(Date value) {
            addCriterion("log_modified <=", value, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedIn(List<Date> values) {
            addCriterion("log_modified in", values, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedNotIn(List<Date> values) {
            addCriterion("log_modified not in", values, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedBetween(Date value1, Date value2) {
            addCriterion("log_modified between", value1, value2, "log_modified");
            return this;
        }

        public Criteria andLog_modifiedNotBetween(Date value1, Date value2) {
            addCriterion("log_modified not between", value1, value2, "log_modified");
            return this;
        }

        public Criteria andLog_modified_loginIsNull() {
            addCriterion("log_modified_login is null");
            return this;
        }

        public Criteria andLog_modified_loginIsNotNull() {
            addCriterion("log_modified_login is not null");
            return this;
        }

        public Criteria andLog_modified_loginEqualTo(String value) {
            addCriterion("log_modified_login =", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginNotEqualTo(String value) {
            addCriterion("log_modified_login <>", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginGreaterThan(String value) {
            addCriterion("log_modified_login >", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginGreaterThanOrEqualTo(String value) {
            addCriterion("log_modified_login >=", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginLessThan(String value) {
            addCriterion("log_modified_login <", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginLessThanOrEqualTo(String value) {
            addCriterion("log_modified_login <=", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginLike(String value) {
            addCriterion("log_modified_login like", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginNotLike(String value) {
            addCriterion("log_modified_login not like", value, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginIn(List<String> values) {
            addCriterion("log_modified_login in", values, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginNotIn(List<String> values) {
            addCriterion("log_modified_login not in", values, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginBetween(String value1, String value2) {
            addCriterion("log_modified_login between", value1, value2, "log_modified_login");
            return this;
        }

        public Criteria andLog_modified_loginNotBetween(String value1, String value2) {
            addCriterion("log_modified_login not between", value1, value2, "log_modified_login");
            return this;
        }

        public Criteria andKmIsNull() {
            addCriterion("km is null");
            return this;
        }

        public Criteria andKmIsNotNull() {
            addCriterion("km is not null");
            return this;
        }

        public Criteria andKmEqualTo(Double value) {
            addCriterion("km =", value, "km");
            return this;
        }

        public Criteria andKmNotEqualTo(Double value) {
            addCriterion("km <>", value, "km");
            return this;
        }

        public Criteria andKmGreaterThan(Double value) {
            addCriterion("km >", value, "km");
            return this;
        }

        public Criteria andKmGreaterThanOrEqualTo(Double value) {
            addCriterion("km >=", value, "km");
            return this;
        }

        public Criteria andKmLessThan(Double value) {
            addCriterion("km <", value, "km");
            return this;
        }

        public Criteria andKmLessThanOrEqualTo(Double value) {
            addCriterion("km <=", value, "km");
            return this;
        }

        public Criteria andKmIn(List<Double> values) {
            addCriterion("km in", values, "km");
            return this;
        }

        public Criteria andKmNotIn(List<Double> values) {
            addCriterion("km not in", values, "km");
            return this;
        }

        public Criteria andKmBetween(Double value1, Double value2) {
            addCriterion("km between", value1, value2, "km");
            return this;
        }

        public Criteria andKmNotBetween(Double value1, Double value2) {
            addCriterion("km not between", value1, value2, "km");
            return this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return this;
        }

        public Criteria andTimeEqualTo(Double value) {
            addCriterion("time =", value, "time");
            return this;
        }

        public Criteria andTimeNotEqualTo(Double value) {
            addCriterion("time <>", value, "time");
            return this;
        }

        public Criteria andTimeGreaterThan(Double value) {
            addCriterion("time >", value, "time");
            return this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Double value) {
            addCriterion("time >=", value, "time");
            return this;
        }

        public Criteria andTimeLessThan(Double value) {
            addCriterion("time <", value, "time");
            return this;
        }

        public Criteria andTimeLessThanOrEqualTo(Double value) {
            addCriterion("time <=", value, "time");
            return this;
        }

        public Criteria andTimeIn(List<Double> values) {
            addCriterion("time in", values, "time");
            return this;
        }

        public Criteria andTimeNotIn(List<Double> values) {
            addCriterion("time not in", values, "time");
            return this;
        }

        public Criteria andTimeBetween(Double value1, Double value2) {
            addCriterion("time between", value1, value2, "time");
            return this;
        }

        public Criteria andTimeNotBetween(Double value1, Double value2) {
            addCriterion("time not between", value1, value2, "time");
            return this;
        }
    }
}