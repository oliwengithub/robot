package com.oliwen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeConfigExample() {
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

        public Criteria andSdkPriceUrlIsNull() {
            addCriterion("sdk_price_url is null");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlIsNotNull() {
            addCriterion("sdk_price_url is not null");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlEqualTo(String value) {
            addCriterion("sdk_price_url =", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlNotEqualTo(String value) {
            addCriterion("sdk_price_url <>", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlGreaterThan(String value) {
            addCriterion("sdk_price_url >", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("sdk_price_url >=", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlLessThan(String value) {
            addCriterion("sdk_price_url <", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlLessThanOrEqualTo(String value) {
            addCriterion("sdk_price_url <=", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlLike(String value) {
            addCriterion("sdk_price_url like", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlNotLike(String value) {
            addCriterion("sdk_price_url not like", value, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlIn(List<String> values) {
            addCriterion("sdk_price_url in", values, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlNotIn(List<String> values) {
            addCriterion("sdk_price_url not in", values, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlBetween(String value1, String value2) {
            addCriterion("sdk_price_url between", value1, value2, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkPriceUrlNotBetween(String value1, String value2) {
            addCriterion("sdk_price_url not between", value1, value2, "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlIsNull() {
            addCriterion("sdk_to_usdt_url is null");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlIsNotNull() {
            addCriterion("sdk_to_usdt_url is not null");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlEqualTo(String value) {
            addCriterion("sdk_to_usdt_url =", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlNotEqualTo(String value) {
            addCriterion("sdk_to_usdt_url <>", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlGreaterThan(String value) {
            addCriterion("sdk_to_usdt_url >", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlGreaterThanOrEqualTo(String value) {
            addCriterion("sdk_to_usdt_url >=", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlLessThan(String value) {
            addCriterion("sdk_to_usdt_url <", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlLessThanOrEqualTo(String value) {
            addCriterion("sdk_to_usdt_url <=", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlLike(String value) {
            addCriterion("sdk_to_usdt_url like", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlNotLike(String value) {
            addCriterion("sdk_to_usdt_url not like", value, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlIn(List<String> values) {
            addCriterion("sdk_to_usdt_url in", values, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlNotIn(List<String> values) {
            addCriterion("sdk_to_usdt_url not in", values, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlBetween(String value1, String value2) {
            addCriterion("sdk_to_usdt_url between", value1, value2, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlNotBetween(String value1, String value2) {
            addCriterion("sdk_to_usdt_url not between", value1, value2, "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNull() {
            addCriterion("symbol is null");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNotNull() {
            addCriterion("symbol is not null");
            return (Criteria) this;
        }

        public Criteria andSymbolEqualTo(String value) {
            addCriterion("symbol =", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotEqualTo(String value) {
            addCriterion("symbol <>", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThan(String value) {
            addCriterion("symbol >", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThanOrEqualTo(String value) {
            addCriterion("symbol >=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThan(String value) {
            addCriterion("symbol <", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThanOrEqualTo(String value) {
            addCriterion("symbol <=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLike(String value) {
            addCriterion("symbol like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotLike(String value) {
            addCriterion("symbol not like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolIn(List<String> values) {
            addCriterion("symbol in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotIn(List<String> values) {
            addCriterion("symbol not in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolBetween(String value1, String value2) {
            addCriterion("symbol between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotBetween(String value1, String value2) {
            addCriterion("symbol not between", value1, value2, "symbol");
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

        public Criteria andSdkPriceUrlLikeInsensitive(String value) {
            addCriterion("upper(sdk_price_url) like", value.toUpperCase(), "sdkPriceUrl");
            return (Criteria) this;
        }

        public Criteria andSdkToUsdtUrlLikeInsensitive(String value) {
            addCriterion("upper(sdk_to_usdt_url) like", value.toUpperCase(), "sdkToUsdtUrl");
            return (Criteria) this;
        }

        public Criteria andSymbolLikeInsensitive(String value) {
            addCriterion("upper(symbol) like", value.toUpperCase(), "symbol");
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