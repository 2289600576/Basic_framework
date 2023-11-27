package com.example.shop_online.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shop_online.common.exception.ServerException;
import com.example.shop_online.entity.Goods;
import com.example.shop_online.entity.UserShoppingCart;
import com.example.shop_online.mapper.GoodsMapper;
import com.example.shop_online.mapper.UserShoppingCartMapper;
import com.example.shop_online.query.CartQuery;
import com.example.shop_online.query.EditCartQuery;
import com.example.shop_online.service.UserShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shop_online.vo.CartGoodsVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyh
 * @since 2023-11-09
 */
@Service
@AllArgsConstructor
public class UserShoppingCartServiceImpl extends ServiceImpl<UserShoppingCartMapper, UserShoppingCart> implements UserShoppingCartService {
private  final GoodsMapper goodsMapper;
    @Override
    public CartGoodsVO addShopCart(CartQuery query) {
        //
        Goods goods = goodsMapper.selectById(query.getId());
        if (goods==null){
            throw  new ServerException("商品不存在");
        }if (query.getCount()>goods.getInventory()){
            throw new ServerException("商品庫存不足");
        }
        //
        UserShoppingCart userShoppingCart =new UserShoppingCart();
        userShoppingCart.setUserId(query.getUserId());
        userShoppingCart.setGoodsId(goods.getId());
        userShoppingCart.setPrice(goods.getPrice());
        userShoppingCart.setCount(query.getCount());
        userShoppingCart.setAttrsText(query.getAttrsText());
        userShoppingCart.setSelected(false);
        baseMapper.insert(userShoppingCart);

        //返回
        CartGoodsVO goodsVO =new CartGoodsVO();
        goodsVO.setId(userShoppingCart.getId());
        goodsVO.setName(goods.getName());
        goodsVO.setAttrsText(query.getAttrsText());
        goodsVO.setPrice(userShoppingCart.getPrice());
        goodsVO.setNowPrice(goods.getPrice());
        goodsVO.setSelected(userShoppingCart.getSelected());
        goodsVO.setStock(goods.getInventory());
        goodsVO.setCount(query.getCount());
        goodsVO.setPicture(goods.getCover());
        goodsVO.setDiscount(goods.getDiscount());

        return goodsVO;
    }

    @Override
    public List<CartGoodsVO> shopCartList(Integer useId) {
        List<CartGoodsVO> list = baseMapper.getCartGoodsInfo(useId);
        return list;
    }

    @Override
    public CartGoodsVO editCart(EditCartQuery query) {
        UserShoppingCart userShoppingCart = baseMapper.selectById(query.getId());
        if (userShoppingCart==null){
            throw new ServerException("購物車信息不存在");
        }
        userShoppingCart.setCount(query.getCount());
        userShoppingCart.setSelected(query.getSelected());
        baseMapper.updateById(userShoppingCart);
        //查詢購物車
        Goods goods=goodsMapper.selectById(userShoppingCart.getGoodsId());
        if (query.getCount()> goods.getInventory()){
             throw new ServerException(goods.getName()+"库存数量不足");        }
        //返回
        CartGoodsVO goodsVO =new CartGoodsVO();
        goodsVO.setId(userShoppingCart.getId());
        goodsVO.setName(goods.getName());
        goodsVO.setAttrsText(userShoppingCart.getAttrsText());
        goodsVO.setPrice(userShoppingCart.getPrice());
        goodsVO.setNowPrice(goods.getPrice());
        goodsVO.setSelected(userShoppingCart.getSelected());
        goodsVO.setStock(goods.getInventory());
        goodsVO.setCount(query.getCount());
        goodsVO.setPicture(goods.getCover());
        goodsVO.setDiscount(goods.getDiscount());

        return goodsVO;
    }

    @Override
    public void removeCartGoods(Integer userId, List<Integer> ids) {
        List<UserShoppingCart>cartList=baseMapper.selectList(new LambdaQueryWrapper<UserShoppingCart>()
                .eq(UserShoppingCart::getUserId,userId));
        if (cartList.size()==0){
            return;
        }
        //與需要刪除的購物車取交集
        List<UserShoppingCart>deleteCartList =cartList.stream().filter(item->
                ids.contains(item.getId())).collect(Collectors.toList());
        //刪除購物車信息
        removeBatchByIds(deleteCartList);
    }

    @Override
    public void editCartSelected(Boolean selected, Integer userId) {
        List<UserShoppingCart>cartList=baseMapper.selectList(new LambdaQueryWrapper<UserShoppingCart>()
                .eq(UserShoppingCart::getUserId,userId));
        if (cartList.size()==0){
            return;
        }
        cartList.stream().forEach(item->item.setSelected(selected));
        saveOrUpdateBatch(cartList);
    }
}
