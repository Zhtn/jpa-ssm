package cn.sintro.jpa.pms.service.impl;

import cn.sintro.jpa.pms.dao.UserDao;
import cn.sintro.jpa.pms.pojo.Permission;
import cn.sintro.jpa.pms.pojo.Role;
import cn.sintro.jpa.pms.pojo.User;
import cn.sintro.jpa.pms.service.PermissionService;
import cn.sintro.jpa.pms.service.RoleService;
import cn.sintro.jpa.pms.service.UserService;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: jpa-ssm-pms
 * @description:UserServiceImpl
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/



@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;



    // 分页查询全部用户
    @Override
    public Page<User> findAllUserByPage(Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<User> users = userDao.findAll(pageable);
        return users;
    }


    // 分页模糊查询用户
    @Override
    public Page<User> findUserByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<User> page = userDao.findByNameLike(search, pageable);
        return page;
    }


    // 删除用户
    @Override
    public ResultBean deleteUser(String uuid) {
        try {
            userDao.deleteById(uuid);
            return ResultBean.build(200, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "删除失败！服务器异常" + e.getMessage());
        }
    }


    // 添加用户
    @Override
    public ResultBean saveUser(User user) {

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        try {
            userDao.save(user);
            return ResultBean.build(200, "新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "新增失败！服务器异常" + e.getMessage());
        }
    }


    // 修改用户信息
    @Override
    public ResultBean updateUser(User user) {

        User user1 = userDao.getOne(user.getUuid());
        if (user1 != null) {

            user.setUpdateTime(new Date());
            user.setCreateTime(user1.getCreateTime());
            try {
                userDao.save(user);
                return ResultBean.build(200, "修改成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "修改失败！服务器异常" + e.getMessage());
            }
        } else {
            return ResultBean.build(500, "失败！");
        }
    }


    // 根据uuid查找用户信息
    @Override
    public ResultBean findUserByUuid(String uuid) throws Exception {

        Optional<User> users = userDao.findById(uuid);
        User userByUuid = users.get();

        if (userByUuid != null) {
            return ResultBean.build(200, "", userByUuid);
        } else {
            return ResultBean.build(500, "用户不存在！");
        }
    }


    // 添加角色方法
    @Override
    public ResultBean addRole(String uuid, String[] userRoleUuid) throws Exception {

        User user = userDao.findUserByUuid(uuid);
        Set<Role> roles = new HashSet<>();

        if (user != null) {
            if (userRoleUuid != null && userRoleUuid.length != 0){

                for (String roleUuid:userRoleUuid) {
                    ResultBean roleBean = roleService.findRoleByUuid(roleUuid);
                    Role role = (Role) roleBean.getData();

                    roles.add(role);
                }
            }
            user.setRoles(roles);

            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());

            try {
                userDao.save(user);
                return ResultBean.build(200, "新增成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "新增失败！服务器异常" + e.getMessage());
            }
        } else {
            return ResultBean.build(500,"用户不存在");
        }
    }


    // 添加用户
    @Override
    public ResultBean findUserByName(String name) throws Exception {
        User userByName = userDao.findUserByName(name);
        if(userByName != null){
            return ResultBean.build(500,"角色名称已存在!");
        }else {
            return ResultBean.build(200,"角色名称可以使用！");
        }
    }


    // 登录方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User user = null;
        try {
            user = userDao.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        org.springframework.security.core.userdetails.User loginUser = new org.springframework.security.core.userdetails.User(user.getUsername(),"{noop}"+user.getPassword(),"0".equals(user.getStatus()) ? false : true ,true,true,true,getAuthority(user.getRoles()));
        return loginUser;
    }


    // 作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(Set<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
        }
        return list;
    }


    // 根据权限查询侧边菜单栏
    @Override
    public Map<String, Object> getUserInfo(String username) throws Exception {

        Map<String, Object> info =  new HashMap<String, Object>();
        List<Map<String, Object>> menus = new ArrayList<Map<String, Object>>();

        // 角色具有权限的菜单列表
        Map<String, Permission> m = new HashMap<String, Permission>();

        if (username != null && !"".equals(username)){
            User user = userDao.findUserByUsername(username);

            if (user != null){

                if (user.getUsername().equals("admin")){
                    // 查询所有权限菜单
                    ResultBean permissionResult = permissionService.findAllAjax();

                    if (permissionResult.getStatus() == 200){
                        List<Permission> permissionList = (List<Permission>) permissionResult.getData();
                        for (Permission permission : permissionList){
                            m.put(permission.getUuid(),permission);
                        }
                    }
                }else {
                    // 不是admin查询所有拥有的权限菜单
                    for (Role role : user.getRoles()){
                        for (Permission permission : role.getPermissions()){
                            m.put(permission.getUuid(),permission);
                        }
                    }
                }
                for (String key : m.keySet()){

                    Permission permission = m.get(key);
                    if (null != permission.getParentPermission()){
                        continue;
                    }
                    menus.addAll(getPermissions(permission,m));
                }

                // 对菜单排序
                for (int i = 0; i < menus.size() -1; i++) {
                    for (int j = 0; j < menus.size() -1 - i; j++) {

                        Map<String, Object> M1 = menus.get(j);
                        Map<String, Object> M2 = menus.get(j+1);

                        Integer seq1 = Integer.parseInt(null == M1.get("zIndex") ? "0" : M1.get("zIndex").toString());
                        Integer seq2 = Integer.parseInt(null == M2.get("zIndex") ? "0" : M2.get("zIndex").toString());
                        if (seq1 > seq2) {
                            Collections.swap(menus, j, j+1);
                        }
                    }
                }

                info.put("user", user);
                info.put("menus", menus);
                return info;
            }else {
                return info;
            }
        }
        return info;
    }

    /**
     * 按层级获取菜单信息
     * @author: wuning
     * @date： 2018年2月3日 下午4:58:13
     * @Title: getMenus
     * @Description: TODO(按层级获取菜单信息)
     * @param: @param menu
     * @param: @param mL
     * @param: @return
     * @return: List<Map<String,Object>>
     * @throws
     */
    private List<Map<String, Object>> getPermissions(Permission menu,Map<String, Permission> mL){

        List<Map<String, Object>> menus = new ArrayList<Map<String, Object>>();
        if(null == mL.get(menu.getUuid())) return menus;// 用户不具有该菜单权限

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("name", menu.getName());
        m.put("path", menu.getUrl());
        m.put("code",menu.getCode());
        m.put("parent", null == menu.getParentPermission()?"":menu.getParentPermission().getUuid());
        m.put("generateMenu", menu.getGenerateMenu());
        m.put("zIndex",menu.getZindex());

        // 递归时当前菜单没有子菜单就退出
        if(null == menu.getChildren() || menu.getChildren().size() ==0) {
        }else {

            List<Map<String, Object>> childrenList = new ArrayList<Map<String, Object>>();
            // 递归获取子菜单
            for(Permission child : (Set<Permission>)menu.getChildren()) {
                childrenList.addAll(getPermissions(child,mL));
            }
            m.put("children", childrenList);
        }
        menus.add(m);
        return menus;
    }
}
