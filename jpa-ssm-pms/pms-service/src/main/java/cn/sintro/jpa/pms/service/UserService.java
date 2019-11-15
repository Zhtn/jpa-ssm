package cn.sintro.jpa.pms.service;

import cn.sintro.jpa.pms.pojo.User;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;


/**
 * @program: jpa-ssm-pms
 * @description: UserService
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/

public interface UserService extends UserDetailsService {

    // 分页查询所有用户
    Page<User> findAllUserByPage(Integer pageNumber, Integer pageSize) throws Exception;

    // 模糊查询用户
    Page<User> findUserByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception;

    // 根据uuid删除用户
    ResultBean deleteUser(String uuid);

    // 添加用户
    ResultBean saveUser(User user);

    // 修改信息
    ResultBean updateUser(User user);

    // 根据uuid查询用户
    ResultBean findUserByUuid(String uuid) throws Exception;

    // 添加角色
    ResultBean addRole(String uuid,String[] userRoleUuid) throws Exception;

    // 存放侧边菜单栏相关数据
    Map<String, Object> getUserInfo(String username) throws Exception;

    // 添加时查找用户名是否存在
    ResultBean findUserByName(String name) throws Exception;

}
