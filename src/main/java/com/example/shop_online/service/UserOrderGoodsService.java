package com.example.shop_online.service;

import com.example.shop_online.entity.UserOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2023-11-09
 */
public interface UserOrderGoodsService extends IService<UserOrderGoods> {
    void batchUserOrderGoods(List<UserOrderGoods> list);
}
