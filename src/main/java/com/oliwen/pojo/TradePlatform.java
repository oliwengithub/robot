package com.oliwen.pojo;

import java.io.Serializable;
import java.util.Date;

public class TradePlatform implements Serializable {
    private Integer id;

    private String tradeUrl;

    private String tradeName;

    private String totalNumUrl;

    private Date createDate;

    private static final long serialVersionUID = 1L;

    public TradePlatform(Integer id, String tradeUrl, String tradeName, String totalNumUrl, Date createDate) {
        this.id = id;
        this.tradeUrl = tradeUrl;
        this.tradeName = tradeName;
        this.totalNumUrl = totalNumUrl;
        this.createDate = createDate;
    }

    public TradePlatform() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeUrl() {
        return tradeUrl;
    }

    public void setTradeUrl(String tradeUrl) {
        this.tradeUrl = tradeUrl == null ? null : tradeUrl.trim();
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName == null ? null : tradeName.trim();
    }

    public String getTotalNumUrl() {
        return totalNumUrl;
    }

    public void setTotalNumUrl(String totalNumUrl) {
        this.totalNumUrl = totalNumUrl == null ? null : totalNumUrl.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        TradePlatform other = (TradePlatform) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeUrl() == null ? other.getTradeUrl() == null : this.getTradeUrl().equals(other.getTradeUrl()))
            && (this.getTradeName() == null ? other.getTradeName() == null : this.getTradeName().equals(other.getTradeName()))
            && (this.getTotalNumUrl() == null ? other.getTotalNumUrl() == null : this.getTotalNumUrl().equals(other.getTotalNumUrl()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeUrl() == null) ? 0 : getTradeUrl().hashCode());
        result = prime * result + ((getTradeName() == null) ? 0 : getTradeName().hashCode());
        result = prime * result + ((getTotalNumUrl() == null) ? 0 : getTotalNumUrl().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tradeUrl=").append(tradeUrl);
        sb.append(", tradeName=").append(tradeName);
        sb.append(", totalNumUrl=").append(totalNumUrl);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}