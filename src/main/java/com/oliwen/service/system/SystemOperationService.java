package com.oliwen.service.system;

import com.oliwen.entity.Page;
import com.oliwen.mapper.SystemOperationMapper;
import com.oliwen.pojo.SystemOperation;
import com.oliwen.util.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemOperationService {

    private static final Logger logger = LoggerFactory.getLogger(SystemMenuService.class);
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<SystemOperation> querySystemOperationListPage(Page page){
        return sqlSessionTemplate.selectList("com.oliwen.data.SystemOperationMapper.querySystemOperationListPage",page);
    }


    @Async("asyncThreadPool")
    public void insertSystemOperation(SystemOperation operation){
        SqlSession sqlSession = null;
        try {

            sqlSession = sqlSessionFactory.openSession();
            SystemOperationMapper mapper = sqlSession.getMapper(SystemOperationMapper.class);
            mapper.insertSelective(operation);
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"插入操作信息",e,operation);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }

        }
    }
}
