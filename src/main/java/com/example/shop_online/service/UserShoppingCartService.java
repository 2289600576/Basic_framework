package com.example.shop_online.service;

import com.example.shop_online.entity.UserShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shop_online.vo.CartGoodsVO;
import com.example.shop_online.vo.CartQuery;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2023-11-09
 */
public interface UserShoppingCartService extends IService<UserShoppingCart> {
    //計入購物車
    CartGoodsVO addShopCart (CartQuery query);
    //購物車列表
    List<CartGoodsVO> shopCartList(Integer useId);
    //修改購物車
}
