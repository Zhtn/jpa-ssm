package cn.sintro.jpa.pms.dao;

import cn.sintro.jpa.pms.pojo.Item;
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

public interface ItemDao extends JpaRepository<Item, String>, JpaSpecificationExecutor<Item> {

    // 项目的模糊查询
    @Query(value = "select t from Item t where name like %?1%")
    Page<Item> findByNameLike(String search, Pageable pageable);
}
