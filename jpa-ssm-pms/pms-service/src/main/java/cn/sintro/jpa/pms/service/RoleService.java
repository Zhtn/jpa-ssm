package cn.sintro.jpa.pms.service;

import cn.sintro.jpa.pms.pojo.Role;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.data.domain.Page;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 14:40
 **/
public interface RoleService {
    // 分页查询所有角色
    Page<Role> findAllRoleByPage(Integer pageNumber, Integer pageSize) throws Exception;

    // 模糊查询角色
    Page<Role> findRoleByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception;

    // 添加角色
    ResultBean saveRole(Role role);

    // 根据uuid删除角色
    ResultBean deleteRole(String uuid);

    // 修改角色
    ResultBean updateRole(Role role);

    // 根据uuid查询角色
    ResultBean findRoleByUuid(String uuid);

    // 添加角色前的查询
    ResultBean findAllAjax() throws Exception;

    // 添加权限
    ResultBean addPermission(String uuid,String[] rolePermissionUuid) throws Exception;
}
