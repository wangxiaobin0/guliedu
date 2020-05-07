package com.service.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.service.statistics.entity.StatisticsDaily;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author wxb
 * @since 2020-05-07
 */
public interface IStatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    Map<String, Object> info(String begin, String end, String type);

}
