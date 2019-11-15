package cn.sintro.jpa.pms.service.impl;

import cn.sintro.jpa.pms.dao.ItemDao;
import cn.sintro.jpa.pms.pojo.Detail;
import cn.sintro.jpa.pms.pojo.Item;
import cn.sintro.jpa.pms.service.DetailService;
import cn.sintro.jpa.pms.service.ItemService;
import cn.sintro.jpa.pms.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: jpa-ssm-pms
 * @description:ItemServiceImpl
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/


@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private DetailService detailService;

    // 分页查询全部项目
    @Override
    public Page<Item> findAllItemByPage(Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<Item> items = itemDao.findAll(pageable);
        return items;
    }


    // 分页模糊查询项目
    @Override
    public Page<Item> findItemByQuery(String search, Integer pageNumber, Integer pageSize) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<Item> page = itemDao.findByNameLike(search, pageable);
        return page;
    }


    // 删除项目
    @Override
    public ResultBean deleteItem(String uuid) {
        try {
            itemDao.deleteById(uuid);
            return ResultBean.build(200, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "删除失败！服务器异常" + e.getMessage());
        }
    }


    // 添加项目
    @Override
    public ResultBean saveItem(Item item) {

        item.setUpdateTime(new Date());
        item.setCreateTime(new Date());
        try {
            itemDao.save(item);
            return ResultBean.build(200, "新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "新增失败！服务器异常" + e.getMessage());
        }
    }


    // 修改项目内容
    @Override
    public ResultBean updateItem(Item item) {

        Item item1 = itemDao.getOne(item.getUuid());

        if (item1 != null) {

            item.setUpdateTime(new Date());
            item.setCreateTime(item1.getCreateTime());
            try {
                itemDao.save(item);
                return ResultBean.build(200, "修改成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "修改失败！服务器异常" + e.getMessage());
            }
        } else {
            return ResultBean.build(500, "失败！");
        }
    }


    // 根据uuid查询项目
    @Override
    public ResultBean findItemByUuid(String uuid) {
        Optional<Item> items = itemDao.findById(uuid);
        Item itemByUuid = items.get();
        if (itemByUuid != null) {
            return ResultBean.build(200, "查询成功", itemByUuid);
        } else {
            return ResultBean.build(500, "用户不存在！");
        }
    }


    // 提交项目的问题
    @Override
    public ResultBean submitDetail(Detail detail, String itemUuid) throws Exception {
        return detailService.submitDetail(detail, itemUuid);
    }

}
