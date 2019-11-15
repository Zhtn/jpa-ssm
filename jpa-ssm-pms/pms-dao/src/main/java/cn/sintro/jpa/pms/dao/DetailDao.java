package cn.sintro.jpa.pms.dao;

import cn.sintro.jpa.pms.pojo.Detail;
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

public interface DetailDao extends JpaRepository<Detail, String>, JpaSpecificationExecutor<Detail> {

    // 问题的模糊查询
    @Query(value = "select t from Detail t where model like %?1%")
    Page<Detail> findByNameLike(String search, Pageable pageable);


    // 根据item的uuid查询相关问题
    Page<Detail> findByItem(Pageable pageable, Item item);
}
