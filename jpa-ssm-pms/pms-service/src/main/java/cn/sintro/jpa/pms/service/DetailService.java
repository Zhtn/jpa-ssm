package cn.sintro.jpa.pms.service;

import cn.sintro.jpa.pms.pojo.Detail;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.data.domain.Page;


/**
 * @program: jpa-ssm-pms
 * @description: DetailService
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/
public interface DetailService {

    // 分页查询问题
    Page<Detail> findAllItemByPage(Integer pageNumber, Integer pageSize, String itemUuid) throws Exception;

    // 模糊分页查询问题
    Page<Detail> findItemByQuery(String search, Integer pageNumber, Integer pageSize, String itemUuid) throws Exception;

    // 根据uuid查询问题
    ResultBean findDetailByUuid(String uuid);

    // 修改问题详情
    ResultBean updateDetail(Detail detail);

    // 删除问题
    ResultBean deleteDetail(String uuid);

    // 提交问题
    ResultBean submitDetail(Detail detail, String uuid) throws Exception;




}
