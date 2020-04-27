package com.oliwen.service.system;

import com.alibaba.fastjson.JSONObject;
import com.oliwen.mapper.SystemConfigMapper;
import com.oliwen.pojo.SystemConfig;
import com.oliwen.pojo.SystemConfigExample;
import com.oliwen.util.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class SystemConfigService {

    private static final Logger logger = LoggerFactory.getLogger(SystemConfigService.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public SystemConfig getConfigByCode(String code){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemConfigMapper mapper = sqlSession.getMapper(SystemConfigMapper.class);
            SystemConfigExample example = new SystemConfigExample();
            example.createCriteria().andCodeEqualTo(code);
            List<SystemConfig> systemConfigs = mapper.selectByExample(example);
            if(systemConfigs.size() > 0){
                return systemConfigs.get(0);
            }
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"根据code获取配置信息",e,code);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return null;
    }


    public Map<String, Object> getConfigObjectByCode(String code){
        SystemConfig config = getConfigByCode(code);
        if(config != null){
            return JSONObject.parseObject(config.getValue());
        }
        return new JSONObject(1);
    }

    public boolean updateConfigByCode(String code,String value){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemConfigMapper mapper = sqlSession.getMapper(SystemConfigMapper.class);
            SystemConfigExample example = new SystemConfigExample();
            example.createCriteria().andCodeEqualTo(code);
            SystemConfig config = new SystemConfig();
            config.setValue(value);
            return mapper.updateByExampleSelective(config,example) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"根据code修改配置信息",e,code,value);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
    }
}
