package com.hlh.order.openfeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "cloud-goods-service", path = "/goods")
public interface GoodsDemoService {
    /**
     * 减库存
     *
     * @param goodsId 商品id
     * @return Boolean
     */
    @PutMapping(value = "/{goodsId}")
    Boolean deStock(@PathVariable(value = "goodsId") int goodsId);
}

