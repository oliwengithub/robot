package com.oliwen.service.quartz;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.mapper.QuartzMapper;
import com.oliwen.pojo.Quartz;
import com.oliwen.util.ExceptionUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
/**
 *
 * @author: olw
 * @date: 2020/4/30 0030 14:11
 * @description:  quartz 业务层
 */
public class QuartzService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzService.class);

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource
    private QuartzMapper quartzMapper;

    public List<PageData> queryQuartzPageList (Page page) {
        return sqlSessionTemplate.selectList("com.oliwen.data.QuartzMapper.queryQuartzPageList", page);
    }

    public boolean insert (Quartz quartz) {
        try {
            return quartzMapper.insertSelective(quartz) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "插入任务", e, quartz);
        }
        return false;
    }

    /**
     * 根据id获取任务信息
     * @author: olw
     * @Date: 2020/5/30 0030 17:25
     * @param id
     * @returns: Quartz
    */
    public Quartz getQuartzById (Integer id) {
        return quartzMapper.selectByPrimaryKey(id);
    }

    public boolean update (Quartz quartz) {
        try {
            return quartzMapper.updateByPrimaryKey(quartz) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "更新任务信息", e, quartz);
        }
        return false;
    }

    public boolean delete (Integer id) {
        return false;
    }

    public PageData getQuartzDetailById (Integer id) {
        return null;
    }
}
