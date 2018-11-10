package com.genogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.genogram.entity.FanNewsCultureZipai;
import com.genogram.mapper.FanNewsCultureZipaiMapper;
import com.genogram.service.IFanNewsCultureZipaiService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.genogram.unit.DateUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 联谊会-家族文化-字派表 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-05
 */
@Service
public class FanNewsCultureZipaiServiceImpl extends ServiceImpl<FanNewsCultureZipaiMapper, FanNewsCultureZipai> implements IFanNewsCultureZipaiService {

    /**
     *联谊会字派查询
     *@Author: yuzhou
     *@Date: 2018-11-09
     *@Time: 16:23
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public Page<FanNewsCultureZipai> commonality(Wrapper<FanNewsCultureZipai> entity, Integer pageNo, Integer pageSize) {
        Page<FanNewsCultureZipai> fanNewsCultureZipais = this.selectPage(new Page<FanNewsCultureZipai>(pageNo, pageSize), entity);
        return fanNewsCultureZipais;
        }

    /**
     *联谊会首页字派查询
     *@Author: yuzhou
     *@Date: 2018-11-09
     *@Time: 16:23
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public StringBuffer commonalityIndex(Wrapper<FanNewsCultureZipai> entity) {

        //首页字派查询
        List<FanNewsCultureZipai> fanNewsCultureZipais = this.selectList(entity);
        if (fanNewsCultureZipais.size()==0){
            return null;
        }
        StringBuffer string=new StringBuffer();
        fanNewsCultureZipais.forEach(( data)->{

            string.append(data.getZipaiTxt()+",");
        });
        //删除最后一个字符
        string.deleteCharAt(string.length() - 1);
        return string;
    }

    /**
     *联谊会字派后台进入修改页面
     *@Author: yuzhou
     *@Date: 2018-11-09
     *@Time: 16:23
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public FanNewsCultureZipai getZiPaiDetail(Integer id) {
        FanNewsCultureZipai fanNewsCultureZipai=this.selectById(id);
        return fanNewsCultureZipai;
    }

    /**
     *联谊会字派后台查询
     *@Author: yuzhou
     *@Date: 2018-11-09
     *@Time: 16:24
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public boolean addOrUpdateZiPai(FanNewsCultureZipai fanNewsCultureZipai) {
        //生成时间
        Timestamp format = DateUtil.timestamp();
        if(fanNewsCultureZipai.getId()==null){
            //存入创建时间
            fanNewsCultureZipai.setCreateUser(null);
            fanNewsCultureZipai.setCreateTime(format);
            //存入修改时间
            fanNewsCultureZipai.setUpdateTime(format);
            fanNewsCultureZipai.setUpdateUser(null);
        }else{
            //存入修改时间
            fanNewsCultureZipai.setUpdateTime(format);
            fanNewsCultureZipai.setUpdateUser(null);
        }
        return this.insertOrUpdate(fanNewsCultureZipai);
    }

    /**
     *联谊会家族字派后台删除
     *@Author: yuzhou
     *@Date: 2018-11-10
     *@Time: 11:45
     *@Param:
     *@return:
     *@Description:
    */
    @Override
    public Boolean deleteByIdZipai(Integer id, int status) {
        FanNewsCultureZipai fanNewsCultureZipai = this.selectById(id);
        fanNewsCultureZipai.setStatus(status);
        fanNewsCultureZipai.setUpdateTime(DateUtil.timestamp());
        //修改人  待写
        boolean b = this.updateAllColumnById(fanNewsCultureZipai);
        return b;
    }
}
