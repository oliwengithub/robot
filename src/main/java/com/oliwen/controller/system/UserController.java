package com.oliwen.controller.system;

import com.oliwen.base.BaseController;
import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.entity.ResultBody;
import com.oliwen.interceptor.OperationMapping;
import com.oliwen.interceptor.SecurityAnnotation;
import com.oliwen.pojo.SystemUser;
import com.oliwen.service.system.SystemUserService;
import com.oliwen.util.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = getView("/system/user/list");
        return mv;
    }

    @RequestMapping("/list/query")
    public ResultBody listQuery(Integer pageIndex, Integer pageSize){
        ResultBody body = new ResultBody();
        Page page = new Page(pageIndex,pageSize);
        PageData pd = this.getPageData();
        String roleIds = pd.getString("roleId");
        if(roleIds != null && !"".equals(roleIds)){
            pd.put("roleIds", roleIds.split(","));
        }
        page.setPd(pd);
        page.setCheckTotal(true);
        List<SystemUser> systemUsers = systemUserService.querySystemUserListPage(page);
        body.setData(systemUsers);
        body.setCount(page.getTotalResult());
        return body;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(int userId){
        ModelAndView mv = getView("/system/user/edit");
        SystemUser systemUser = systemUserService.querySystemUserById(userId);
        mv.addObject("systemUser",systemUser);
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = getView("/system/user/add");
        //将传入的角色ID传递到页面
        mv.addAllObjects(this.getPageData());
        return mv;
    }

    @OperationMapping(value = "/update", description = "更新系统用户")
    public ResultBody update(SystemUser user){
        ResultBody body = new ResultBody();
        boolean status = systemUserService.updateUser(user.getUserId(),user.getUserName(),null,user.getNickName(),user.getAvatar(),user.getSex(),
                user.getMobile(),user.getEmail(),user.getRemark(),user.getStatus());
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @RequestMapping("/insert")
    public ResultBody insert(SystemUser systemUser, String roleIds){
        ResultBody body = new ResultBody();
        SystemUser sessionUser = (SystemUser)getSessionUser();
        if(sessionUser == null){
            return body.error(Constants.CODE_NOT_USER);
        }
        systemUser.setAdminId(sessionUser.getUserId());
        systemUser.setAdminName(sessionUser.getUserName());
        systemUser.setCreateTime(new Date());
        systemUser.setPassword(getPassword(systemUser.getUserName(),systemUser.getPassword()));
        boolean status = systemUserService.insertUserAndAuth(systemUser, roleIds);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @RequestMapping("/check/username")
    @SecurityAnnotation(auth = false)
    public ResultBody checkUserName(String userName){
        ResultBody body = new ResultBody();
        boolean exist = systemUserService.checkUserName(userName);
        body.setData(exist);
        return body;
    }

    @OperationMapping(value = "/update/status",description = "更新系统用户状态")
    public ResultBody infoUpdate(int userId,int status){
        ResultBody body = new ResultBody();
        boolean updateStatus = systemUserService.updateUserStatus(userId,status);
        if(!updateStatus){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }

    @RequestMapping("/my/info")
    @SecurityAnnotation(auth = false)
    public ModelAndView myInfo(){
        ModelAndView mv = getView("/system/user/my/info");
        SystemUser sessionUser = (SystemUser)getSessionUser();
        if(sessionUser != null){
            SystemUser systemUser = systemUserService.querySystemUserById(sessionUser.getUserId());
            mv.addObject("systemUser",systemUser);
        }
        return mv;
    }

    @OperationMapping(value = "/my/info/update",description = "修改个人信息")
    @SecurityAnnotation(auth = false)
    public ResultBody myInfoUpdate(SystemUser user){
        ResultBody body = new ResultBody();
        SystemUser systemUser = (SystemUser)getSessionUser();
        if(systemUser == null){
            return body.error(Constants.CODE_NOT_USER);
        }
        boolean status = systemUserService.updateUser(systemUser.getUserId(),user.getUserName(),null,user.getNickName(),user.getAvatar(),user.getSex(),
                user.getMobile(),user.getEmail(),user.getRemark(),null);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        //更新用户缓存数据
        systemUser = systemUserService.querySystemUserById(systemUser.getUserId());
        setSessionUser(systemUser);
        return body;
    }

    @RequestMapping("/my/password")
    @SecurityAnnotation(auth = false)
    public ModelAndView myPassword(){
        ModelAndView mv = getView("/system/user/my/password");
        return mv;
    }

    @OperationMapping(value = "/my/password/update",description = "修改个人密码")
    @SecurityAnnotation(auth = false)
    public ResultBody myPasswordUpdate(String oldPassword,String newPassword){
        ResultBody body = new ResultBody();
        if(existEmpty(oldPassword,newPassword)){
            return body.error("参数不能为空");
        }
        SystemUser systemUser = (SystemUser)getSessionUser();
        if(systemUser == null){
            return body.error(Constants.CODE_NOT_USER);
        }

        if(!getPassword(systemUser.getUserName(),oldPassword).equals(systemUser.getPassword())){
            return body.error(Constants.CODE_FAIL);
        }
        boolean status = systemUserService.updateUserPassword(systemUser.getUserId(),getPassword(systemUser.getUserName(),newPassword));
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        //更新用户缓存数据
        systemUser = systemUserService.querySystemUserById(systemUser.getUserId());
        setSessionUser(systemUser);
        return body;
    }

    @RequestMapping("/lock")
    @SecurityAnnotation(auth = false)
    public ModelAndView lock(){
        ModelAndView mv = getView("/system/user/lock");
        return mv;
    }

    @RequestMapping("/unlock")
    @SecurityAnnotation(auth = false)
    public ResultBody unlock(String password){
        ResultBody body = new ResultBody();
        SystemUser systemUser = (SystemUser)getSessionUser();
        if(systemUser == null){
            return body.error(Constants.CODE_NOT_USER);
        }
        if(!systemUser.getPassword().equals(getPassword(systemUser.getUserName(),password))){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }
}
