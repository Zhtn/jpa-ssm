package cn.sintro.jpa.pms.controller;

import cn.sintro.jpa.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: sintro-pms3
 * @description:
 * @author: zhang
 * @create: 2019-09-27 08:56
 **/

@RequestMapping("system")
@Controller
public class SystemController {


    @Autowired
    private UserService userService;


    // 主页面跳转控制
    @RequestMapping("index")
    public String getIndexPage() {
        return "system/index";
    }


    // 用户管理页面跳转控制
    @RequestMapping("userList")
    public String getUserPage() {
        return "user/user-list";
    }


    // 项目管理页面跳转控制
    @RequestMapping("itemList")
    public String getItemPage() {
        return "item/item-list";
    }


    // 问题详情页面跳转控制
    @RequestMapping("detailList")
    public String getDetailPage(String itemUuid, Model model) {
        if (itemUuid != null && !"".equals(itemUuid)) {
            model.addAttribute("itemUuid", itemUuid);
        }
        return "detail/detail-list";
    }


    // 角色管理页面跳转控制
    @RequestMapping("roleList")
    public String getRolePage() {
        return "role/role-list";
    }


    // 权限管理页面跳转控制
    @RequestMapping("permissionList")
    public String getPermissionPage() {
        return "permission/permission-list";
    }


    // 登录按钮页面跳转控制
    @RequestMapping("login")
    public String login(HttpSession session) {
        return "login/login";
    }


    // 退出按钮页面跳转控制
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login/login";
    }


    // 错误页面跳转
    @RequestMapping("loginError")
    public String loginError(String error, Model model){
        if(null != error && error.equals("1")) {
            model.addAttribute("loginError","用户名或密码不正确!");
            return "login/login";
        }else {
            return "system/403";
        }
    }


    // 成功页面跳转
    @RequestMapping("loginSuccess")
    public String loginSuccess(String username,HttpSession session) throws Exception {
        Map<String, Object> info = userService.getUserInfo(username);
        session.setAttribute("info",info);
        return "system/index";
    }

}
