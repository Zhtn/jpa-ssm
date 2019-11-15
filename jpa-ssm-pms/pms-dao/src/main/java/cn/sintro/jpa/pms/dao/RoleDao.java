package cn.sintro.jpa.pms.dao;

import cn.sintro.jpa.pms.pojo.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 14:40
 **/
public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    // 角色的模糊查询
    @Query(value = "select t from Role t where name like %?1%")
    Page<Role> findByNameLike(String search, Pageable pageable);

    // 查询角色是否存在
    Role findRoleByUuid(String uuid);
}
