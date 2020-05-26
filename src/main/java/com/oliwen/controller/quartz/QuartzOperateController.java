package com.oliwen.controller.quartz;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.Quartz;
import com.oliwen.pojo.QuartzGroup;
import com.oliwen.service.quartz.QuartzGroupService;
import com.oliwen.service.quartz.QuartzService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/system/quartz")
/**
 *
 * @author: olw
 * @date: 2020/5/26 0030 17:06
 * @description:  任务调度操控模块
 */
public class QuartzOperateController extends BaseController {

    @Resource
    private QuartzService quartzService;

    @Resource
    private QuartzGroupService quartzGroupService;

    /*@RequestMapping("/start")
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
