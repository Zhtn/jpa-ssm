package cn.sintro.jpa.pms.controller;

import cn.sintro.jpa.pms.pojo.User;
import cn.sintro.jpa.pms.service.UserService;
import cn.sintro.jpa.pms.util.ResultBean;
import com.mysql.jdbc.log.Slf4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: jpa-ssm-pms
 * @description: UserController
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/

@Controller
@RequestMapping("user")
public class UserController {


    // spring注入
    @Autowired
    private UserService userService;

    Slf4JLogger logger = new Slf4JLogger("UserController");


    // 查询所有用户和模糊查询
    @RequestMapping("findAll")
    @ResponseBody
    public Page<User> findAllUser(String search, Integer offset, Integer limit, HttpServletResponse response) throws Exception {

        // 判断搜索是否为空
        if (search != null && !"".equals(search)) {
            logger.logInfo(search);
            Page<User> page = userService.findUserByQuery(search, offset, limit);
            return page;
        } else {
            System.out.println(offset);
            Page<User> page = userService.findAllUserByPage(offset, limit);
            return page;
        }
    }


    // 删除用户
    @RequestMapping("deleteUser")
    @ResponseBody
    public ResultBean deleteRole(String uuid) throws Exception {
        ResultBean result = userService.deleteUser(uuid);
        return result;
    }


    // 添加新用户
    @RequestMapping("saveUser")
    @ResponseBody
    public ResultBean saveUser(User user) throws Exception {
        ResultBean result = userService.saveUser(user);
        return result;
    }


    // 修改用户信息
    @RequestMapping("updateUser")
    @ResponseBody
    public ResultBean updateUser(User user) throws Exception {
        ResultBean result = userService.updateUser(user);
        return result;
    }


    // 根据uuid查询用户方法
    @RequestMapping("findUserByUuid")
    @ResponseBody
    public ResultBean findUserByUuid(String uuid, HttpServletResponse response) throws Exception {
        ResultBean result = userService.findUserByUuid(uuid);
        return result;
    }


    // 用户绑定角色方法
    @RequestMapping("addRole")
    @ResponseBody
    public ResultBean addRole(String uuid, String[] userRoleUuid) throws Exception{
        ResultBean result = userService.addRole(uuid,userRoleUuid);
        return result;
    }


    // 校验用户名是否存在
    @RequestMapping("findUserByName")
    @ResponseBody
    public ResultBean findUserByName(String name) throws Exception{
        ResultBean userByName = userService.findUserByName(name);
        return userByName;
    }

}
