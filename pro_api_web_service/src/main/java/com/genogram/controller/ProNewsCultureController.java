package com.genogram.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.genogram.config.Constants;
import com.genogram.entity.ProNewsCultureZipai;
import com.genogram.entityvo.NewsCultureZipaiVo;
import com.genogram.service.IProNewsCultureZipaiService;
import com.genogram.unit.Response;
import com.genogram.unit.ResponseUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 联谊会-家族文化前端控制器
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/genogram/proNewsCulture")
public class ProNewsCultureController {

    @Autowired
    private IProNewsCultureZipaiService proNewsCultureZipaiService;

    /**
     * 省级家族字派查询
     * @Author: yuzhou
     * @Date: 2018-11-09
     * @Time: 16:20
     * @Param:
     * @return:
     * @Description:
     */
    @RequestMapping(value = "/getCommonalityPage", method = RequestMethod.GET)
    public Response<ProNewsCultureZipai> getCommonalityPage(
            @RequestParam(value = "showId") Integer showId, // 家族文化显示位置
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize
    ) {
        try {
            //判断showId是否有值
            if (showId==null) {
                return ResponseUtlis.error(Constants.IS_EMPTY, null);
            }
            //状态(0:删除;1:已发布;2:草稿3:不显示)
            List statusList = new ArrayList();
            statusList.add(1);
            //查询条件
            Wrapper<ProNewsCultureZipai> entity = new EntityWrapper<ProNewsCultureZipai>();
            entity.eq("show_id", showId);
            entity.in("status", statusList);
            entity.orderBy("create_time", false);
            Page<ProNewsCultureZipai> fanNewsCultureZipai = proNewsCultureZipaiService.commonality(entity, pageNo, pageSize);
            if (fanNewsCultureZipai == null) {
                //没有取到参数,返回空参
                Page<ProNewsCultureZipai> emptfamilyCultureVo = new Page<ProNewsCultureZipai>();
                return ResponseUtlis.error(Constants.ERRO_CODE, null);
            }
            return ResponseUtlis.success(fanNewsCultureZipai);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtlis.error(Constants.FAILURE_CODE, null);
        }
    }

    /**
     *省级家族字派模糊查询
     *@Author: yuzhou
     *@Date: 2018-11-10
     *@Time: 10:06
     *@Param:
     *@return:
     *@Description:
     */
    @RequestMapping(value = "/getZipaiVaguePage",method = RequestMethod.POST)
    public Response<ProNewsCultureZipai> getZipaiVaguePage(
            @RequestParam(value = "showId") Integer showId, // 家族字派显示位置
            @RequestParam(value = "zipaiTxt") String zipaiTxt, // 家族字派模糊查询参数
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize
    ) {
        try{
            //返回的空list集合结构
            List<ProNewsCultureZipai> list=new ArrayList<>();
            //判断showId是否有值
            if(showId==null){
                return ResponseUtlis.error(Constants.IS_EMPTY,list);
            }
            //状态(0:删除;1:已发布;2:草稿3:不显示)
            List statusList = new ArrayList();
            statusList.add(1);
            Wrapper<ProNewsCultureZipai> entity = new EntityWrapper<ProNewsCultureZipai>();
            entity.eq("show_id",showId);
            entity.in("status",statusList);
            entity.like("zipai_txt",zipaiTxt);
            Page<NewsCultureZipaiVo> fanNewsCultureZipaiPage = proNewsCultureZipaiService.getZipaiVaguePage(entity,pageNo, pageSize);
            if(fanNewsCultureZipaiPage==null){
                return ResponseUtlis.error(Constants.ERRO_CODE,list);
            }
            return ResponseUtlis.success(fanNewsCultureZipaiPage);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtlis.error(Constants.FAILURE_CODE,null);
        }
    }

}
