package cn.sintro.jpa.pms.controller;


import cn.sintro.jpa.pms.pojo.Role;
import cn.sintro.jpa.pms.service.RoleService;
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
 * @description:
 * @author: zhang
 * @create: 2019-10-19 14:37
 **/
@Controller
@RequestMapping("role")
public class RoleController {

    // spring注入
    @Autowired
    private RoleService roleService;

    Slf4JLogger logger = new Slf4JLogger("RoleController");

    // 查询所有角色和模糊查询
    @RequestMapping("findAll")
    @ResponseBody
    public Page<Role> findAllRole(String search, Integer offset, Integer limit, HttpServletResponse response) throws Exception {
        // 判断搜索是否为空
        if (search != null && !"".equals(search)) {
            logger.logInfo(search);
            Page<Role> page = roleService.findRoleByQuery(search, offset, limit);
            return page;
        } else {
            System.out.println(offset);
            Page<Role> page = roleService.findAllRoleByPage(offset, limit);
            return page;
        }
    }

    // 添加新角色
    @RequestMapping("saveRole")
    @ResponseBody
    public ResultBean saveRole(Role role) throws Exception {
        ResultBean result = roleService.saveRole(role);
        return result;
    }

    // 删除角色方法
    @RequestMapping("deleteRole")
    @ResponseBody
    public ResultBean deleteRole(String uuid) throws Exception {
        ResultBean result = roleService.deleteRole(uuid);
        return result;
    }

    // 修改角色信息方法
    @RequestMapping("updateRole")
    @ResponseBody
    public ResultBean updateRole(Role role) throws Exception {
        ResultBean result = roleService.updateRole(role);
        return result;
    }

    // 根据uuid查询角色方法
    @RequestMapping("findRoleByUuid")
    @ResponseBody
    public ResultBean findRoleByUuid(String uuid, HttpServletResponse response) throws Exception {
        ResultBean result = roleService.findRoleByUuid(uuid);
        return result;
    }

    // 用户添加角色查询方法
    @RequestMapping("findAjax")
    @ResponseBody
    public ResultBean findAllAjax() throws Exception {
        return roleService.findAllAjax();
    }

    // 角色绑定权限方法
    @RequestMapping("addPermission")
    @ResponseBody
    public ResultBean addPermission(String uuid, String[] rolePermissionUuid) throws Exception{
        ResultBean result = roleService.addPermission(uuid,rolePermissionUuid);
        return result;
    }

}
