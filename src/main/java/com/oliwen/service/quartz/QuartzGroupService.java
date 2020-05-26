package com.oliwen.service.quartz;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.pojo.QuartzGroup;
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


    public List<PageData> queryQuartzPageList (Page page) {
        return null;
    }

    public List<QuartzGroup> getAllQuartzGroup () {
        return null;
    }

    public boolean insert (QuartzGroup quartzGroup) {
        return false;
    }

    public QuartzGroup getQuartGroupById (Integer id) {
        return null;
    }

    public QuartzGroup getQuartzGroupById (Integer id) {
        return null;
    }

    public boolean update (QuartzGroup quartzGroup) {
        return false;
    }
}
