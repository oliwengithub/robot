package com.oliwen.controller.quartz;

import com.alibaba.fastjson.JSONObject;
import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.TradeConfig;
import com.oliwen.pojo.TradePlatform;
import com.oliwen.pojo.TradeThread;
import com.oliwen.service.quartz.QuartzService;
import com.oliwen.util.thread.ThreadConstants;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


@RestController
@RequestMapping("/system/quartz")
/**
 *
 * @author: olw
 * @date: 2020/4/30 0030 11:06
 * @description:  任务调度quartz模块
 */
public class QuartzController extends BaseController {

    @Resource
    private QuartzService quartzService;

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/thread/list");
        return mv;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageSize, Integer pageIndex){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex, pageSize);
        page.setPd(this.getPageData());
        page.setCheckTotal(true);
        List<PageData> queryQuartzList = quartzService.queryQuartzPageList(page);
        body.setData(queryQuartzList);
        body.setCount(page.getTotalResult());
        return body;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        LOGGER.info("新增任务");
        ModelAndView mv = getView("/system/quartz/add");
        List<TradePlatform> tradePlatforms = tradePlatformService.getAllTradePlatform();
        List<TradeConfig> tradeConfigs = tradeConfigService.getAllTradeConfig();
        mv.addObject("tradePlatforms", tradePlatforms);
        mv.addObject("tradeConfigs", tradeConfigs);
        return mv;
    }

    @RequestMapping("/insert")
    public ResultBody insertThread(TradeThread tradeThread) {
        ResultBody body = new ResultBody();
        boolean flag = tradeThreadService.insert(tradeThread);
        if (!flag) {
            return body.error("添加失败");
        }
        return body;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mv = getView("/system/thread/edit");
        TradeThread tradeThread = tradeThreadService.getTradeThreadById(id);
        List<TradeConfig> tradeConfigs = tradeConfigService.getAllTradeConfig();
        List<TradePlatform> tradePlatforms = tradePlatformService.getAllTradePlatform();
        mv.addObject("tradeThread", tradeThread);
        mv.addObject("tradePlatforms", tradePlatforms);
        mv.addObject("tradeConfigs", tradeConfigs);
        return mv;
    }

    @RequestMapping("/update")
    public ResultBody updateThread(TradeThread tradeThread) {
        ResultBody body = new ResultBody();
        TradeThread old = tradeThreadService.getTradeThreadById(tradeThread.getId());
        if (old.getStatus() == 1) {
            return body.error("正在运行中，不可修改");
        }
        boolean flag = tradeThreadService.update(tradeThread);
        if(!flag) {
            return body.error("修改失败");
        }
        return body;
    }

    @RequestMapping("/delete")
    public ResultBody deleteThread(Integer id) {
        ResultBody body = new ResultBody();
        boolean flag = tradeThreadService.delete(id);
        if (!flag) {
            body.error("删除失败");
        }
        return body;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(Integer id) {
        ModelAndView mv = getView("/system/thread/detail");
        PageData tradeThreadDetail = tradeThreadService.getTradeThreadConfigById(id);
        mv.addObject("tradeThreadDetail", tradeThreadDetail);
        return mv;
    }

    @RequestMapping("/start")
    public ResultBody startThread(ThreadData threadData) {
        ResultBody body = new ResultBody();
        if (threadData.getStatus() == ThreadConstants.START_STATUS && threadService.getScheduledFuture(threadData.getSymbol(), threadData.getTradeName()) != null) {
            return body.error("当前交易线程已启动");
        }
        threadService.start(threadData);
        TradeThread tradeThread = new TradeThread();
        tradeThread.setId(threadData.getId());
        tradeThread.setStatus(ThreadConstants.START_STATUS);
        tradeThread.setStartTime(new Date());
        tradeThreadService.update(tradeThread);
        return body;
    }

    @RequestMapping("/stop")
    public ResultBody stopThread(ThreadData threadData) {
        ResultBody body = new ResultBody();
        if (threadService.getScheduledFuture(threadData.getSymbol(), threadData.getTradeName()) == null && threadData.getStatus() == ThreadConstants.STOP_STATUS) {
            return body.error("当前交易线程已停止");
        }
        threadService.stop(threadData);
        TradeThread tradeThread = new TradeThread();
        tradeThread.setId(threadData.getId());
        tradeThread.setStatus(ThreadConstants.STOP_STATUS);
        tradeThread.setStopTime(new Date());
        tradeThreadService.update(tradeThread);
        return body;
    }

    @RequestMapping("/oneKeyStart")
    public ResultBody oneKeyStart(String threadArray) {
        ResultBody body = new ResultBody();
        List<ThreadData> tradeThreads = JSONObject.parseArray(threadArray, ThreadData.class);
        threadService.oneKeyStart(tradeThreads);
        List<Integer> ids = new ArrayList<>();
        tradeThreads.forEach(threadData ->{
            ids.add(threadData.getId());
        });
        //更新模板
        TradeThread tradeThread = new TradeThread();
        tradeThread.setStatus(ThreadConstants.START_STATUS);
        tradeThread.setStartTime(new Date());
        tradeThreadService.updateBatch(ids, tradeThread);
        return body;
    }

    @RequestMapping("/oneKeyStop")
    public ResultBody oneKeyStop(String threadArray) {
        ResultBody body = new ResultBody();
        List<ThreadData> tradeThreads = JSONObject.parseArray(threadArray, ThreadData.class);
        threadService.oneKeyStop(tradeThreads);
        List<Integer> ids = new ArrayList<>();
        tradeThreads.forEach(threadData ->{
            ids.add(threadData.getId());
        });
        //更新模板
        TradeThread tradeThread = new TradeThread();
        tradeThread.setStatus(ThreadConstants.STOP_STATUS);
        tradeThread.setStopTime(new Date());
        tradeThreadService.updateBatch(ids, tradeThread);
        return body;
    }*/

}
