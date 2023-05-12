package com.hlh.goods.controller;

import com.hlh.goods.service.GoodsService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
public class goodsController {
    @Resource
    private GoodsService goodsService;

    @PutMapping("/goods/{goodsId}")
    @GlobalTransactional(name = "goods-service-demo", rollbackFor = Exception.class)
    public Boolean deStock(@PathVariable("goodsId") int goodsId) {
        // 减库存操作
        return goodsService.delStock(goodsId) > 0;
    }
}
