package com.service.sms.controller;

import com.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date 2020/5/5
 */
@Slf4j
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    RedisTemplate redisTemplate;

    private static final String SMS_CODE_PREFIX = "SMS:REGISTER:CODE:";
    private static final Long CODE_EXPIRE_TIME = 5L;

    @GetMapping("/{mobileNo}")
    public R getSmsCode(@PathVariable("mobileNo") String mobileNo) {
        String code = String.valueOf((int)(100000 + Math.random() * 899999));
        code.replaceAll("-", "");
        redisTemplate.opsForValue().set(SMS_CODE_PREFIX + mobileNo, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);
        log.info("获取短信验证码", mobileNo + ":" + code);
        return R.ok().put("code", code);
    }
}
