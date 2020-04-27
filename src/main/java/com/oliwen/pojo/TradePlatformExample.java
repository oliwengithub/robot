package com.oliwen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradePlatformExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradePlatformExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTradeUrlIsNull() {
            addCriterion("trade_url is null");
            return (Criteria) this;
        }

        public Criteria andTradeUrlIsNotNull() {
            addCriterion("trade_url is not null");
            return (Criteria) this;
        }

        public Criteria andTradeUrlEqualTo(String value) {
            addCriterion("trade_url =", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlNotEqualTo(String value) {
            addCriterion("trade_url <>", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlGreaterThan(String value) {
            addCriterion("trade_url >", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlGreaterThanOrEqualTo(String value) {
            addCriterion("trade_url >=", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlLessThan(String value) {
            addCriterion("trade_url <", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlLessThanOrEqualTo(String value) {
            addCriterion("trade_url <=", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlLike(String value) {
            addCriterion("trade_url like", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlNotLike(String value) {
            addCriterion("trade_url not like", value, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlIn(List<String> values) {
            addCriterion("trade_url in", values, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlNotIn(List<String> values) {
            addCriterion("trade_url not in", values, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlBetween(String value1, String value2) {
            addCriterion("trade_url between", value1, value2, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeUrlNotBetween(String value1, String value2) {
            addCriterion("trade_url not between", value1, value2, "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeNameIsNull() {
            addCriterion("trade_name is null");
            return (Criteria) this;
        }

        public Criteria andTradeNameIsNotNull() {
            addCriterion("trade_name is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNameEqualTo(String value) {
            addCriterion("trade_name =", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotEqualTo(String value) {
            addCriterion("trade_name <>", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameGreaterThan(String value) {
            addCriterion("trade_name >", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("trade_name >=", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLessThan(String value) {
            addCriterion("trade_name <", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLessThanOrEqualTo(String value) {
            addCriterion("trade_name <=", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLike(String value) {
            addCriterion("trade_name like", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotLike(String value) {
            addCriterion("trade_name not like", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameIn(List<String> values) {
            addCriterion("trade_name in", values, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotIn(List<String> values) {
            addCriterion("trade_name not in", values, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameBetween(String value1, String value2) {
            addCriterion("trade_name between", value1, value2, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotBetween(String value1, String value2) {
            addCriterion("trade_name not between", value1, value2, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlIsNull() {
            addCriterion("total_num_url is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlIsNotNull() {
            addCriterion("total_num_url is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlEqualTo(String value) {
            addCriterion("total_num_url =", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlNotEqualTo(String value) {
            addCriterion("total_num_url <>", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlGreaterThan(String value) {
            addCriterion("total_num_url >", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlGreaterThanOrEqualTo(String value) {
            addCriterion("total_num_url >=", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlLessThan(String value) {
            addCriterion("total_num_url <", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlLessThanOrEqualTo(String value) {
            addCriterion("total_num_url <=", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlLike(String value) {
            addCriterion("total_num_url like", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlNotLike(String value) {
            addCriterion("total_num_url not like", value, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlIn(List<String> values) {
            addCriterion("total_num_url in", values, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlNotIn(List<String> values) {
            addCriterion("total_num_url not in", values, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlBetween(String value1, String value2) {
            addCriterion("total_num_url between", value1, value2, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlNotBetween(String value1, String value2) {
            addCriterion("total_num_url not between", value1, value2, "totalNumUrl");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andTradeUrlLikeInsensitive(String value) {
            addCriterion("upper(trade_url) like", value.toUpperCase(), "tradeUrl");
            return (Criteria) this;
        }

        public Criteria andTradeNameLikeInsensitive(String value) {
            addCriterion("upper(trade_name) like", value.toUpperCase(), "tradeName");
            return (Criteria) this;
        }

        public Criteria andTotalNumUrlLikeInsensitive(String value) {
            addCriterion("upper(total_num_url) like", value.toUpperCase(), "totalNumUrl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}