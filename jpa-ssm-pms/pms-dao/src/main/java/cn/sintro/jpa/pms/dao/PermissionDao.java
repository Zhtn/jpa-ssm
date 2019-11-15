package cn.sintro.jpa.pms.dao;

import cn.sintro.jpa.pms.pojo.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 15:45
 **/
public interface PermissionDao extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {

    // 权限的模糊查询
    @Query(value = "select t from Permission t where name like %?1% or username like %?1%")
    Page<Permission> findByNameLike(String search, Pageable pageable);

}
