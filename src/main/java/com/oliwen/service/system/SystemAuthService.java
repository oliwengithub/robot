package com.oliwen.service.system;

import com.oliwen.mapper.SystemAuthMapper;
import com.oliwen.pojo.SystemAuth;
import com.oliwen.pojo.SystemAuthExample;
import com.oliwen.util.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAuthService {

    private static final Logger logger = LoggerFactory.getLogger(SystemAuthService.class);
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public boolean insertUserAuth(SystemAuth systemAuth) {
        return sqlSessionTemplate.insert("com.oliwen.data.SystemAuthMapper.insertUserAuth",systemAuth) > 0;
    }

    public SystemAuth queryUserAuthById(int userId){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemAuthMapper mapper = sqlSession.getMapper(SystemAuthMapper.class);
            SystemAuthExample example = new SystemAuthExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<SystemAuth> auths = mapper.selectByExample(example);
            if(auths.size() > 0){
                return auths.get(0);
            }
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"根据用户ID查询权限信息",e,userId);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return null;
    }
}
