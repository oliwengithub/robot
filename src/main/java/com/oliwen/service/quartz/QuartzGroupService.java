package com.oliwen.service.quartz;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.mapper.QuartzGroupMapper;
import com.oliwen.pojo.QuartzGroup;
import com.oliwen.pojo.QuartzGroupExample;
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
public class QuartzGroupService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzGroupService.class);

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource
    private QuartzGroupMapper quartzGroupMapper;


    public List<PageData> queryQuartzGroupPageList (Page page) {
        return sqlSessionTemplate.selectList("com.oliwen.data.QuartzGroupMapper.queryQuartzGroupPageList", page);
    }

    public List<QuartzGroup> getAllQuartzGroup () {
        return sqlSessionTemplate.selectList("com.oliwen.data.QuartzGroupMapper.getAllQuartzGroup");
    }

    public boolean insert (QuartzGroup quartzGroup) {

        try {
            return quartzGroupMapper.insertSelective(quartzGroup) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "添加任务分组", e);
        }
        return false;
    }

    /**
     *  根据id获取任务分组
     * @author: olw
     * @Date: 2020/5/30 0030 17:10
     * @param id
     * @returns:
    */
    public QuartzGroup getQuartzGroupById (Integer id) {
       return  quartzGroupMapper.selectByPrimaryKey(id);
    }


    public boolean update (QuartzGroup quartzGroup) {
        try {
            return quartzGroupMapper.updateByPrimaryKeySelective(quartzGroup) > 0;
        }catch (Exception e) {
            ExceptionUtil.loggerError(logger, "更新任务分组", e);
        }
        return false;
    }
}
