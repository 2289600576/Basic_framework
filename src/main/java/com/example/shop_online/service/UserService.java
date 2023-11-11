package com.example.shop_online.service;

import com.example.shop_online.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shop_online.query.UserLoginQuery;
import com.example.shop_online.vo.LoginResultVO;
import com.example.shop_online.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2023-11-09
 */
public interface UserService extends IService<User> {
//用户登录
LoginResultVO login(UserLoginQuery query);


}
