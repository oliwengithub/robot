package com.oliwen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeThreadExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeThreadExample() {
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

        public Criteria andClientIdIsNull() {
            addCriterion("client_id is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("client_id is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(Integer value) {
            addCriterion("client_id =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(Integer value) {
            addCriterion("client_id <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(Integer value) {
            addCriterion("client_id >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("client_id >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(Integer value) {
            addCriterion("client_id <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(Integer value) {
            addCriterion("client_id <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<Integer> values) {
            addCriterion("client_id in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<Integer> values) {
            addCriterion("client_id not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(Integer value1, Integer value2) {
            addCriterion("client_id between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(Integer value1, Integer value2) {
            addCriterion("client_id not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andTradeMaxIsNull() {
            addCriterion("trade_max is null");
            return (Criteria) this;
        }

        public Criteria andTradeMaxIsNotNull() {
            addCriterion("trade_max is not null");
            return (Criteria) this;
        }

        public Criteria andTradeMaxEqualTo(Double value) {
            addCriterion("trade_max =", value, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxNotEqualTo(Double value) {
            addCriterion("trade_max <>", value, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxGreaterThan(Double value) {
            addCriterion("trade_max >", value, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxGreaterThanOrEqualTo(Double value) {
            addCriterion("trade_max >=", value, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxLessThan(Double value) {
            addCriterion("trade_max <", value, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxLessThanOrEqualTo(Double value) {
            addCriterion("trade_max <=", value, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxIn(List<Double> values) {
            addCriterion("trade_max in", values, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxNotIn(List<Double> values) {
            addCriterion("trade_max not in", values, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxBetween(Double value1, Double value2) {
            addCriterion("trade_max between", value1, value2, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMaxNotBetween(Double value1, Double value2) {
            addCriterion("trade_max not between", value1, value2, "tradeMax");
            return (Criteria) this;
        }

        public Criteria andTradeMinIsNull() {
            addCriterion("trade_min is null");
            return (Criteria) this;
        }

        public Criteria andTradeMinIsNotNull() {
            addCriterion("trade_min is not null");
            return (Criteria) this;
        }

        public Criteria andTradeMinEqualTo(Double value) {
            addCriterion("trade_min =", value, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinNotEqualTo(Double value) {
            addCriterion("trade_min <>", value, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinGreaterThan(Double value) {
            addCriterion("trade_min >", value, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinGreaterThanOrEqualTo(Double value) {
            addCriterion("trade_min >=", value, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinLessThan(Double value) {
            addCriterion("trade_min <", value, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinLessThanOrEqualTo(Double value) {
            addCriterion("trade_min <=", value, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinIn(List<Double> values) {
            addCriterion("trade_min in", values, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinNotIn(List<Double> values) {
            addCriterion("trade_min not in", values, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinBetween(Double value1, Double value2) {
            addCriterion("trade_min between", value1, value2, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andTradeMinNotBetween(Double value1, Double value2) {
            addCriterion("trade_min not between", value1, value2, "tradeMin");
            return (Criteria) this;
        }

        public Criteria andWaveRatioIsNull() {
            addCriterion("wave_ratio is null");
            return (Criteria) this;
        }

        public Criteria andWaveRatioIsNotNull() {
            addCriterion("wave_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andWaveRatioEqualTo(Double value) {
            addCriterion("wave_ratio =", value, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioNotEqualTo(Double value) {
            addCriterion("wave_ratio <>", value, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioGreaterThan(Double value) {
            addCriterion("wave_ratio >", value, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioGreaterThanOrEqualTo(Double value) {
            addCriterion("wave_ratio >=", value, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioLessThan(Double value) {
            addCriterion("wave_ratio <", value, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioLessThanOrEqualTo(Double value) {
            addCriterion("wave_ratio <=", value, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioIn(List<Double> values) {
            addCriterion("wave_ratio in", values, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioNotIn(List<Double> values) {
            addCriterion("wave_ratio not in", values, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioBetween(Double value1, Double value2) {
            addCriterion("wave_ratio between", value1, value2, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andWaveRatioNotBetween(Double value1, Double value2) {
            addCriterion("wave_ratio not between", value1, value2, "waveRatio");
            return (Criteria) this;
        }

        public Criteria andPriceDigitIsNull() {
            addCriterion("price_digit is null");
            return (Criteria) this;
        }

        public Criteria andPriceDigitIsNotNull() {
            addCriterion("price_digit is not null");
            return (Criteria) this;
        }

        public Criteria andPriceDigitEqualTo(Integer value) {
            addCriterion("price_digit =", value, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitNotEqualTo(Integer value) {
            addCriterion("price_digit <>", value, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitGreaterThan(Integer value) {
            addCriterion("price_digit >", value, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitGreaterThanOrEqualTo(Integer value) {
            addCriterion("price_digit >=", value, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitLessThan(Integer value) {
            addCriterion("price_digit <", value, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitLessThanOrEqualTo(Integer value) {
            addCriterion("price_digit <=", value, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitIn(List<Integer> values) {
            addCriterion("price_digit in", values, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitNotIn(List<Integer> values) {
            addCriterion("price_digit not in", values, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitBetween(Integer value1, Integer value2) {
            addCriterion("price_digit between", value1, value2, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andPriceDigitNotBetween(Integer value1, Integer value2) {
            addCriterion("price_digit not between", value1, value2, "priceDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitIsNull() {
            addCriterion("num_digit is null");
            return (Criteria) this;
        }

        public Criteria andNumDigitIsNotNull() {
            addCriterion("num_digit is not null");
            return (Criteria) this;
        }

        public Criteria andNumDigitEqualTo(Integer value) {
            addCriterion("num_digit =", value, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitNotEqualTo(Integer value) {
            addCriterion("num_digit <>", value, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitGreaterThan(Integer value) {
            addCriterion("num_digit >", value, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_digit >=", value, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitLessThan(Integer value) {
            addCriterion("num_digit <", value, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitLessThanOrEqualTo(Integer value) {
            addCriterion("num_digit <=", value, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitIn(List<Integer> values) {
            addCriterion("num_digit in", values, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitNotIn(List<Integer> values) {
            addCriterion("num_digit not in", values, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitBetween(Integer value1, Integer value2) {
            addCriterion("num_digit between", value1, value2, "numDigit");
            return (Criteria) this;
        }

        public Criteria andNumDigitNotBetween(Integer value1, Integer value2) {
            addCriterion("num_digit not between", value1, value2, "numDigit");
            return (Criteria) this;
        }

        public Criteria andTaskCycleIsNull() {
            addCriterion("task_cycle is null");
            return (Criteria) this;
        }

        public Criteria andTaskCycleIsNotNull() {
            addCriterion("task_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCycleEqualTo(Integer value) {
            addCriterion("task_cycle =", value, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleNotEqualTo(Integer value) {
            addCriterion("task_cycle <>", value, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleGreaterThan(Integer value) {
            addCriterion("task_cycle >", value, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_cycle >=", value, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleLessThan(Integer value) {
            addCriterion("task_cycle <", value, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleLessThanOrEqualTo(Integer value) {
            addCriterion("task_cycle <=", value, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleIn(List<Integer> values) {
            addCriterion("task_cycle in", values, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleNotIn(List<Integer> values) {
            addCriterion("task_cycle not in", values, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleBetween(Integer value1, Integer value2) {
            addCriterion("task_cycle between", value1, value2, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andTaskCycleNotBetween(Integer value1, Integer value2) {
            addCriterion("task_cycle not between", value1, value2, "taskCycle");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdIsNull() {
            addCriterion("trade_platform_id is null");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdIsNotNull() {
            addCriterion("trade_platform_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdEqualTo(Integer value) {
            addCriterion("trade_platform_id =", value, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdNotEqualTo(Integer value) {
            addCriterion("trade_platform_id <>", value, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdGreaterThan(Integer value) {
            addCriterion("trade_platform_id >", value, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_platform_id >=", value, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdLessThan(Integer value) {
            addCriterion("trade_platform_id <", value, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdLessThanOrEqualTo(Integer value) {
            addCriterion("trade_platform_id <=", value, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdIn(List<Integer> values) {
            addCriterion("trade_platform_id in", values, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdNotIn(List<Integer> values) {
            addCriterion("trade_platform_id not in", values, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdBetween(Integer value1, Integer value2) {
            addCriterion("trade_platform_id between", value1, value2, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradePlatformIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_platform_id not between", value1, value2, "tradePlatformId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdIsNull() {
            addCriterion("trade_config_id is null");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdIsNotNull() {
            addCriterion("trade_config_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdEqualTo(Integer value) {
            addCriterion("trade_config_id =", value, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdNotEqualTo(Integer value) {
            addCriterion("trade_config_id <>", value, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdGreaterThan(Integer value) {
            addCriterion("trade_config_id >", value, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_config_id >=", value, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdLessThan(Integer value) {
            addCriterion("trade_config_id <", value, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdLessThanOrEqualTo(Integer value) {
            addCriterion("trade_config_id <=", value, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdIn(List<Integer> values) {
            addCriterion("trade_config_id in", values, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdNotIn(List<Integer> values) {
            addCriterion("trade_config_id not in", values, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdBetween(Integer value1, Integer value2) {
            addCriterion("trade_config_id between", value1, value2, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andTradeConfigIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_config_id not between", value1, value2, "tradeConfigId");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeIsNull() {
            addCriterion("stop_time is null");
            return (Criteria) this;
        }

        public Criteria andStopTimeIsNotNull() {
            addCriterion("stop_time is not null");
            return (Criteria) this;
        }

        public Criteria andStopTimeEqualTo(Date value) {
            addCriterion("stop_time =", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotEqualTo(Date value) {
            addCriterion("stop_time <>", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeGreaterThan(Date value) {
            addCriterion("stop_time >", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("stop_time >=", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeLessThan(Date value) {
            addCriterion("stop_time <", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeLessThanOrEqualTo(Date value) {
            addCriterion("stop_time <=", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeIn(List<Date> values) {
            addCriterion("stop_time in", values, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotIn(List<Date> values) {
            addCriterion("stop_time not in", values, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeBetween(Date value1, Date value2) {
            addCriterion("stop_time between", value1, value2, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotBetween(Date value1, Date value2) {
            addCriterion("stop_time not between", value1, value2, "stopTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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