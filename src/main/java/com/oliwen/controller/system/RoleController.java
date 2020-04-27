package com.oliwen.controller.system;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.ResultBody;
import com.oliwen.interceptor.OperationMapping;
import com.oliwen.interceptor.SecurityAnnotation;
import com.oliwen.pojo.SystemRole;
import com.oliwen.pojo.SystemUser;
import com.oliwen.service.system.SystemMenuService;
import com.oliwen.service.system.SystemRoleService;
import com.oliwen.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemMenuService systemMenuService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/role/list");
        SystemUser sessionUser = (SystemUser)getSessionUser();
        /*if(sessionUser != null){
            mv.addObject("tree", JSONObject.toJSONString(systemRoleService.queryAllRoleTree()));

            mv.addObject("menus",systemMenuService.queryUserTreeMenus(sessionUser.getUserId(),Constants.URL_TYPE_MENU));

        }*/
        return mv;
    }
    @SecurityAnnotation(auth = false)
    @RequestMapping("/role/menu")
    public ResultBody roleMenus(Integer roleId) {
        ResultBody body = new ResultBody();
        SystemRole role = systemRoleService.querySystemRoleById(roleId);
        body.setData(role);
        return body;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageIndex, Integer pageSize){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex,pageSize);
        page.setPd(this.getPageData());
        page.setCheckTotal(true);
        List<SystemRole> systemMenus = systemRoleService.querySystemRoleListPage(page);
        body.setData(systemMenus);
        body.setCount(page.getTotalResult());
        return body;
    }

    @OperationMapping(value = "/insert",description = "添加角色")
    public ResultBody insert(SystemRole role){
        ResultBody body = new ResultBody();
        SystemUser systemUser = (SystemUser)getSessionUser();
        if(systemUser == null){
            return body.error(Constants.CODE_NOT_USER);
        }
        role.setAdminId(systemUser.getUserId());
        role.setAdminName(systemUser.getUserName());
        role.setCreateTime(new Date());
        boolean status = systemRoleService.insertSystemRole(role);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @OperationMapping(value = "/edit", description = "编辑角色信息")
    public ResultBody edit(SystemRole role){
        ResultBody body = new ResultBody();
        boolean status = systemRoleService.updateSystemRole(role);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @OperationMapping(value = "/del", description = "删除角色")
    public ResultBody delete (int roleId) {
        ResultBody body = new ResultBody();
        boolean status = systemRoleService.delete(roleId);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @OperationMapping(value = "/update", description = "更新角色")
    public ResultBody update(SystemRole role){
        ResultBody body = new ResultBody();
        boolean status = systemRoleService.updateSystemRole(role);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @SecurityAnnotation(auth = false)
    @RequestMapping("/get/tree")
    public ResultBody getTree () {
        ResultBody body = new ResultBody();
        body.setData(systemRoleService.queryAllRoleTree());
        return body;
    }
}
