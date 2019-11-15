package cn.sintro.jpa.pms.controller;

import cn.sintro.jpa.pms.pojo.Detail;
import cn.sintro.jpa.pms.pojo.Item;
import cn.sintro.jpa.pms.service.ItemService;
import cn.sintro.jpa.pms.util.ResultBean;
import com.mysql.jdbc.log.Slf4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: jpa-ssm-pms
 * @description: ItemController
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/

@Controller
@RequestMapping("item")
public class ItemController {

    // spring注入
    @Autowired
    private ItemService itemService;

    Slf4JLogger logger = new Slf4JLogger("ItemController");

    //查询所有项目和模糊查询
    @RequestMapping("findItemAll")
    @ResponseBody
    public Page<Item> findAllItem(String search, Integer offset, Integer limit, HttpServletResponse response) throws Exception {

        // 判断搜索是否为空
        if (search != null && !"".equals(search)) {
            logger.logInfo(search);
            Page<Item> page = itemService.findItemByQuery(search, offset, limit);
            return page;
        } else {
            System.out.println(offset);
            Page<Item> page = itemService.findAllItemByPage(offset, limit);
            return page;
        }
    }


    // 删除项目
    @RequestMapping("deleteItem")
    @ResponseBody
    public ResultBean deleteRole(String uuid) throws Exception {
        ResultBean result = itemService.deleteItem(uuid);
        return result;
    }


    // 添加新项目
    @RequestMapping("saveItem")
    @ResponseBody
    public ResultBean saveItem(Item item) throws Exception {
        ResultBean result = itemService.saveItem(item);
        return result;
    }


    // 修改项目信息
    @RequestMapping("updateItem")
    @ResponseBody
    public ResultBean updateItem(Item item) throws Exception {
        ResultBean result = itemService.updateItem(item);
        return result;
    }


    // 查询项目
    @RequestMapping("findItemByUuid")
    @ResponseBody
    public ResultBean findItemByUuid(String uuid, HttpServletResponse response) throws Exception {
        ResultBean result = itemService.findItemByUuid(uuid);
        return result;
    }


    // 添加新问题
    @RequestMapping("submitDetail")
    @ResponseBody
    public ResultBean submitItem(Detail detail, String itemUuid) throws Exception {
        ResultBean result = itemService.submitDetail(detail, itemUuid);
        return result;
    }
}
