package com.oliwen.service.trade;

import com.oliwen.entity.Page;
import com.oliwen.mapper.TradeConfigMapper;
import com.oliwen.pojo.TradeConfig;
import com.oliwen.util.ExceptionUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TradeConfigService {

    private static final Logger logger = LoggerFactory.getLogger(TradeConfigService.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Resource
    private TradeConfigMapper tradeConfigMapper;

    public List<TradeConfig> queryTradeConfigListPage (Page page) {
        return sqlSessionTemplate.selectList("com.oliwen.data.TradeConfigMapper.queryTradeConfigListPage", page);
    }

    public boolean insert (TradeConfig tradeConfig) {
        try {
            tradeConfig.setCreateDate(new Date());
            return tradeConfigMapper.insert(tradeConfig) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger,"插入交易线程配置",e,tradeConfig);
        }
        return false;
    }

    public boolean update (TradeConfig tradeConfig) {
        try {
            return tradeConfigMapper.updateByPrimaryKey(tradeConfig) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger,"修改交易线程配置",e,tradeConfig);
        }
        return false;
    }

    public TradeConfig queryById (Integer id) {
        try {
            return tradeConfigMapper.selectByPrimaryKey(id);
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "根据id获取交易线程配置", e, id);
        }
        return null;
    }

   /* public boolean delete (Integer id) {
        try {
            return tradeConfigMapper.deleteByPrimaryKey(id) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "根据删除交易线程配置", e, id);
        }
        return false;
    }*/

    /**
     * 获取所有线程配置列表
     * @Author: olw
     * @Date: 2020/1/7 0007 16:22
     * @return: java.util.List<TradeConfig>
    */
    public List<TradeConfig> getAllTradeConfig () {
        return sqlSessionTemplate.selectList("com.oliwen.data.TradeConfigMapper.getAllTradeConfig");
    }
}
