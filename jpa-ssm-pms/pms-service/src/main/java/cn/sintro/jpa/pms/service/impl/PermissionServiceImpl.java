package cn.sintro.jpa.pms.service.impl;

import cn.sintro.jpa.pms.dao.PermissionDao;
import cn.sintro.jpa.pms.pojo.Permission;
import cn.sintro.jpa.pms.service.PermissionService;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 15:51
 **/
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    // 分页查询全部权限
    @Override
    public Page<Permission> findAllPermissionByPage(Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<Permission> permissions = permissionDao.findAll(pageable);
        return permissions;
    }


    // 分页模糊查询权限
    @Override
    public Page<Permission> findPermissionByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<Permission> page = permissionDao.findByNameLike(search, pageable);
        return page;
    }


    // 查询可用的父级权限
    @Override
    public ResultBean findAllAjax() throws Exception {
        List<Permission> permissions = permissionDao.findAll();
        return ResultBean.build(200, "成功", permissions);
    }


    // 添加权限
    @Override
    public ResultBean savePermission(Permission permission, String parentPermissionUuid) {

        if (!("").equals(parentPermissionUuid)) {
            Permission one = permissionDao.getOne(parentPermissionUuid);
            permission.setParentPermission(one);
            System.out.println(one);
        }

        permission.setCreateTime(new Date());
        permission.setUpdateTime(new Date());
        try {
            permissionDao.save(permission);
            return ResultBean.build(200, "新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "新增失败！服务器异常" + e.getMessage());
        }
    }


    // 删除权限
    @Override
    public ResultBean deletePermission(String uuid) {
        try {
            permissionDao.deleteById(uuid);
            return ResultBean.build(200, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "删除失败！服务器异常" + e.getMessage());
        }
    }


    // 修改权限信息
    @Override
    public ResultBean updatePermission(Permission permission, String parentPermissionUuid) {

        if (!("").equals(parentPermissionUuid)) {
            Permission one = permissionDao.getOne(parentPermissionUuid);
            permission.setParentPermission(one);
        }

        Permission permission1 = permissionDao.getOne(permission.getUuid());
        if (permission1 != null) {

            permission.setUpdateTime(new Date());
            permission.setCreateTime(permission1.getCreateTime());

            try {
                permissionDao.save(permission);
                return ResultBean.build(200, "修改成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "修改失败！服务器异常" + e.getMessage());
            }
        } else {
            return ResultBean.build(500, "修改失败，该权限已被删除！");
        }
    }


    // 根据uuid查找权限信息
    @Override
    public ResultBean findPermissionByUuid(String uuid) throws Exception {

        Optional<Permission> permissions = permissionDao.findById(uuid);
        Permission permissionByUuid = permissions.get();

        if (permissionByUuid != null) {
            return ResultBean.build(200, "", permissionByUuid);
        } else {
            return ResultBean.build(500, "用户不存在！");
        }
    }


}
