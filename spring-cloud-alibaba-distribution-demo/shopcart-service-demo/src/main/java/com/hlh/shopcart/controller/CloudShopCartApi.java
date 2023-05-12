package com.hlh.shopcart.controller;

import com.hlh.shopcart.service.ShopCartService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class CloudShopCartApi {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private ShopCartService shopCartService;
    @GetMapping("/shop-cart/getGoodsId")
    public int getGoodsId(@RequestParam("cartId") int cartId) {
        // 根据主键id查询购物表
        Map<String, Object> cartItemObject = jdbcTemplate.queryForMap("select * from tb_cart_item where cart_id=" + cartId + " limit 1");
        if (cartItemObject.containsKey("goods_id")) {
            // 返回商品id
            return (int) cartItemObject.get("goods_id");
        }
        return 0;
    }

    @DeleteMapping("/shop-cart/{cartId}")
    @GlobalTransactional(name = "shopcart-service-demo", rollbackFor = Exception.class)
    public Boolean deleteItem(@PathVariable("cartId") int cartId) {
//        System.out.println(cartId);
        // 删除购物车数据
        int result = shopCartService.deleteitem(cartId);
        return result > 0;
    }
}