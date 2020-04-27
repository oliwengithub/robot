package com.oliwen.controller.trade;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.TradeConfig;
import com.oliwen.service.trade.TradeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/system/thread/config")
public class TradeConfigController extends BaseController {

    @Autowired
    private TradeConfigService tradeConfigService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/thread/config/list");
        return mv;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageSize, Integer pageIndex){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex, pageSize);
        page.setPd(this.getPageData());
        page.setCheckTotal(true);
        List<TradeConfig> tradeConfigList = tradeConfigService.queryTradeConfigListPage(page);
        body.setData(tradeConfigList);
        body.setCount(page.getTotalResult());
        return body;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = getView("/system/thread/config/add");
        return mv;
    }

    @RequestMapping("/insert")
    public ResultBody insertThread(TradeConfig tradeConfig) {
        ResultBody body = new ResultBody();
        boolean flag = tradeConfigService.insert(tradeConfig);
        if (!flag) {
            return body.error("添加失败");
        }
        return body;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mv = getView("/system/thread/config/edit");
        TradeConfig tradeConfig = tradeConfigService.queryById(id);
        mv.addObject("tradeConfig", tradeConfig);
        return mv;
    }

    @RequestMapping("/update")
    public ResultBody updateThread(TradeConfig tradeConfig) {
        ResultBody body = new ResultBody();
        //TradeThread old = tradeConfigService.getTradeThreadById(tradeThread.getId());
        /*if (old.getStatus() == 1) {
            return body.error("正在运行中，不可修改");
        }*/
        boolean flag = tradeConfigService.update(tradeConfig);
        if(!flag) {
            return body.error("修改失败");
        }
        return body;
    }

    @RequestMapping("/delete")
    public ResultBody deleteThread(Integer id) {
        ResultBody body = new ResultBody();
        TradeConfig tradeConfig = new TradeConfig();
        tradeConfig.setId(id);
        boolean flag = tradeConfigService.update(tradeConfig);
        if (!flag) {
            body.error("删除失败");
        }
        return body;
    }

}
