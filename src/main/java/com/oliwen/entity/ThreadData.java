package com.oliwen.entity;

/**
 * @author: olw
 * @date: 2020/1/15 0015 11:17
 * @description: 交易机器人参数类
 */
public class ThreadData {
    /** 交易线程id **/
    private Integer id;
    /** 交易所名称 **/
    private String tradeName;
    /** 交易对名称 **/
    private String symbol;
    /** 调度周期 **/
    private Integer taskCycle;
    /** 线程状态 **/
    private Integer status;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getTradeName () {
        return tradeName;
    }

    public void setTradeName (String tradeName) {
        this.tradeName = tradeName;
    }

    public String getSymbol () {
        return symbol;
    }

    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Integer getTaskCycle () {
        return taskCycle;
    }

    public void setTaskCycle (Integer taskCycle) {
        this.taskCycle = taskCycle;
    }
}
