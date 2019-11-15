package cn.sintro.jpa.pms.service;

import cn.sintro.jpa.pms.pojo.Detail;
import cn.sintro.jpa.pms.pojo.Item;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.data.domain.Page;


/**
 * @program: jpa-ssm-pms
 * @description: ItemService
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/
public interface ItemService {

    // 查询所有项目
    Page<Item> findAllItemByPage(Integer pageNumber, Integer pageSize) throws Exception;

    // 模糊查询项目
    Page<Item> findItemByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception;

    // 删除项目
    ResultBean deleteItem(String uuid);

    // 添加项目
    ResultBean saveItem(Item item);

    // 修改项目
    ResultBean updateItem(Item item);

    // 根据uuid查询项目
    ResultBean findItemByUuid(String uuid);

    // 提交本项目下的问题
    ResultBean submitDetail(Detail detail, String itemUuid) throws Exception;


}
