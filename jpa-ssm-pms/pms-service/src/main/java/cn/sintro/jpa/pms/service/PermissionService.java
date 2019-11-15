package cn.sintro.jpa.pms.service;

import cn.sintro.jpa.pms.pojo.Permission;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.data.domain.Page;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 15:51
 **/
public interface PermissionService {

    // 分页查询所有权限
    Page<Permission> findAllPermissionByPage(Integer pageNumber, Integer pageSize) throws Exception;

    // 模糊查询权限
    Page<Permission> findPermissionByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception;

    // 添加权限
    ResultBean savePermission(Permission permission, String parentPermissionUuid);

    // 根据uuid删除权限
    ResultBean deletePermission(String uuid);

    // 修改权限
    ResultBean updatePermission(Permission permission, String parentPermissionUuid);

    // 根据uuid查询权限
    ResultBean findPermissionByUuid(String uuid) throws Exception;

    // 查询父权限的名字
    ResultBean findAllAjax() throws Exception;
}
