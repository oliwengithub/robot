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
 * @date: 2020/4/30 0030 11:06
 * @description:  任务调度配置模块
 */
public class QuartzController extends BaseController {

    @Resource
    private QuartzService quartzService;

    @Resource
    private QuartzGroupService quartzGroupService;


    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/quartz/list");
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
        ModelAndView mv = getView("/system/quartz/list");
        return mv;
    }

    @RequestMapping("/insert")
    public ResultBody insert(Quartz quartz) {
        ResultBody body = new ResultBody();
        boolean flag = quartzService.insert(quartz);
        if (!flag) {
            return body.error("添加失败");
        }
        return body;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mv = getView("/system/quartz/edit");
        Quartz quartz = quartzService.getQuartzById(id);
        List<QuartzGroup> quartzGroups = quartzGroupService.getAllQuartzGroup();
        mv.addObject("quartz", quartz);
        mv.addObject("quartzGroups", quartzGroups);
        return mv;
    }

    @RequestMapping("/update")
    public ResultBody update(Quartz quartz) {
        ResultBody body = new ResultBody();
        Quartz old = quartzService.getQuartzById(quartz.getId());
        if (old.getStatus() == 1) {
            return body.error("正在运行中，不可修改");
        }
        boolean flag = quartzService.update(quartz);
        if(!flag) {
            return body.error("修改失败");
        }
        return body;
    }

    @RequestMapping("/delete")
    public ResultBody delete(Integer id) {
        ResultBody body = new ResultBody();
        boolean flag = quartzService.delete(id);
        if (!flag) {
            body.error("删除失败");
        }
        return body;
    }

    @RequestMapping("/status")
    public ResultBody updateStatus(Integer id, Integer status) {
        ResultBody body = new ResultBody();
        Quartz quartz = new Quartz();
        quartz.setId(id);
        quartz.setStatus(status);
        boolean flag = quartzService.update(quartz);
        if (!flag) {
            body.error("操作失败");
        }
        return body;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(Integer id) {
        ModelAndView mv = getView("/system/quartz/detail");
        PageData quartzDetail = quartzService.getQuartzDetailById(id);
        mv.addObject("quartzDetail", quartzDetail);
        return mv;
    }

    /*@RequestMapping("/start")
    public ResultBody startquartz(quartzData quartzData) {
        ResultBody body = new ResultBody();
        if (quartzData.getStatus() == quartzConstants.START_STATUS && quartzService.getScheduledFuture(quartzData.getSymbol(), quartzData.getTradeName()) != null) {
            return body.error("当前交易线程已启动");
        }
        quartzService.start(quartzData);
        Tradequartz tradequartz = new Tradequartz();
        tradequartz.setId(quartzData.getId());
        tradequartz.setStatus(quartzConstants.START_STATUS);
        tradequartz.setStartTime(new Date());
        tradequartzService.update(tradequartz);
        return body;
    }

    @RequestMapping("/stop")
    public ResultBody stopquartz(quartzData quartzData) {
        ResultBody body = new ResultBody();
        if (quartzService.getScheduledFuture(quartzData.getSymbol(), quartzData.getTradeName()) == null && quartzData.getStatus() == quartzConstants.STOP_STATUS) {
            return body.error("当前交易线程已停止");
        }
        quartzService.stop(quartzData);
        Tradequartz tradequartz = new Tradequartz();
        tradequartz.setId(quartzData.getId());
        tradequartz.setStatus(quartzConstants.STOP_STATUS);
        tradequartz.setStopTime(new Date());
        tradequartzService.update(tradequartz);
        return body;
    }

    @RequestMapping("/oneKeyStart")
    public ResultBody oneKeyStart(String quartzArray) {
        ResultBody body = new ResultBody();
        List<quartzData> tradequartzs = JSONObject.parseArray(quartzArray, quartzData.class);
        quartzService.oneKeyStart(tradequartzs);
        List<Integer> ids = new ArrayList<>();
        tradequartzs.forEach(quartzData ->{
            ids.add(quartzData.getId());
        });
        //更新模板
        Tradequartz tradequartz = new Tradequartz();
        tradequartz.setStatus(quartzConstants.START_STATUS);
        tradequartz.setStartTime(new Date());
        tradequartzService.updateBatch(ids, tradequartz);
        return body;
    }

    @RequestMapping("/oneKeyStop")
    public ResultBody oneKeyStop(String quartzArray) {
        ResultBody body = new ResultBody();
        List<quartzData> tradequartzs = JSONObject.parseArray(quartzArray, quartzData.class);
        quartzService.oneKeyStop(tradequartzs);
        List<Integer> ids = new ArrayList<>();
        tradequartzs.forEach(quartzData ->{
            ids.add(quartzData.getId());
        });
        //更新模板
        Tradequartz tradequartz = new Tradequartz();
        tradequartz.setStatus(quartzConstants.STOP_STATUS);
        tradequartz.setStopTime(new Date());
        tradequartzService.updateBatch(ids, tradequartz);
        return body;
    }*/

}
