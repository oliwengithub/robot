package com.oliwen.service.trade;

import com.oliwen.entity.Page;
import com.oliwen.mapper.TradePlatformMapper;
import com.oliwen.pojo.TradePlatform;
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
public class TradePlatformService {

    private static final Logger logger = LoggerFactory.getLogger(TradePlatformService.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Resource
    private TradePlatformMapper tradePlatformMapper;

    public List<TradePlatform> queryTradePlatformListPage (Page page) {
        return sqlSessionTemplate.selectList("com.oliwen.data.TradePlatformMapper.queryTradePlatformListPage",page);
    }

    public boolean insert (TradePlatform tradePlatform) {
        try {
            tradePlatform.setCreateDate(new Date());
            return tradePlatformMapper.insert(tradePlatform) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger,"插入交易所信息",e,tradePlatform);
        }
        return false;
    }

    public boolean update (TradePlatform tradePlatform) {
        try {
            return tradePlatformMapper.updateByPrimaryKey(tradePlatform) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger,"修改交易所信息",e,tradePlatform);
        }
        return false;
    }

    public TradePlatform queryById (Integer id) {
        try {
            return tradePlatformMapper.selectByPrimaryKey(id);
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "根据id获取交易所信息", e, id);
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
     * 获取所有交易所列表
     * @Author: olw
     * @Date: 2020/1/7 0007 16:22
     * @return: java.util.List<TradePlatform>
     */
    public List<TradePlatform> getAllTradePlatform () {
        return sqlSessionTemplate.selectList("com.oliwen.data.TradePlatformMapper.getAllTradePlatform");
    }
}
