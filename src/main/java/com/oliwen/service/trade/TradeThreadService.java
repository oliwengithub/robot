package com.oliwen.service.trade;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.mapper.TradeThreadMapper;
import com.oliwen.pojo.TradeThread;
import com.oliwen.pojo.TradeThreadExample;
import com.oliwen.util.ExceptionUtil;
import com.oliwen.util.thread.ThreadConstants;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TradeThreadService {

    private static final Logger logger = LoggerFactory.getLogger(TradeThreadService.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource
    private TradeThreadMapper tradeThreadMapper;

    /**
     * 获取所有的交易机器人
     * @Author: olw
     * @Date: 2019/12/31 0031 16:41
     * @param: [page]
     * @return: java.util.List<TradeThread>
    */
    public List<PageData> queryTradeThreadListPage (Page page) {
        return sqlSessionTemplate.selectList("com.oliwen.data.TradeThreadMapper.queryTradeThreadListPage", page);
    }

    public boolean insert (TradeThread tradeThread) {
        try {
            //没传默认设置1s
            if (tradeThread.getTaskCycle() == null || tradeThread.getTaskCycle() == 0) {
                tradeThread.setTaskCycle(1000);
            }
            tradeThread.setStatus(ThreadConstants.STOP_STATUS);
            tradeThread.setCreateTime(new Date());
            return tradeThreadMapper.insert(tradeThread) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger,"插入交易线程",e,tradeThread);
        }
        return false;
    }

    public boolean update (TradeThread tradeThread) {
        try {
            return tradeThreadMapper.updateByPrimaryKeySelective(tradeThread) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger,"修改交易线程",e,tradeThread);
        }
        return false;
    }

    public boolean delete (int id) {
        try {
            TradeThread tradeThread = new TradeThread();
            tradeThread.setId(id);
            tradeThread.setStatus(ThreadConstants.DELETE_STATUS);
            return tradeThreadMapper.updateByPrimaryKeySelective(tradeThread) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger,"删除交易线程",e,id);
        }
        return false;
    }

    public TradeThread getTradeThreadById (int id) {
        try {
            return tradeThreadMapper.selectByPrimaryKey(id);
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "根据id获取交易线程信息", e, id);
        }
        return null;
    }

    public boolean updateBatch(List<Integer> ids, TradeThread tradeThread) {
        try {
            TradeThreadExample example = new TradeThreadExample();
            example.createCriteria().andIdIn(ids);
            return tradeThreadMapper.updateByExampleSelective(tradeThread, example) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"批量更新交易线程信息", e, ids, tradeThread);
        }
        return false;
    }

    /**
     * 根据id获取所有参数
     * @Author: olw
     * @Date: 2020/1/8 0008 16:44
     * @param: [id]
     * @return: PageData
    */
    public PageData getTradeThreadConfigById (int id) {
        return sqlSessionTemplate.selectOne("com.oliwen.data.TradeThreadMapper.getTradeThreadConfigById", id);
    }
}
