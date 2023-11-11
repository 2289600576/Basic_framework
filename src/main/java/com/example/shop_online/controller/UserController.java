package com.example.shop_online.controller;

import com.example.shop_online.common.result.Result;
import com.example.shop_online.query.UserLoginQuery;
import com.example.shop_online.service.UserService;
import com.example.shop_online.vo.LoginResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyh
 * @since 2023-11-09
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("user")
@AllArgsConstructor
//@RequestMapping("/shop_online/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "微信登录")
    @PostMapping("login/wxMin")
    public Result<LoginResultVO> wxLogin(@RequestBody @Validated UserLoginQuery query) {
        LoginResultVO userVO = userService.login(query);
        return Result.ok(userVO);
    }
}
