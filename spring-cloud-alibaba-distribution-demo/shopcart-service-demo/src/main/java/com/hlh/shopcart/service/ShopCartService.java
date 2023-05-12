package com.hlh.shopcart.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = {RuntimeException.class})
public class ShopCartService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    public int deleteitem(int cartId){
        System.out.println(cartId);
        int update = jdbcTemplate.update("delete from tb_cart_item where cart_id=" + cartId);
        System.out.println(update);
        return update;
    }
}
