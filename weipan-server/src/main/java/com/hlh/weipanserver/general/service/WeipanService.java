package com.hlh.weipanserver.general.service;

import com.hlh.weipanserver.common.ResponseResult;
import com.hlh.weipanserver.general.domain.Weipan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hlh
* @description 针对表【weipan】的数据库操作Service
* @createDate 2022-12-01 13:52:43
*/
public interface WeipanService extends IService<Weipan> {

    ResponseResult insert(Weipan weipan);
    ResponseResult delete(String name);
    List<Weipan> select(String id);

}
