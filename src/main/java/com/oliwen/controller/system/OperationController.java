package com.oliwen.controller.system;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.SystemOperation;
import com.oliwen.service.system.SystemOperationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2019/1/13 11:02 AM
 * @description:  系统操作日志
 */
@RestController
@RequestMapping("/system/operation")
public class OperationController extends BaseController {

    @Resource(name = "systemOperationService")
    private SystemOperationService systemOperationService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/operation/list");
        return mv;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageIndex, Integer pageSize){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex,pageSize);
        page.setPd(this.getPageData());
        page.setCheckTotal(true);
        List<SystemOperation> systemOperations = systemOperationService.querySystemOperationListPage(page);
        body.setData(systemOperations);
        body.setCount(page.getTotalResult());
        return body;
    }
}
