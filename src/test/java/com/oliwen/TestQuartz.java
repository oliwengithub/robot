package com.oliwen;

import com.alibaba.fastjson.JSONObject;
import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.pojo.QuartzGroup;
import com.oliwen.pojo.SystemMenu;
import com.oliwen.service.quartz.QuartzGroupService;
import com.oliwen.service.quartz.QuartzService;
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
public class TestQuartz {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private QuartzGroupService quartzGroupService;

    @Test
    public void test_01(){

        List<QuartzGroup> allQuartzGroup = quartzGroupService.getAllQuartzGroup();
        System.out.println(allQuartzGroup);

    }

    @Test
    public void test_02(){

        Page page = new Page(1, 10);
        List<QuartzGroup> pageData = quartzGroupService.queryQuartzGroupPageList(page);
        System.out.println(pageData);

    }

    @Test
    public void test_03(){

        Page page = new Page(1, 10);
        List<PageData> pageData = quartzService.queryQuartzPageList(page);
        System.out.println(pageData);

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
