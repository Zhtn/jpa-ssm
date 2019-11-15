package cn.sintro.jpa.pms.controller;

import cn.sintro.jpa.pms.pojo.Detail;
import cn.sintro.jpa.pms.service.DetailService;
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
 * @description:
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/
@Controller
@RequestMapping("detail")
public class DetailController {

    // spring注入
    @Autowired
    private DetailService detailService;

    Slf4JLogger logger = new Slf4JLogger("DetailController");

    // 查询本项目下的全部问题
    @RequestMapping("findDetailByItemId")
    @ResponseBody
    public Page<Detail> findDetailByItemId(String search, Integer offset, Integer limit, String itemUuid) throws Exception {
        // 判断搜索是否为空
        if (search != null && !"".equals(search)) {
            logger.logInfo(search);
            Page<Detail> page = detailService.findItemByQuery(search, offset, limit, itemUuid);
            return page;
        } else {
            System.out.println(offset);
            Page<Detail> page = detailService.findAllItemByPage(offset, limit, itemUuid);
            return page;
        }
    }


    // 查询问题
    @RequestMapping("findDetailByUuid")
    @ResponseBody
    public ResultBean findDetailByUuid(String uuid, HttpServletResponse response) throws Exception {
        ResultBean result = detailService.findDetailByUuid(uuid);
        return result;
    }


    // 修改问题
    @RequestMapping("updateDetail")
    @ResponseBody
    public ResultBean updateDetail(Detail detail) throws Exception {
        ResultBean result = detailService.updateDetail(detail);
        return result;
    }


    // 删除问题
    @RequestMapping("deleteDetail")
    @ResponseBody
    public ResultBean deleteRole(String uuid) throws Exception {
        ResultBean result = detailService.deleteDetail(uuid);
        return result;
    }


    // 添加新问题
    @RequestMapping("submitDetail")
    @ResponseBody
    public ResultBean submitItem(Detail detail, String itemUuid) throws Exception {
        ResultBean result = detailService.submitDetail(detail, itemUuid);
        return result;
    }
}
