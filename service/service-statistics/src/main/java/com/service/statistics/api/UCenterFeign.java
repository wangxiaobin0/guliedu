package com.service.statistics.api;

import com.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author
 * @date 2020/5/7
 */
@FeignClient(value = "SERVICE-UCENTER")
public interface UCenterFeign {

    /**
     * 某天注册人数
     * @param day
     * @return
     */
    @GetMapping("/api/member/register/{day}")
    R registerCount(@PathVariable("day") String day);
}
