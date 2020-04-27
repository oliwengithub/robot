package com.oliwen.service.thread;

import com.oliwen.entity.PageData;
import com.oliwen.entity.ThreadData;
import com.oliwen.service.trade.TradeThreadService;
import com.oliwen.thread.TradeTaskThread;
import com.oliwen.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadService.class);

    private static Map<String, Map<String, ScheduledFuture>> threadMap = new HashMap<>();

    @Resource(name = "scheduledExecutorService")
    private ScheduledExecutorService scheduledExecutorService;

    @Resource
    private TradeThreadService tradeThreadService;

    //开启
    public boolean start (ThreadData threadData) {
        return start(threadData.getId(), threadData.getSymbol(), threadData.getTradeName(), threadData.getTaskCycle());
    }

    /**
     *
     * @Author: olw
     * @Date: 2020/1/15 0015 12:07
     * @param: [
     * id, 配置id
     * symbol, 交易对名称
     * tradeName, 交易所名称
     * taskCycle 执行周期
     * ]
     * @return: boolean
    */
    public boolean start (Integer id, String symbol, String tradeName, Integer taskCycle) {
        try {
            //先停止再开启 防止一个任务被多次开启导致无法关闭
            stop(symbol, tradeName);
            Map<String, ScheduledFuture> scheduledFuture = new HashMap<>();
            scheduledFuture.put(symbol, scheduledExecutorService.scheduleAtFixedRate(getTradeTaskThread(id), 0, taskCycle, TimeUnit.MILLISECONDS));
            if (getScheduledFutureMap(tradeName) != null && getScheduledFutureMap(tradeName).size() > 0) {
                threadMap.get(tradeName).putAll(scheduledFuture);
            } else {
                threadMap.put(tradeName, scheduledFuture);
            }
            return getScheduledFuture(symbol, tradeName) != null;
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionUtil.loggerError(logger, "开启线程任务", e, id, symbol, tradeName);
        }
        return false;
    }

    //关闭
    public boolean stop (ThreadData threadData) {
        return stop(threadData.getSymbol(), threadData.getTradeName());
    }

    public boolean stop (String symbol, String tradeName) {
        try {
            ScheduledFuture scheduledFuture = getScheduledFuture(symbol, tradeName);
            if (scheduledFuture != null) {
                threadMap.get(tradeName).get(symbol).cancel(true);
                removeScheduledFuture(symbol, tradeName);
            }
            return getScheduledFuture(symbol, tradeName) == null;
        } catch (Exception e) {
            ExceptionUtil.loggerError(logger, "关闭线程任务", e, symbol, tradeName);
        }
        return false;
    }


    /**
     * 一键开启
     * @Author: olw
     * @Date: 2020/1/15 0015 13:59
     * @param: [tradeThreads]
     * @return: void
     */
    public void oneKeyStart (List<ThreadData> tradeThreads) {
        if (tradeThreads != null && tradeThreads.size() > 0) {
            tradeThreads.forEach(threadData -> {
                start(threadData);
            });
        }
    }

    /**
     * 一键关闭
     * @Author: olw
     * @Date: 2020/1/15 0015 11:40
     * @param: [tradeThreads]
     * @return: void
     */
    public void oneKeyStop (List<ThreadData> tradeThreads) {
        if (tradeThreads != null && tradeThreads.size() > 0) {
            tradeThreads.forEach(threadData -> {
                stop(threadData);
            });
        }
    }

    /**
     * 获取指定交易所交易对任务线程
     *
     * @Author: olw
     * @Date: 2020/1/13 0013 11:42
     * @param: [symbol, tradeName]
     * @return: java.util.concurrent.ScheduledFuture
     */
    public ScheduledFuture getScheduledFuture (String symbol, String tradeName) {
        ScheduledFuture scheduledFuture = null;
        try {
            if (getScheduledFutureMap(tradeName) != null) {
                scheduledFuture = getScheduledFutureMap(tradeName).get(symbol);
            }
        } catch (Exception e) {
            ExceptionUtil.loggerError(logger, "根据交易所名称和交易对名称获取当前任务线程", e, symbol, tradeName);
        }
        return scheduledFuture;
    }

    /**
     * 清除map集合中的交易任务
     *
     * @Author: olw
     * @Date: 2020/1/13 0013 11:34
     * @param: [symbol, tradeName]  交易对, 交易所
     * @return: java.util.concurrent.ScheduledFuture
     */
    public ScheduledFuture removeScheduledFuture (String symbol, String tradeName) throws Exception {
        return threadMap.get(tradeName).remove(symbol);
    }

    /**
     * 获取交易所全部交易map集合
     *
     * @Author: olw
     * @Date: 2020/1/13 0013 11:35
     * @param: [tradeName]  交易所
     * @return: java.util.Map<java.lang.String, java.util.concurrent.ScheduledFuture>
     */
    public Map<String, ScheduledFuture> getScheduledFutureMap (String tradeName) {
        return threadMap.get(tradeName);
    }

    public TradeTaskThread getTradeTaskThread (Integer id) {
        PageData pd = tradeThreadService.getTradeThreadConfigById(id);
        return getTradeTaskThread(pd);
    }

    /**
     * 绑定交易线程任务
     * @Author: olw
     * @Date: 2020/1/15 0015 11:53
     * @param: [pd] 参数集合
     * @return: TradeTaskThread
    */
    public TradeTaskThread getTradeTaskThread (PageData pd) {
        String totalNumUrl = pd.getString("total_num_url");
        String startTradeUrl = pd.getString("trade_url");
        String sdkPriceUrl = pd.getString("sdk_price_url");
        String sdkToUsdtUrl = pd.getString("sdk_to_usdt_url");
        Long clientId = Long.parseLong(pd.getString("client_id"));
        Double waveRatio = Double.parseDouble(pd.getString("wave_ratio"));
        Integer priceDigit = Integer.parseInt(pd.getString("price_digit"));
        Integer numDigit = Integer.parseInt(pd.getString("num_digit"));
        String symbol = pd.getString("symbol");
        Double tradeMax = Double.parseDouble(pd.getString("trade_max"));
        Double tradeMin = Double.parseDouble(pd.getString("trade_min"));
        return new TradeTaskThread(totalNumUrl, startTradeUrl, sdkPriceUrl, sdkToUsdtUrl, clientId, waveRatio, priceDigit, numDigit, symbol, tradeMax, tradeMin);
    }
}
