package com.oliwen.util.thread;

/**
 * @author: olw
 * @date: 2019/12/31 0031 11:53
 * @description: 线程常量类
 */
public class ThreadConstants {

    /** 请求地址  **/
    public static final String BTC_USDT_URL = "https://www.okex.me/api/spot/v3/instruments/BTC-USDT/ticker";
    public static final String ETH_USDT_URL = "https://www.okex.me/api/spot/v3/instruments/ETH-USDT/ticker";
    public static final String EOS_USDT_URL = "https://www.okex.me/api/spot/v3/instruments/EOS-USDT/ticker";
    public static final String LTC_USDT_URL = "https://www.okex.me/api/spot/v3/instruments/LTC-USDT/ticker";
    public static final String BCH_USDT_URL = "https://www.okex.me/api/spot/v3/instruments/BCH-USDT/ticker";
    public static final String TRX_USDT_URL = "https://www.okex.me/api/spot/v3/instruments/TRX-USDT/ticker";
    public static final String XRP_USDT_URL = "https://www.okex.me/api/spot/v3/instruments/XRP-USDT/ticker";

    /** 交易对名称 **/
    public static final String BTC_USDT_SYMBOL = "btc_usdt";
    public static final String ETH_USDT_SYMBOL = "eth_usdt";
    public static final String EOS_USDT_SYMBOL = "eos_usdt";
    public static final String LTC_USDT_SYMBOL = "ltc_usdt";
    public static final String BCH_USDT_SYMBOL = "bch_usdt";
    public static final String TRX_USDT_SYMBOL = "trx_usdt";
    public static final String XRP_USDT_SYMBOL = "xrp_usdt";


    /** 交易数额折合人民币 **/
    public static final Double MAX_TRADE = 30D;
    public static final Double MIN_TRADE = 5D;

    /** 价格波动 **/
    //价格波动万分之一
    public static final Double RATIO = 0.00001D;

    /** 交易类型 **/
    public static final Integer TRADET_YPE_BUY = 1;
    public static final Integer TRADET_YPE_SELL = 2;


    /** 交易机器人状态  0、停止，1、运行、2、删除 **/
    public static final Integer STOP_STATUS = 0;
    public static final Integer START_STATUS = 1;
    public static final Integer DELETE_STATUS = 2;

}
