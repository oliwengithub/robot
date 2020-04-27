package com.oliwen.thread;

import com.alibaba.fastjson.JSON;
import com.oliwen.util.HttpConnectionPoolUtil;
import com.oliwen.util.MathUtils;
import com.oliwen.util.thread.ThreadConstants;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: olw
 * @date: 2020/1/13 0013 11:56
 * @description: 交易任务线程
 */
public class TradeTaskThread implements Runnable {

    /** 获取当前单价盘面数量URL **/
    private  String totalNumUrl;
    /** 开始交易机器人链接 **/
    private String startTradeUrl;
    /** 获取参数交易对比例价格接口url **/
    private String sdkPriceUrl;
    /** 获取参数交易对中商品折合USDT接口url **/
    private String sdkToUsdtUrl;
    /** 交易机器人Id **/
    private Long clientId;
    /** 单价波动比例 **/
    private Double waveRatio;
    /** 单价小数位 **/
    private Integer priceDigit;
    /** 数量小数位 **/
    private Integer numDigit;
    /** 交易对名称 **/
    private String symbol;
    /** 默认单笔挂单最大限额(人民币) **/
    private Double tradeMax;
    /** 默认单笔挂单最小限额(人民币) **/
    private Double tradeMin;

    public TradeTaskThread (String totalNumUrl, String startTradeUrl, String sdkPriceUrl, String sdkToUsdtUrl, Long clientId, Double waveRatio, Integer priceDigit, Integer numDigit, String symbol, Double tradeMax, Double tradeMin) {
        this.totalNumUrl = totalNumUrl;
        this.startTradeUrl = startTradeUrl;
        this.sdkPriceUrl = sdkPriceUrl;
        this.sdkToUsdtUrl = sdkToUsdtUrl;
        this.clientId = clientId;
        this.waveRatio = waveRatio;
        this.priceDigit = priceDigit;
        this.numDigit = numDigit;
        this.symbol = symbol;
        this.tradeMax = tradeMax;
        this.tradeMin = tradeMin;
    }

    @Override
    public void run () {
        //防止抛异常后单个周期任务终止
        try {
            Double symbolPrice = null;
            Double toUsdtPrice = null;
            //获取交易对价格
            String priceStr = HttpConnectionPoolUtil.get(sdkPriceUrl, "");
            //获取商品转成usdt
            String toUsdtStr = HttpConnectionPoolUtil.get(sdkToUsdtUrl, "");
            //System.out.println("------"+symbol+"----交易启动-------当前执行线程----"+Thread.currentThread().getName());
            if ( (priceStr != null && !"".equals(priceStr)) && (toUsdtStr != null && !"".equals(toUsdtStr)) ) {
                symbolPrice = JSON.parseObject(priceStr).getDouble("last");
                toUsdtPrice = JSON.parseObject(toUsdtStr).getDouble("last");
                //System.out.println(symbolPrice+"------"+toUsdtPrice);
            }
            //System.out.println(str);
            changeParams(symbolPrice, toUsdtPrice, clientId, waveRatio, priceDigit, numDigit, symbol, totalNumUrl, tradeMax, tradeMin, startTradeUrl);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("------交易异常-----");
        }
    }

