package com.oliwen.controller.system;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.ResultBody;
import com.oliwen.interceptor.OperationMapping;
import com.oliwen.interceptor.SecurityAnnotation;
import com.oliwen.pojo.SystemMenu;
import com.oliwen.pojo.SystemUser;
import com.oliwen.service.system.SystemMenuService;
import com.oliwen.service.system.SystemRoleService;
import com.oliwen.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private SystemMenuService systemMenuService;

    @Autowired
    private SystemRoleService systemRoleService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/menu/list");
        return mv;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageIndex, Integer pageSize){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex,pageSize);
        page.setPd(this.getPageData());
        page.setCheckTotal(true);
        List<SystemMenu> systemMenus = systemMenuService.querySystemMenuListPage(page);
        body.setData(systemMenus);
        body.setCount(page.getTotalResult());
        return body;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = getView("/system/menu/add");
        return mv;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(int id){
        ModelAndView mv = getView("/system/menu/edit");
        SystemMenu menu = systemMenuService.querySystemMenuById(id);
        mv.addObject("menu",menu);
        return mv;
    }

    @OperationMapping(value = "/update",description = "更新菜单")
    public ResultBody update(SystemMenu menu){
        ResultBody body = new ResultBody();
        boolean status = systemMenuService.updateSystemMenu(menu);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @OperationMapping(value = "/insert",description = "添加菜单")
    public ResultBody insert(SystemMenu menu){
        ResultBody body = new ResultBody();
        SystemUser sessionUser = (SystemUser)getSessionUser();
        if(sessionUser == null){
            return body.error(Constants.CODE_NOT_USER);
        }
        menu.setAdminId(sessionUser.getUserId());
        menu.setAdminName(sessionUser.getUserName());
        menu.setCreateTime(new Date());
        boolean status = systemMenuService.insertSystemMenu(menu);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        status = systemRoleService.cumulationRolesMenuId(menu.getId());
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @RequestMapping("/query/all")
    @SecurityAnnotation(auth = false)
    public ResultBody queryAll(Integer type){
        Map<String,Object> data = new HashMap<>(3);
        ResultBody body = new ResultBody();
        List<SystemMenu> menus = systemMenuService.getAllSystemMenusByType(type);
        List<Map<String,String>> list = new ArrayList<>();
        menus.forEach(menu->{
            Map<String,String> map = new HashMap<>();
            map.put("id",menu.getId()+"");
            String name = menu.getName();
            if(menu.getType() != null && menu.getType() != Constants.URL_TYPE_MENU){
                if(menu.getType() == Constants.URL_TYPE_BUTTON){
                    name +=" - <font color='#1E9FFF'>按钮</font>";
                }else{
                    name +=" - <font color='#FFB800'>操作</font>";
                }
            }
            map.put("name",name);
            map.put("pid",menu.getPid()+"");
            list.add(map);
        });
        data.put("list",list);
        body.setData(data);
        return body;
    }
}
