package com.hlh.weipanserver.general.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlh.weipanserver.common.ResponseResult;
import com.hlh.weipanserver.general.domain.Weipan;
import com.hlh.weipanserver.general.service.WeipanService;
import com.hlh.weipanserver.general.mapper.WeipanMapper;
import com.hlh.weipanserver.utils.MinIoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author hlh
* @description 针对表【weipan】的数据库操作Service实现
* @createDate 2022-12-01 13:52:43
*/
@Service
public class WeipanServiceImpl extends ServiceImpl<WeipanMapper, Weipan>
    implements WeipanService{
    @Resource
    private WeipanMapper weipanMapper;
    @Resource
    private MinIoTemplate minIoTemplate;
    @Override
    public ResponseResult insert(Weipan weipan) {
        weipanMapper.insert(weipan);
        return ResponseResult.success("保存成功");
    }

    @Override
    public ResponseResult delete(String name) {
        weipanMapper.deleteByName(name);
        return ResponseResult.success("删除成功");
    }

    @Override
    public List<Weipan> select(String id) {
        List<Weipan> weipans = weipanMapper.selectByUserId(id);
        return weipans;
    }
}




