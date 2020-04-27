package com.oliwen.service.system;

import com.oliwen.entity.Page;
import com.oliwen.mapper.SystemAuthMapper;
import com.oliwen.mapper.SystemUserMapper;
import com.oliwen.pojo.SystemAuth;
import com.oliwen.pojo.SystemUser;
import com.oliwen.pojo.SystemUserExample;
import com.oliwen.util.Constants;
import com.oliwen.util.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class SystemUserService {

    private static final Logger logger = LoggerFactory.getLogger(SystemUserService.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<SystemUser> querySystemUserListPage(Page page){
        return sqlSessionTemplate.selectList("com.oliwen.data.SystemUserMapper.querySystemUserListPage",page);
    }

    public SystemUser login(String userName,String password){
        SqlSession sqlSession = null;
        SystemUser systemUser = null;
        try {
            sqlSession = sqlSessionFactory.openSession(true);
            SystemUserMapper mapper = sqlSession.getMapper(SystemUserMapper.class);

            SystemUserExample example = new SystemUserExample();
            example.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(password)
                    .andStatusEqualTo(Constants.SYSTEM_USER_STATUS_NORMAL);
            List<SystemUser> systemUsers = mapper.selectByExample(example);
            if(systemUsers.size() > 0){
                systemUser = systemUsers.get(0);
            }
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"用户登录",e,userName,password);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return systemUser;
    }

    public boolean checkUserName(String userName){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemUserMapper mapper = sqlSession.getMapper(SystemUserMapper.class);
            SystemUserExample example = new SystemUserExample();
            example.createCriteria().andUserNameEqualTo(userName);
            return mapper.selectByExample(example).size() > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"检查用户名是否存在",e,userName);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return true;
    }

    public SystemUser querySystemUserById(int userId){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemUserMapper mapper = sqlSession.getMapper(SystemUserMapper.class);
            return mapper.selectByPrimaryKey(userId);
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"根据user_id查询用户信息",e,userId);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return null;
    }

    public boolean insertUser(SystemUser systemUser){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemUserMapper mapper = sqlSession.getMapper(SystemUserMapper.class);
            return mapper.insertSelective(systemUser) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"添加用户信息",e,systemUser);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
    }

    public boolean updateUser(int userId, String userName, String password, String nickName, String avatar, Integer sex, String mobile, String email, String remark, Integer status){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemUserMapper mapper = sqlSession.getMapper(SystemUserMapper.class);
            SystemUser user = new SystemUser(userId,userName,password,nickName,avatar,sex,mobile,email,remark,status,null,null,null);
            return mapper.updateByPrimaryKeySelective(user) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"根据user_id修改用户信息",e,userId);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
    }

    public boolean updateUserPassword(int userId, String password){
        return updateUser(userId,null,password,null,null,null,null,null,null,null);
    }
    public boolean updateUserStatus(int userId, int status){
        return updateUser(userId,null,null,null,null,null,null,null,null,status);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertUserAndAuth (SystemUser systemUser, String roleIds) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemUserMapper systemUserMapper = sqlSession.getMapper(SystemUserMapper.class);
            systemUserMapper.insertSelective(systemUser);

            SystemAuthMapper systemAuthMapper = sqlSession.getMapper(SystemAuthMapper.class);
            SystemAuth systemAuth = new SystemAuth();
            systemAuth.setUserId(systemUser.getUserId());
            systemAuth.setRoleIds(roleIds);
            systemAuth.setAdminId(systemUser.getAdminId());
            systemAuth.setAdminName(systemUser.getAdminName());
            systemAuth.setCreateTime(new Date());
            return systemAuthMapper.insertSelective(systemAuth) > 0;
        } catch (Exception e) {
            ExceptionUtil.loggerError(logger, "添加用户信息", e, systemUser);
            //主动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return false;
    }
}
