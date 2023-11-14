package com.example.shop_online.mapper;

import com.example.shop_online.entity.UserShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop_online.vo.CartGoodsVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2023-11-09
 */
public interface UserShoppingCartMapper extends BaseMapper<UserShoppingCart> {

    //查詢購物車信息  mapper接口寫完 到xml文件中完成查詢語句
    List<CartGoodsVO> getCartGoodsInfo(@Param("id") Integer id);

}
