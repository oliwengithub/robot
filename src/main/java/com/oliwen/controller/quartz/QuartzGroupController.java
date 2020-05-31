package com.oliwen.controller.quartz;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.QuartzGroup;
import com.oliwen.service.quartz.QuartzGroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/system/quartz/group")
/**
 *
 * @author: olw
 * @date: 2020/5/24 0030 11:06
 * @description:  任务调度分组模块
 */
public class QuartzGroupController extends BaseController {

    @Resource
    private QuartzGroupService quartzGroupService;


    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/quartz/group/list");
        return mv;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageSize, Integer pageIndex){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex, pageSize);
        page.setPd(this.getPageData());
        page.setCheckTotal(true);
        List<QuartzGroup> queryQuartzList = quartzGroupService.queryQuartzGroupPageList(page);
        body.setData(queryQuartzList);
        body.setCount(page.getTotalResult());
        return body;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = getView("/system/quartz/group/add");
        return mv;
    }

    @RequestMapping("/insert")
    public ResultBody insert(QuartzGroup quartzGroup) {
        ResultBody body = new ResultBody();
        boolean flag = quartzGroupService.insert(quartzGroup);
        if (!flag) {
            return body.error("添加失败");
        }
        return body;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mv = getView("/system/quartz/group/edit");
        QuartzGroup quartzGroup = quartzGroupService.getQuartzGroupById(id);
        mv.addObject("group", quartzGroup);
        return mv;
    }

    @RequestMapping("/update")
    public ResultBody update(QuartzGroup quartzGroup) {
        ResultBody body = new ResultBody();
        boolean flag = quartzGroupService.update(quartzGroup);
        if(!flag) {
            return body.error("修改失败");
        }
        return body;
    }

    @RequestMapping("/status")
    public ResultBody updateStatus(Integer id, Integer status) {
        ResultBody body = new ResultBody();
        QuartzGroup quartzGroup = new QuartzGroup();
        quartzGroup.setId(id);
        quartzGroup.setStatus(status);
        boolean flag = quartzGroupService.update(quartzGroup);
        if (!flag) {
            body.error("操作失败");
        }
        return body;
    }

    /*@RequestMapping("/detail")
    public ModelAndView detail(Integer id) {
        ModelAndView mv = getView("/system/quartz/detail");
        PageData quartzDetail = quartzService.getQuartzDetailById(id);
        mv.addObject("quartzDetail", quartzDetail);
        return mv;
    }*/

}