    /**
     * 预挂单方法
     * @Author: olw
     * @Date: 2020/1/17 0017 15:27
     * @param: [
     * symbolPrice, 当前交易对价格
     * toUsdtPrice, 交易对商品中折合usdt价格
     * clientId, 交易用户id
     * waveRatio, 价格波动比例（一般为当前交易对价格的万分之一）
     * priceDigit, 单价小数位
     * numDigit, 数量小数位
     * symbol, 交易对名称
     * totalNumUrl, 获取当前价格盘面挂单数量url
     * tradeMax, 最大限额（CNY）
     * tradeMin, 最小限额（CNY）
     * tradeUrl 交易请求url
     * ]
     * @return: void
    */
    public static void changeParams(Double symbolPrice, Double toUsdtPrice, Long clientId, Double waveRatio, Integer priceDigit, Integer numDigit, String symbol, String totalNumUrl, Double tradeMax, Double tradeMin, String tradeUrl) throws Exception {
        Double price = getWavePrice(symbolPrice, waveRatio, priceDigit);
        Integer tradeType = getTradeType();
        Double num = getTotalNum(clientId, tradeType, price, symbol, totalNumUrl);
        if (num == 0 || num == null) {
            num = getRandomNum(tradeMax, tradeMin, toUsdtPrice, numDigit);
        }
        //Double num = getRandomNum(tradeMax, tradeMin, toUsdtPrice, numDigit);
        System.out.println(symbol+"-----"+clientId+"-----"+num+"-----"+price+"-------"+tradeType);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clientId", clientId);
        map.put("price", price);
        map.put("num", num);
        map.put("tradeType", tradeType);
        map.put("symbol", symbol);
        // TODO: 2020/1/15 0015 测试环境不开启
        saveTrade(map, tradeUrl);
    }

    /**
     * 开始交易
     * @Author: olw
     * @Date: 2020/1/13 0013 15:24
     * @param: [
     * map, 请求参数
     * tradeUrl 请求url
     * ]
     * @return: void
    */
    public static void saveTrade(Map<String, Object> map, String tradeUrl) throws Exception {
        JSONObject params = new JSONObject();
        params.putAll(map);
        String code = HttpConnectionPoolUtil.post(tradeUrl, params);
        System.out.println(code);

    }

    /**
     * 获取当前单价盘面数量
     * @Author: olw
     * @Date: 2020/1/13 0013 15:22
     * @param: [
     * clientId, 挂单机器人id
     * tradeType, 挂单类型
     * price, 挂单价格
     * symbol, 交易对
     * totalNumUrl 请求url
     * ]
     * @return: java.lang.Double
    */
    public static Double getTotalNum(Long clientId, Integer tradeType, Double price, String symbol, String totalNumUrl) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clientId", clientId);
        map.put("price", price);
        map.put("tradeType", tradeType);
        map.put("symbol", symbol);
        JSONObject params = new JSONObject();
        params.putAll(map);
        String str = HttpConnectionPoolUtil.post(totalNumUrl, params);
        if (str != null) {
            return Double.parseDouble(str);
        }
        return 0D;
    }

    /**
     * 获取随机挂单是数量
     * @Author: olw
     * @Date: 2020/1/17 0017 15:23
     * @param: [
     * moneyMax, 最大限额（CNY）
     * moneyMin, 最小限额（CNY）
     * toUsdtPrice, 当前币种价格（USDT）
     * numDigit, 数量保留小数位
     * ]
     * @return: java.lang.Double
    */
    public static Double getRandomNum(Double moneyMax, Double moneyMin, Double toUsdtPrice, Integer numDigit) {
        Double num = null;
        try {
            num = MathUtils.getRandom(MathUtils.chu(moneyMin, MathUtils.cheng(toUsdtPrice, 7D), 8), MathUtils.chu(moneyMax,MathUtils.cheng(toUsdtPrice, 7D), 8), numDigit);
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        }
        return num;
    }

   /**
    * 获取波动价格
    * @Author: olw
    * @Date: 2020/1/17 0017 15:20
    * @param: [oldPrice, waveRatio, priceDigit] 原始价格，波动比例，价格位数
    * @return: java.lang.Double
   */
    public static Double getWavePrice(Double oldPrice, Double waveRatio, Integer priceDigit) {
        Double waveNum = MathUtils.cheng(oldPrice, waveRatio);
        return MathUtils.getRandom(MathUtils.jian(oldPrice, waveNum), MathUtils.jia(oldPrice, waveNum), priceDigit);
    }

    //返回交易类型 1.买 2.卖
    public static Integer getTradeType() {
        return Math.random() > 0.5 ? ThreadConstants.TRADET_YPE_BUY:ThreadConstants.TRADET_YPE_SELL;
    }
}
