package com.genogram.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.genogram.entity.FanNewsCharityOut;
import com.genogram.service.IFanNewsCharityOutService;
import com.genogram.unit.Response;
import com.genogram.unit.ResponseUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 联谊会-家族慈善财 前端控制器
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/genogram/fanNewsCharity")
public class FanNewsCharityController {

    @Autowired
    private IFanNewsCharityOutService iFanNewsCharityOutService;

    @RequestMapping("")
    public Response<FanNewsCharityOut> selectByCreateTime(@RequestParam(value = "siteId",defaultValue = "1") Integer siteId,
                                                          @RequestParam(value = "status",defaultValue = "1")Integer status,
                                                          @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                          @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize) {

        Page<FanNewsCharityOut> fanNewsCharityOutPage = iFanNewsCharityOutService.selectPage(siteId, status, pageNo, pageSize);

        return ResponseUtlis.success(fanNewsCharityOutPage);
    }
}

