package com.oliwen.service.system;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.mapper.SystemRoleMapper;
import com.oliwen.pojo.SystemRole;
import com.oliwen.pojo.SystemRoleExample;
import com.oliwen.util.Constants;
import com.oliwen.util.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SystemRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SystemRoleService.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private SystemConfigService systemConfigService;

    public List<SystemRole> querySystemRoleListPage(Page page) {
        return sqlSessionTemplate.selectList("com.oliwen.data.SystemRoleMapper.querySystemRoleListPage",page);
    }

    public boolean cumulationRolesMenuId(Integer menuId) {
        return sqlSessionTemplate.update("com.oliwen.data.SystemRoleMapper.cumulationRolesMenuId",menuId) > 0;
    }

    /**
     * 查询用户所属角色下的所有菜单ID
     * @param userId
     * @return
     */
    public Set<String> queryUserRoleMenuIds(int userId){
        List<SystemRole> roles = queryUserRoles(userId);
        Set<String> menuIds = new HashSet<>(10);
        roles.forEach(role->{
            String ids = role.getMenuIds();
            if(ids != null && !"".equals(ids)){
                String []id = ids.split(",");
                menuIds.addAll(Arrays.asList(id));
            }
        });
        return menuIds;
    }

    public List<SystemRole> queryUserRoles(int userId) {
        return sqlSessionTemplate.selectList("com.oliwen.data.SystemRoleMapper.queryUserRoles",userId);
    }

    /**
     * 获取角色树
     * @author: olw
     * @Date: 2019/9/28 10:28
     * @param
     * @returns: java.util.List<PageData>
     */
    public List<PageData> queryAllRoleTree () {
        List<PageData> roleTree = this.getRoleTree(this.queryAllSystemRole());
        PageData pd = new PageData();
        pd.put("id", 0);
        pd.put("title",  systemConfigService.getConfigObjectByCode(Constants.SYSTEM_CONFIG_CODE_SYSTEM).get("customer"));
        pd.put("parentId", -1);
        pd.put("remark", "");

        roleTree.add(pd);

        return roleTree;
    }

    public List<SystemRole> queryAllSystemRole(){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemRoleMapper mapper = sqlSession.getMapper(SystemRoleMapper.class);
            SystemRoleExample example = new SystemRoleExample();
            //过滤了超级管理员
            example.createCriteria().andRoleStatusEqualTo(0).andRoleIdNotEqualTo(1);
            return mapper.selectByExample(example);
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"查询所有角色信息",e);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return null;
    }

    
    public SystemRole querySystemRoleById(int roleId){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemRoleMapper mapper = sqlSession.getMapper(SystemRoleMapper.class);
            return mapper.selectByPrimaryKey(roleId);
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"根据ID查询角色信息",e,roleId);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return null;
    }

    public boolean insertSystemRole(SystemRole role){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemRoleMapper mapper = sqlSession.getMapper(SystemRoleMapper.class);
            return mapper.insertSelective(role) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"插入角色信息",e,role);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
    }

    public boolean updateSystemRole(SystemRole role){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemRoleMapper mapper = sqlSession.getMapper(SystemRoleMapper.class);
            return mapper.updateByPrimaryKeySelective(role) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"修改角色信息",e,role);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
    }

    public boolean delete (int roleId) {
        try {
            SystemRole role = new SystemRole();
            role.setRoleId(roleId);
            role.setRoleStatus(Constants.SYSTEM_ROLE_STATUS_DELETE);
            return updateSystemRole(role);
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"删除角色信息",e,roleId);
        }
        return false;
    }

    /**
     * 返回角色树
     * @author: olw
     * @Date: 2019/9/28 10:23
     * @param roles
     * @returns: java.util.List<PageData>
     */
    public List<PageData> getRoleTree (List<SystemRole> roles) {
        List<PageData> pds = new ArrayList<>();
        for (SystemRole role : roles) {
            PageData pd = new PageData();
            pd.put("id", role.getRoleId());
            pd.put("title", role.getRoleName());
            pd.put("parentId", role.getPid());
            pd.put("remark", role.getRemark());
            pds.add(pd);
        }
        return pds;
    }
}
