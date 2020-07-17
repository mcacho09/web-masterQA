package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAlmacenExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    public ProductAlmacenExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
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
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table lgk_product_almacen
     *
     * @abatorgenerated Mon Jul 06 12:31:48 CDT 2020
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

        public Criteria andId_product_almacenIsNull() {
            addCriterion("id_product_almacen is null");
            return this;
        }

        public Criteria andId_product_almacenIsNotNull() {
            addCriterion("id_product_almacen is not null");
            return this;
        }

        public Criteria andId_product_almacenEqualTo(Long value) {
            addCriterion("id_product_almacen =", value, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenNotEqualTo(Long value) {
            addCriterion("id_product_almacen <>", value, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenGreaterThan(Long value) {
            addCriterion("id_product_almacen >", value, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenGreaterThanOrEqualTo(Long value) {
            addCriterion("id_product_almacen >=", value, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenLessThan(Long value) {
            addCriterion("id_product_almacen <", value, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenLessThanOrEqualTo(Long value) {
            addCriterion("id_product_almacen <=", value, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenIn(List<Long> values) {
            addCriterion("id_product_almacen in", values, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenNotIn(List<Long> values) {
            addCriterion("id_product_almacen not in", values, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenBetween(Long value1, Long value2) {
            addCriterion("id_product_almacen between", value1, value2, "id_product_almacen");
            return this;
        }

        public Criteria andId_product_almacenNotBetween(Long value1, Long value2) {
            addCriterion("id_product_almacen not between", value1, value2, "id_product_almacen");
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

        public Criteria andId_almacenIsNull() {
            addCriterion("id_almacen is null");
            return this;
        }

        public Criteria andId_almacenIsNotNull() {
            addCriterion("id_almacen is not null");
            return this;
        }

        public Criteria andId_almacenEqualTo(Long value) {
            addCriterion("id_almacen =", value, "id_almacen");
            return this;
        }

        public Criteria andId_almacenNotEqualTo(Long value) {
            addCriterion("id_almacen <>", value, "id_almacen");
            return this;
        }

        public Criteria andId_almacenGreaterThan(Long value) {
            addCriterion("id_almacen >", value, "id_almacen");
            return this;
        }

        public Criteria andId_almacenGreaterThanOrEqualTo(Long value) {
            addCriterion("id_almacen >=", value, "id_almacen");
            return this;
        }

        public Criteria andId_almacenLessThan(Long value) {
            addCriterion("id_almacen <", value, "id_almacen");
            return this;
        }

        public Criteria andId_almacenLessThanOrEqualTo(Long value) {
            addCriterion("id_almacen <=", value, "id_almacen");
            return this;
        }

        public Criteria andId_almacenIn(List<Long> values) {
            addCriterion("id_almacen in", values, "id_almacen");
            return this;
        }

        public Criteria andId_almacenNotIn(List<Long> values) {
            addCriterion("id_almacen not in", values, "id_almacen");
            return this;
        }

        public Criteria andId_almacenBetween(Long value1, Long value2) {
            addCriterion("id_almacen between", value1, value2, "id_almacen");
            return this;
        }

        public Criteria andId_almacenNotBetween(Long value1, Long value2) {
            addCriterion("id_almacen not between", value1, value2, "id_almacen");
            return this;
        }

        public Criteria andQtyIsNull() {
            addCriterion("qty is null");
            return this;
        }

        public Criteria andQtyIsNotNull() {
            addCriterion("qty is not null");
            return this;
        }

        public Criteria andQtyEqualTo(Long value) {
            addCriterion("qty =", value, "qty");
            return this;
        }

        public Criteria andQtyNotEqualTo(Long value) {
            addCriterion("qty <>", value, "qty");
            return this;
        }

        public Criteria andQtyGreaterThan(Long value) {
            addCriterion("qty >", value, "qty");
            return this;
        }

        public Criteria andQtyGreaterThanOrEqualTo(Long value) {
            addCriterion("qty >=", value, "qty");
            return this;
        }

        public Criteria andQtyLessThan(Long value) {
            addCriterion("qty <", value, "qty");
            return this;
        }

        public Criteria andQtyLessThanOrEqualTo(Long value) {
            addCriterion("qty <=", value, "qty");
            return this;
        }

        public Criteria andQtyIn(List<Long> values) {
            addCriterion("qty in", values, "qty");
            return this;
        }

        public Criteria andQtyNotIn(List<Long> values) {
            addCriterion("qty not in", values, "qty");
            return this;
        }

        public Criteria andQtyBetween(Long value1, Long value2) {
            addCriterion("qty between", value1, value2, "qty");
            return this;
        }

        public Criteria andQtyNotBetween(Long value1, Long value2) {
            addCriterion("qty not between", value1, value2, "qty");
            return this;
        }

        public Criteria andMinIsNull() {
            addCriterion("min is null");
            return this;
        }

        public Criteria andMinIsNotNull() {
            addCriterion("min is not null");
            return this;
        }

        public Criteria andMinEqualTo(Long value) {
            addCriterion("min =", value, "min");
            return this;
        }

        public Criteria andMinNotEqualTo(Long value) {
            addCriterion("min <>", value, "min");
            return this;
        }

        public Criteria andMinGreaterThan(Long value) {
            addCriterion("min >", value, "min");
            return this;
        }

        public Criteria andMinGreaterThanOrEqualTo(Long value) {
            addCriterion("min >=", value, "min");
            return this;
        }

        public Criteria andMinLessThan(Long value) {
            addCriterion("min <", value, "min");
            return this;
        }

        public Criteria andMinLessThanOrEqualTo(Long value) {
            addCriterion("min <=", value, "min");
            return this;
        }

        public Criteria andMinIn(List<Long> values) {
            addCriterion("min in", values, "min");
            return this;
        }

        public Criteria andMinNotIn(List<Long> values) {
            addCriterion("min not in", values, "min");
            return this;
        }

        public Criteria andMinBetween(Long value1, Long value2) {
            addCriterion("min between", value1, value2, "min");
            return this;
        }

        public Criteria andMinNotBetween(Long value1, Long value2) {
            addCriterion("min not between", value1, value2, "min");
            return this;
        }

        public Criteria andMaxIsNull() {
            addCriterion("max is null");
            return this;
        }

        public Criteria andMaxIsNotNull() {
            addCriterion("max is not null");
            return this;
        }

        public Criteria andMaxEqualTo(Long value) {
            addCriterion("max =", value, "max");
            return this;
        }

        public Criteria andMaxNotEqualTo(Long value) {
            addCriterion("max <>", value, "max");
            return this;
        }

        public Criteria andMaxGreaterThan(Long value) {
            addCriterion("max >", value, "max");
            return this;
        }

        public Criteria andMaxGreaterThanOrEqualTo(Long value) {
            addCriterion("max >=", value, "max");
            return this;
        }

        public Criteria andMaxLessThan(Long value) {
            addCriterion("max <", value, "max");
            return this;
        }

        public Criteria andMaxLessThanOrEqualTo(Long value) {
            addCriterion("max <=", value, "max");
            return this;
        }

        public Criteria andMaxIn(List<Long> values) {
            addCriterion("max in", values, "max");
            return this;
        }

        public Criteria andMaxNotIn(List<Long> values) {
            addCriterion("max not in", values, "max");
            return this;
        }

        public Criteria andMaxBetween(Long value1, Long value2) {
            addCriterion("max between", value1, value2, "max");
            return this;
        }

        public Criteria andMaxNotBetween(Long value1, Long value2) {
            addCriterion("max not between", value1, value2, "max");
            return this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return this;
        }
    }
}