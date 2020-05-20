package com.oliwen.service.quartz;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.mapper.SystemQuartzMapper;
import com.oliwen.pojo.SystemAuth;
import com.oliwen.service.system.SystemAuthService;
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
    private SystemQuartzMapper systemQuartzMapper;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;


    public List<PageData> queryQuartzPageList (Page page) {
        return null;
    }
}
