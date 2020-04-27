package com.oliwen.controller.trade;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.TradePlatform;
import com.oliwen.service.trade.TradePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/system/thread/platform")
public class TradePlatformController extends BaseController {

    @Autowired
    private TradePlatformService tradePlatformService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/thread/platform/list");
        return mv;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageSize, Integer pageIndex){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex, pageSize);
        page.setPd(this.getPageData());
        page.setCheckTotal(true);
        List<TradePlatform> tradePlatformList = tradePlatformService.queryTradePlatformListPage(page);
        body.setData(tradePlatformList);
        body.setCount(page.getTotalResult());
        return body;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = getView("/system/thread/platform/add");
        return mv;
    }

    @RequestMapping("/insert")
    public ResultBody insertThread(TradePlatform tradePlatform) {
        ResultBody body = new ResultBody();
        boolean flag = tradePlatformService.insert(tradePlatform);
        if (!flag) {
            return body.error("添加失败");
        }
        return body;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mv = getView("/system/thread/platform/edit");
        TradePlatform tradePlatform = tradePlatformService.queryById(id);
        mv.addObject("tradePlatform", tradePlatform);
        return mv;
    }

    @RequestMapping("/update")
    public ResultBody updateThread(TradePlatform tradePlatform) {
        ResultBody body = new ResultBody();
        //TradeThread old = tradeConfigService.getTradeThreadById(tradeThread.getId());
        /*if (old.getStatus() == 1) {
            return body.error("正在运行中，不可修改");
        }*/
        boolean flag = tradePlatformService.update(tradePlatform);
        if(!flag) {
            return body.error("修改失败");
        }
        return body;
    }

    @RequestMapping("/delete")
    public ResultBody deleteThread(Integer id) {
        ResultBody body = new ResultBody();
        TradePlatform tradePlatform = new TradePlatform();
        tradePlatform.setId(id);
        boolean flag = tradePlatformService.update(tradePlatform);
        if (!flag) {
            body.error("删除失败");
        }
        return body;
    }

}
