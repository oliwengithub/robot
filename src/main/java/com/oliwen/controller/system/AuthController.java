package com.oliwen.controller.system;

import com.oliwen.base.BaseController;
import com.oliwen.entity.ResultBody;
import com.oliwen.interceptor.OperationMapping;
import com.oliwen.pojo.SystemAuth;
import com.oliwen.pojo.SystemRole;
import com.oliwen.pojo.SystemUser;
import com.oliwen.service.system.SystemAuthService;
import com.oliwen.service.system.SystemRoleService;
import com.oliwen.util.Constants;
import com.oliwen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/system/auth")
public class AuthController extends BaseController {

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemAuthService systemAuthService;

    @RequestMapping()
    public ModelAndView auth(int userId){
        ModelAndView mv = getView("/system/user/auth");
        List<SystemRole> roles = systemRoleService.queryAllSystemRole();
        SystemAuth systemAuth = systemAuthService.queryUserAuthById(userId);

        List<String> roleIds = new ArrayList<>(1);
        List<Map<String,Object>> roleArray = new ArrayList<>(roles.size());

        if(systemAuth != null && StringUtil.isNotEmpty(systemAuth.getRoleIds())){
            String []ids = systemAuth.getRoleIds().split(",");
            roleIds = Arrays.asList(ids);
        }

        for (SystemRole role : roles){
            Map<String,Object> map = new HashMap<>();
            map.put("roleId",role.getRoleId());
            map.put("roleName",role.getRoleName());
            map.put("checked", roleIds.contains(role.getRoleId()+""));
            roleArray.add(map);
        }
        mv.addObject("roleArray",roleArray);
        return mv;
    }

    @OperationMapping(value = "/insert", description = "修改角色")
    public ResultBody authInsert(int userId, String roleIds){
        ResultBody body = new ResultBody();
        SystemUser sessionUser = (SystemUser)getSessionUser();
        if(sessionUser == null){
            return body.error(Constants.CODE_NOT_USER);
        }
        SystemAuth systemAuth = new SystemAuth();
        systemAuth.setUserId(userId);
        systemAuth.setRoleIds(roleIds);
        systemAuth.setAdminId(sessionUser.getUserId());
        systemAuth.setAdminName(sessionUser.getUserName());
        systemAuth.setCreateTime(new Date());
        boolean status = systemAuthService.insertUserAuth(systemAuth);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }
}
