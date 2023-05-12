package com.hlh.goods.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = {RuntimeException.class})
public class GoodsService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    public int delStock(int goodsId){
        return jdbcTemplate.update("update tb_goods set goods_stock=goods_stock-1 where goods_id=" + goodsId);
    }
}
