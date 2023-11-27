package com.example.shop_online.service;

import com.example.shop_online.entity.UserShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shop_online.query.CartQuery;
import com.example.shop_online.query.EditCartQuery;
import com.example.shop_online.vo.CartGoodsVO;

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
    CartGoodsVO addShopCart(CartQuery query);

    //購物車列表
    List<CartGoodsVO> shopCartList(Integer useId);

    //修改購物車
    CartGoodsVO editCart(EditCartQuery query);

    //刪除
    void removeCartGoods(Integer userId, List<Integer> ids);

    void editCartSelected(Boolean selected, Integer userId);
}
