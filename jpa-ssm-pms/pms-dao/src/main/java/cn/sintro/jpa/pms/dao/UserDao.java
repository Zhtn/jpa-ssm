package cn.sintro.jpa.pms.dao;

import cn.sintro.jpa.pms.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


/**
 * @program: jpa-ssm-pms
 * @description: UserDao
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    // 用户的模糊查询
    @Query(value = "select t from User t where name like %?1% or username like %?1%")
    Page<User> findByNameLike(String search, Pageable pageable);

    // 查询用户是否存在
    User findUserByUuid(String uuid);

    // 查询用户名
    User findUserByUsername(String username);

    // 添加时查找用户名是否存在
    User findUserByName(String name);
}
