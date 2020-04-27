package com.oliwen;

import com.alibaba.fastjson.JSONObject;
import com.oliwen.pojo.SystemMenu;
import com.oliwen.service.system.SystemMenuService;
import com.oliwen.service.system.SystemRoleService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRole {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemMenuService systemMenuService;

    @Test
    public void test_01(){
        List<SystemMenu> menus = systemMenuService.queryUserMenus(1);
        List<SystemMenu> treeMenus = getMenuTree(0,menus);
        System.out.println(JSONObject.toJSONString(treeMenus));

    }

    public List<SystemMenu> getMenuTree(int pId,List<SystemMenu> menus){
        List<SystemMenu> childrens = new ArrayList<>(10);
        Iterator<SystemMenu> it = menus.iterator();
        while(it.hasNext()){
            SystemMenu menu = it.next();
            if(menu.getPid() == pId){
                if(menu.getType() == 0){
                    menu.setChildren(getMenuTree(menu.getId(),menus));
                }
                childrens.add(menu);
            }
        }
        return childrens;
    }
}
