//package com.uniclo;
//
//import com.alibaba.fastjson.JSONObject;
//import SystemMenuMapper;
//import SystemMenu;
//import SystemMenuExample;
//import SystemRole;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class ApplicationTests {
//
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//
//    @Test
//    public void test1() {
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = sqlSessionFactory.openSession();
//            SystemMenuMapper mapper = sqlSession.getMapper(SystemMenuMapper.class);
//            SystemMenuExample example = new SystemMenuExample();
//            example.createCriteria().andStatusEqualTo(0);
//            example.setOrderByClause("pid ASC,sort ASC");
//            List<SystemMenu> menus = mapper.selectByExample(example);
//
//            /*Iterator<SystemMenu> it = menus.iterator();
//            while(it.hasNext()){
//                SystemMenu x = it.next();
//                if(x.equals("del")){
//                    it.remove();
//                }
//            }*/
//
//            System.out.println(JSONObject.toJSONString(getChildrens(0,menus)));
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(sqlSession != null){
//                sqlSession.close();
//            }
//        }
//    }
//
//    public List<SystemMenu> getChildrens(int pId,List<SystemMenu> menus){
//        List<SystemMenu> childrens = new ArrayList<>(10);
//        Iterator<SystemMenu> it = menus.iterator();
//        while(it.hasNext()){
//            SystemMenu menu = it.next();
//            if(menu.getPid() == pId){
//                SystemMenu children = menu;
//                children.setChildren(getChildrens(menu.getId(),menus));
//                childrens.add(children);
//            }
//        }
//        return childrens;
//    }
//
//    @Test
//    public void test2() {
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = sqlSessionFactory.openSession();
//            SystemMenuMapper mapper = sqlSession.getMapper(SystemMenuMapper.class);
//            List<SystemMenu> menus = getMenusByFId(0,mapper);
//            System.out.println(JSONObject.toJSONString(menus));
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(sqlSession != null){
//                sqlSession.close();
//            }
//        }
//    }
//
//    public List<SystemMenu> getMenusByFId(int fMenuId,SystemMenuMapper mapper){
//        SystemMenuExample example = new SystemMenuExample();
//        example.createCriteria().andPidEqualTo(fMenuId);
//        example.setOrderByClause("menu_sort ASC");
//        System.out.println("查询sql");
//        List<SystemMenu> menus = mapper.selectByExample(example);
//        if(menus.size() > 0){
//            for (int i = 0,len = menus.size(); i < len; i++) {
//                SystemMenu menu = menus.get(i);
//                List<SystemMenu> sons = getMenusByFId(menu.getId(),mapper);
//                if(sons != null && sons.size() > 0){
////                    menu.setMenus(sons);
//                    menus.set(i,menu);
//                }
//            }
//            return menus;
//        }
//        return menus;
//    }
//
//}
//
