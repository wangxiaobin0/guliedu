package com.service.edu.feign;

import com.common.entity.vo.SessionVo;
import com.common.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.edu.feign.impl.UCenterFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2020/5/6
 */
@FeignClient(value = "SERVICE-UCENTER", fallback = UCenterFeignImpl.class)
public interface UCenterFeign {
    @GetMapping("/api/member/auth")
    R auth(@RequestParam("request") HttpServletRequest request)  throws JsonProcessingException;
}
