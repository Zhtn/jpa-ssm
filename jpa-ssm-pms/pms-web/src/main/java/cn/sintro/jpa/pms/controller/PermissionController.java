package cn.sintro.jpa.pms.controller;

import cn.sintro.jpa.pms.pojo.Permission;
import cn.sintro.jpa.pms.service.PermissionService;
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
 * @create: 2019-10-19 15:49
 **/

@Controller
@RequestMapping("permission")
public class PermissionController {

    // spring注入
    @Autowired
    private PermissionService permissionService;

    Slf4JLogger logger = new Slf4JLogger("PermissionController");

    // 查询所有权限和模糊查询
    @RequestMapping("findAll")
    @ResponseBody
    public Page<Permission> findAllUser(String search, Integer offset, Integer limit, HttpServletResponse response) throws Exception {
        // 判断搜索是否为空
        if (search != null && !"".equals(search)) {
            logger.logInfo(search);
            Page<Permission> page = permissionService.findPermissionByQuery(search, offset, limit);
            return page;
        } else {
            System.out.println(offset);
            Page<Permission> page = permissionService.findAllPermissionByPage(offset, limit);
            return page;
        }
    }

    // 添加新权限
    @RequestMapping("savePermission")
    @ResponseBody
    public ResultBean savePermission(Permission permission, String parentPermissionUuid) throws Exception {
        System.out.println("Controller:permission" + permission);
        System.out.println("Controller:parentPermissionUuid" + parentPermissionUuid);
        ResultBean result = permissionService.savePermission(permission,parentPermissionUuid);
        return result;
    }

    // 删除权限
    @RequestMapping("deletePermission")
    @ResponseBody
    public ResultBean deletePermission(String uuid) throws Exception {
        ResultBean result = permissionService.deletePermission(uuid);
        return result;
    }

    // 修改权限信息
    @RequestMapping("updatePermission")
    @ResponseBody
    public ResultBean updatePermission(Permission permission, String parentPermissionUuid) throws Exception {
        ResultBean result = permissionService.updatePermission(permission, parentPermissionUuid);
        return result;
    }

    // 根据uuid查询权限
    @RequestMapping("findPermissionByUuid")
    @ResponseBody
    public ResultBean findPermissionByUuid(String uuid, HttpServletResponse response) throws Exception {
        ResultBean result = permissionService.findPermissionByUuid(uuid);
        return result;
    }

    // 查询所有父级权限
    @RequestMapping("findAllPermission")
    @ResponseBody
    public ResultBean findAllAjax() throws Exception {
        return permissionService.findAllAjax();
    }

}
