package com.oliwen.pojo;

import java.io.Serializable;
import java.util.Date;

public class TradeConfig implements Serializable {
    private Integer id;

    private String sdkPriceUrl;

    private String sdkToUsdtUrl;

    private String symbol;

    private Date createDate;

    private static final long serialVersionUID = 1L;

    public TradeConfig(Integer id, String sdkPriceUrl, String sdkToUsdtUrl, String symbol, Date createDate) {
        this.id = id;
        this.sdkPriceUrl = sdkPriceUrl;
        this.sdkToUsdtUrl = sdkToUsdtUrl;
        this.symbol = symbol;
        this.createDate = createDate;
    }

    public TradeConfig() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSdkPriceUrl() {
        return sdkPriceUrl;
    }

    public void setSdkPriceUrl(String sdkPriceUrl) {
        this.sdkPriceUrl = sdkPriceUrl == null ? null : sdkPriceUrl.trim();
    }

    public String getSdkToUsdtUrl() {
        return sdkToUsdtUrl;
    }

    public void setSdkToUsdtUrl(String sdkToUsdtUrl) {
        this.sdkToUsdtUrl = sdkToUsdtUrl == null ? null : sdkToUsdtUrl.trim();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
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
        TradeConfig other = (TradeConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSdkPriceUrl() == null ? other.getSdkPriceUrl() == null : this.getSdkPriceUrl().equals(other.getSdkPriceUrl()))
            && (this.getSdkToUsdtUrl() == null ? other.getSdkToUsdtUrl() == null : this.getSdkToUsdtUrl().equals(other.getSdkToUsdtUrl()))
            && (this.getSymbol() == null ? other.getSymbol() == null : this.getSymbol().equals(other.getSymbol()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSdkPriceUrl() == null) ? 0 : getSdkPriceUrl().hashCode());
        result = prime * result + ((getSdkToUsdtUrl() == null) ? 0 : getSdkToUsdtUrl().hashCode());
        result = prime * result + ((getSymbol() == null) ? 0 : getSymbol().hashCode());
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
        sb.append(", sdkPriceUrl=").append(sdkPriceUrl);
        sb.append(", sdkToUsdtUrl=").append(sdkToUsdtUrl);
        sb.append(", symbol=").append(symbol);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}