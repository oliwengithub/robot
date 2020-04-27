package com.oliwen.service.system;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.mapper.SystemMenuMapper;
import com.oliwen.pojo.SystemMenu;
import com.oliwen.pojo.SystemMenuExample;
import com.oliwen.util.Constants;
import com.oliwen.util.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SystemMenuService {

    private static final Logger logger = LoggerFactory.getLogger(SystemMenuService.class);
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource(name = "systemRoleService")
    private SystemRoleService systemRoleService;

    public List<SystemMenu> querySystemMenuListPage(Page page) {
        return sqlSessionTemplate.selectList("com.oliwen.data.SystemMenuMapper.querySystemMenuListPage",page);
    }

    /**
     * 根据菜单ID集合查询菜单列表
     * @param pd
     * @return
     */
    public List<SystemMenu> querySystemMenuByIds(PageData pd) {
        //TODO 增加缓存机制
        return sqlSessionTemplate.selectList("com.oliwen.data.SystemMenuMapper.querySystemMenuByIds",pd);
    }

    /**
     * 查询用户指定类型的菜单的树状菜单列表
     * @param userId
     * @param type
     * @return
     */
    public List<SystemMenu> queryUserTreeMenus(int userId,Integer type){
        List<SystemMenu> menus = queryUserMenus(userId,type);
        return getMenuTree(0,menus);
    }

    /**
     * 查询用户的所有菜单
     * @param userId
     * @return
     */
    public List<SystemMenu> queryUserMenus(int userId){
        return queryUserMenus(userId,null);
    }

    /**
     * 查询用户的指定类型的所有菜单
     * @param userId
     * @param type
     * @return
     */
    public List<SystemMenu> queryUserMenus(int userId,Integer type){
        List<SystemMenu> menus = new ArrayList<>(1);

        Set<String> menuIds = systemRoleService.queryUserRoleMenuIds(userId);
        if(menuIds.size() > 0){
            PageData pd = new PageData();
            pd.put("ids",menuIds);
            pd.put("type",type);
            menus = querySystemMenuByIds(pd);
        }
        return menus;
    }

    public SystemMenu querySystemMenuById(int id){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemMenuMapper mapper = sqlSession.getMapper(SystemMenuMapper.class);
            return mapper.selectByPrimaryKey(id);
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"根据ID查询菜单信息",e,id);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return null;
    }

    public boolean updateSystemMenu(SystemMenu menu){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemMenuMapper mapper = sqlSession.getMapper(SystemMenuMapper.class);
            return mapper.updateByPrimaryKeySelective(menu) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"修改菜单信息",e,menu);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
    }

    public boolean insertSystemMenu(SystemMenu menu){
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemMenuMapper mapper = sqlSession.getMapper(SystemMenuMapper.class);
            return mapper.insertSelective(menu) > 0;
        }catch (Exception e){
            ExceptionUtil.loggerError(logger,"插入菜单信息",e,menu);
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
    }

    /**
     * 根据类型，查询所有的系统菜单
     * @param type
     * @return
     */
    public List<SystemMenu> getAllSystemMenusByType(Integer type) {
        SqlSession sqlSession = null;
        List<SystemMenu> menus = new ArrayList<>();
        try {
            sqlSession = sqlSessionFactory.openSession();
            SystemMenuMapper mapper = sqlSession.getMapper(SystemMenuMapper.class);
            SystemMenuExample example = new SystemMenuExample();
            SystemMenuExample.Criteria criteria = example.createCriteria().andStatusEqualTo(0);
            if(type != null){
                criteria.andTypeEqualTo(type);
            }
            example.setOrderByClause("pid ASC,sort ASC");
            menus = mapper.selectByExample(example);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        {
            SystemMenu menu = new SystemMenu();
            menu.setId(0);
            menu.setName("根菜单");
            menu.setPid(-1);
            menus.add(menu);
        }
        return menus;
    }

    /**
     * 根据PID返回树形菜单
     * @Author: olw
     * @Date: 2019/12/31 0031 12:14
     * @param: [pId, menus]
     * @return: java.util.List<SystemMenu>
    */
    public List<SystemMenu> getMenuTree(int pId,List<SystemMenu> menus){
        List<SystemMenu> childrens = new ArrayList<>(10);
        Iterator<SystemMenu> it = menus.iterator();
        while(it.hasNext()){
            SystemMenu menu = it.next();
            if(menu.getPid() == pId){
                if(menu.getType() == Constants.URL_TYPE_MENU){
                    menu.setChildren(getMenuTree(menu.getId(),menus));
                }
                childrens.add(menu);
            }
        }
        return childrens;
    }
}
