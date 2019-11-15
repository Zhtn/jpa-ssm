package cn.sintro.jpa.pms.service.impl;

import cn.sintro.jpa.pms.dao.RoleDao;
import cn.sintro.jpa.pms.pojo.Permission;
import cn.sintro.jpa.pms.pojo.Role;
import cn.sintro.jpa.pms.service.PermissionService;
import cn.sintro.jpa.pms.service.RoleService;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 14:40
 **/
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionService permissionService;

    // 分页查询全部角色
    @Override
    public Page<Role> findAllRoleByPage(Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<Role> roles = roleDao.findAll(pageable);
        return roles;
    }


    // 分页模糊查询角色
    @Override
    public Page<Role> findRoleByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<Role> page = roleDao.findByNameLike(search, pageable);
        return page;
    }


    // 添加角色
    @Override
    public ResultBean saveRole(Role role) {

        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());

        try {
            roleDao.save(role);
            return ResultBean.build(200, "新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "新增失败！服务器异常" + e.getMessage());
        }
    }


    // 删除角色
    @Override
    public ResultBean deleteRole(String uuid) {
        try {
            roleDao.deleteById(uuid);
            return ResultBean.build(200, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "删除失败！服务器异常" + e.getMessage());
        }
    }


    // 修改角色
    @Override
    public ResultBean updateRole(Role role) {

        Role role1 = roleDao.getOne(role.getUuid());

        if (role1 != null) {

            role.setUpdateTime(new Date());
            role.setCreateTime(role1.getCreateTime());
            try {
                roleDao.save(role);
                return ResultBean.build(200, "修改成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "修改失败！服务器异常" + e.getMessage());
            }
        } else {
            return ResultBean.build(500, "失败！");
        }
    }


    // 根据uuid查找角色信息
    @Override
    public ResultBean findRoleByUuid(String uuid) {

        Optional<Role> roles = roleDao.findById(uuid);
        Role roleByUuid = roles.get();

        if (roleByUuid != null) {
            return ResultBean.build(200, "", roleByUuid);
        } else {
            return ResultBean.build(500, "用户不存在！");
        }
    }


    // 用户添加角色时查询方法
    @Override
    public ResultBean findAllAjax() throws Exception {
        List<Role> name = roleDao.findAll();
        return ResultBean.build(200,"成功",name);
    }


    // 添加权限操作方法
    @Override
    public ResultBean addPermission(String uuid, String[] rolePermissionUuid) throws Exception {

        Role role = roleDao.findRoleByUuid(uuid);
        Set<Permission> permissions = new HashSet<>();
        if (role != null) {
            if (rolePermissionUuid != null && rolePermissionUuid.length !=0){

                for (String permissionUuid:rolePermissionUuid) {
                    ResultBean roleBean = permissionService.findPermissionByUuid(permissionUuid);
                    Permission permission = (Permission) roleBean.getData();
                    permissions.add(permission);
                }
            }
            role.setPermissions(permissions);
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());

            try {
                roleDao.save(role);
                return ResultBean.build(200, "新增成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "新增失败！服务器异常" + e.getMessage());
            }
        } else {
            return ResultBean.build(500,"角色不存在");
        }
    }
}
