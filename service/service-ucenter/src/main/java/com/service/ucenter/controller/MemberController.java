package com.service.ucenter.controller;


import com.common.utils.R;
import com.service.ucenter.entity.vo.LoginVo;
import com.service.ucenter.entity.vo.RegisterVo;
import com.service.ucenter.service.IMemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2020-05-05
 */
@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    IMemberService memberService;

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public R login(@ApiParam(value = "LoginVo对象", name = "loginVo") @RequestBody LoginVo loginVo) {
        log.info("用户登录", loginVo);
        String token = memberService.login(loginVo);
        return R.ok().put("token", token);
    }


    @PostMapping("/register")
    public R register(@ApiParam(value = "RegisterVo对象", name = "registerVo") @RequestBody RegisterVo registerVo) {
        log.info("用户注册", registerVo);
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("/auth")
    public R auth(HttpServletRequest request) {
        LoginVo loginVo = memberService.auth(request);
        return R.ok().put("item", loginVo);
    }
}

