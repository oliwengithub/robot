package com.oliwen.pojo;

import java.io.Serializable;
import java.util.Date;

public class TradeThread implements Serializable {
    private Integer id;

    private Integer clientId;

    private Double tradeMax;

    private Double tradeMin;

    private Double waveRatio;

    private Integer priceDigit;

    private Integer numDigit;

    private Integer taskCycle;

    private Integer status;

    private Integer tradePlatformId;

    private Integer tradeConfigId;

    private Date startTime;

    private Date stopTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public TradeThread(Integer id, Integer clientId, Double tradeMax, Double tradeMin, Double waveRatio, Integer priceDigit, Integer numDigit, Integer taskCycle, Integer status, Integer tradePlatformId, Integer tradeConfigId, Date startTime, Date stopTime, Date createTime) {
        this.id = id;
        this.clientId = clientId;
        this.tradeMax = tradeMax;
        this.tradeMin = tradeMin;
        this.waveRatio = waveRatio;
        this.priceDigit = priceDigit;
        this.numDigit = numDigit;
        this.taskCycle = taskCycle;
        this.status = status;
        this.tradePlatformId = tradePlatformId;
        this.tradeConfigId = tradeConfigId;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.createTime = createTime;
    }

    public TradeThread() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Double getTradeMax() {
        return tradeMax;
    }

    public void setTradeMax(Double tradeMax) {
        this.tradeMax = tradeMax;
    }

    public Double getTradeMin() {
        return tradeMin;
    }

    public void setTradeMin(Double tradeMin) {
        this.tradeMin = tradeMin;
    }

    public Double getWaveRatio() {
        return waveRatio;
    }

    public void setWaveRatio(Double waveRatio) {
        this.waveRatio = waveRatio;
    }

    public Integer getPriceDigit() {
        return priceDigit;
    }

    public void setPriceDigit(Integer priceDigit) {
        this.priceDigit = priceDigit;
    }

    public Integer getNumDigit() {
        return numDigit;
    }

    public void setNumDigit(Integer numDigit) {
        this.numDigit = numDigit;
    }

    public Integer getTaskCycle() {
        return taskCycle;
    }

    public void setTaskCycle(Integer taskCycle) {
        this.taskCycle = taskCycle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTradePlatformId() {
        return tradePlatformId;
    }

    public void setTradePlatformId(Integer tradePlatformId) {
        this.tradePlatformId = tradePlatformId;
    }

    public Integer getTradeConfigId() {
        return tradeConfigId;
    }

    public void setTradeConfigId(Integer tradeConfigId) {
        this.tradeConfigId = tradeConfigId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TradeThread other = (TradeThread) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getTradeMax() == null ? other.getTradeMax() == null : this.getTradeMax().equals(other.getTradeMax()))
            && (this.getTradeMin() == null ? other.getTradeMin() == null : this.getTradeMin().equals(other.getTradeMin()))
            && (this.getWaveRatio() == null ? other.getWaveRatio() == null : this.getWaveRatio().equals(other.getWaveRatio()))
            && (this.getPriceDigit() == null ? other.getPriceDigit() == null : this.getPriceDigit().equals(other.getPriceDigit()))
            && (this.getNumDigit() == null ? other.getNumDigit() == null : this.getNumDigit().equals(other.getNumDigit()))
            && (this.getTaskCycle() == null ? other.getTaskCycle() == null : this.getTaskCycle().equals(other.getTaskCycle()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTradePlatformId() == null ? other.getTradePlatformId() == null : this.getTradePlatformId().equals(other.getTradePlatformId()))
            && (this.getTradeConfigId() == null ? other.getTradeConfigId() == null : this.getTradeConfigId().equals(other.getTradeConfigId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getStopTime() == null ? other.getStopTime() == null : this.getStopTime().equals(other.getStopTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getTradeMax() == null) ? 0 : getTradeMax().hashCode());
        result = prime * result + ((getTradeMin() == null) ? 0 : getTradeMin().hashCode());
        result = prime * result + ((getWaveRatio() == null) ? 0 : getWaveRatio().hashCode());
        result = prime * result + ((getPriceDigit() == null) ? 0 : getPriceDigit().hashCode());
        result = prime * result + ((getNumDigit() == null) ? 0 : getNumDigit().hashCode());
        result = prime * result + ((getTaskCycle() == null) ? 0 : getTaskCycle().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTradePlatformId() == null) ? 0 : getTradePlatformId().hashCode());
        result = prime * result + ((getTradeConfigId() == null) ? 0 : getTradeConfigId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getStopTime() == null) ? 0 : getStopTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clientId=").append(clientId);
        sb.append(", tradeMax=").append(tradeMax);
        sb.append(", tradeMin=").append(tradeMin);
        sb.append(", waveRatio=").append(waveRatio);
        sb.append(", priceDigit=").append(priceDigit);
        sb.append(", numDigit=").append(numDigit);
        sb.append(", taskCycle=").append(taskCycle);
        sb.append(", status=").append(status);
        sb.append(", tradePlatformId=").append(tradePlatformId);
        sb.append(", tradeConfigId=").append(tradeConfigId);
        sb.append(", startTime=").append(startTime);
        sb.append(", stopTime=").append(stopTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}