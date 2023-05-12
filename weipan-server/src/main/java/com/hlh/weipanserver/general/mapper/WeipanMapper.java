package com.hlh.weipanserver.general.mapper;

import com.hlh.weipanserver.common.ResponseResult;
import com.hlh.weipanserver.general.domain.Weipan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author hlh
* @description 针对表【weipan】的数据库操作Mapper
* @createDate 2022-12-01 13:52:43
* @Entity general.domain.Weipan
*/
public interface WeipanMapper extends BaseMapper<Weipan> {
    void deleteByName(String name);
    List<Weipan> selectByUserId(String id);
}




