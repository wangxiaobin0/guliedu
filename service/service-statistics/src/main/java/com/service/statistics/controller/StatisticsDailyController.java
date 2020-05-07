package com.service.statistics.controller;


import com.common.utils.R;
import com.service.statistics.service.IStatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/admin/statistics/daily")
public class StatisticsDailyController {
    @Autowired
    IStatisticsDailyService dailyService;
    @PostMapping("/register/{day}")
    public R registerCount(@PathVariable("day") String day) {
        dailyService.registerCount(day);
        return R.ok();
    }

    @GetMapping("/{begin}/{end}/{type}")
    public R info(@PathVariable("begin") String begin, @PathVariable("end") String end, @PathVariable("type") String type) {
        Map<String, Object> info = dailyService.info(begin, end, type);
        return R.ok().put("dateList", info.get("dateList")).put("dataList", info.get("dataList"));
    }
}

