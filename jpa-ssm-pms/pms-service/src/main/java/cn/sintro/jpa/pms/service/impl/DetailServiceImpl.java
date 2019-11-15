package cn.sintro.jpa.pms.service.impl;

import cn.sintro.jpa.pms.dao.DetailDao;
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
import java.util.Optional;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/
@Service("detailService")
@Transactional
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailDao detailDao;

    @Autowired
    private ItemService itemService;

    // 根据item的uuid查询子问题
    @Override
    public Page<Detail> findAllItemByPage(Integer pageNumber, Integer pageSize, String itemUuid) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);

        ResultBean itemByUuid = itemService.findItemByUuid(itemUuid);
        Item item = (Item) itemByUuid.getData();

        Page<Detail> details = detailDao.findByItem(pageable, item);
        return details;
    }


    // 模糊查询问题
    @Override
    public Page<Detail> findItemByQuery(String search, Integer pageNumber, Integer pageSize, String itemUuid) throws Exception {
        Pageable pageable = new PageRequest((pageNumber / pageSize), pageSize);
        Page<Detail> page = detailDao.findByNameLike(search, pageable);
        return page;
    }


    // 根据uuid查询问题
    @Override
    public ResultBean findDetailByUuid(String uuid) {
        Optional<Detail> details = detailDao.findById(uuid);
        Detail detailByUuid = details.get();
        if (detailByUuid != null) {
            return ResultBean.build(200, "查询成功", detailByUuid);
        } else {
            return ResultBean.build(500, "用户不存在！");
        }
    }


    // 修改问题
    @Override
    public ResultBean updateDetail(Detail detail) {

        Detail detail1 = detailDao.getOne(detail.getUuid());

        if (detail1 != null) {

            detail.setUpdateTime(new Date());
            detail.setCreateTime(detail1.getCreateTime());
            detail.setItem(detail1.getItem());
            try {
                detailDao.save(detail);
                return ResultBean.build(200, "修改成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "修改失败！服务器异常" + e.getMessage());
            }
        } else {
            return ResultBean.build(500, "失败！");
        }
    }


    // 删除问题
    @Override
    public ResultBean deleteDetail(String uuid) {
        try {
            detailDao.deleteById(uuid);
            return ResultBean.build(200, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.build(500, "删除失败！服务器异常" + e.getMessage());
        }
    }


    // 提交项目的问题
    @Override
    public ResultBean submitDetail(Detail detail, String itemUuid) throws Exception {

        if (detail != null) {
            ResultBean item = itemService.findItemByUuid(itemUuid);
            Item itemByUuid = (Item) item.getData();

            if (itemByUuid != null) {
                detail.setItem(itemByUuid);
            }
            try {
                detail.setCreateTime(new Date());
                detail.setUpdateTime(new Date());
                detailDao.save(detail);
                return ResultBean.build(200, "成功");
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBean.build(500, "失败" + e.getMessage());
            }
        } else {
            return ResultBean.build(500, "失败");
        }
    }
}
