package com.genogram.controller;

import com.genogram.config.Constants;
import com.genogram.entityvo.SysWebMenuVo;
import com.genogram.service.IProSysWebNewsShowService;
import com.genogram.unit.Response;
import com.genogram.unit.ResponseUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 联谊会首页-设置 前端控制器
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@RestController
@RequestMapping("/genogram/admin/proMenu")
@CrossOrigin(origins = "*")
public class ProIndexMenuController {
    @Value("${pro_api_web_service.ip}")
    private String hostIp;
    @Autowired
    private IProSysWebNewsShowService proSysWebNewsShowService;

    //localhost:8050/genogram/admin/proMenu/getTitlesByMenuId?siteId=1&menuId=2
    @RequestMapping(value = "/getTitlesByMenuId", method = RequestMethod.GET)
    public Response getTitlesByMenuId(@RequestParam(name = "siteId") int siteId, @RequestParam(name = "menuId") int menuId) {
        List<SysWebMenuVo> list = proSysWebNewsShowService.getTitlesByMenuId(hostIp,false,siteId, menuId);
        if (list.isEmpty()) {
            return ResponseUtlis.error(Constants.IS_EMPTY, list);
        }
        return ResponseUtlis.success(list);
    }
}
