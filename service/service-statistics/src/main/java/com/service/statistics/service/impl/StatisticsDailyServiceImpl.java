package com.service.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.R;
import com.service.statistics.api.UCenterFeign;
import com.service.statistics.entity.StatisticsDaily;
import com.service.statistics.mapper.StatisticsDailyMapper;
import com.service.statistics.service.IStatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-07
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements IStatisticsDailyService {

    @Resource
    UCenterFeign uCenterFeign;


    @Override
    public void registerCount(String day) {
        //删除已经统计过的
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("date_calculated", day);
        this.remove(queryWrapper);

        R r = uCenterFeign.registerCount(day);
        if(!r.getSuccess()) {
            throw new RuntimeException("统计失败");
        }
        Integer register = (Integer) r.getData().get("count");
        Integer loginNum = RandomUtils.nextInt(100, 200);
        Integer videoViewNum = RandomUtils.nextInt(100, 200);
        Integer courseNum = RandomUtils.nextInt(100, 200);

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setLoginNum(loginNum);
        statisticsDaily.setCourseNum(courseNum);
        statisticsDaily.setRegisterNum(register);
        statisticsDaily.setVideoViewNum(videoViewNum);
        statisticsDaily.setDateCalculated(day);
        this.save(statisticsDaily);

    }

    @Override
    public Map<String, Object> info(String begin, String end, String type) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper();
        queryWrapper.between("date_calculated", begin, end);
        List<StatisticsDaily> dailyList = this.list(queryWrapper);
        List<Integer> dataList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        dailyList.forEach(item->{
            dateList.add(item.getDateCalculated());
            switch (type) {
                case "register_num":
                    dataList.add(item.getRegisterNum());
                    break;
                case "login_num":
                    dataList.add(item.getLoginNum());
                    break;
                case "video_view_num":
                    dataList.add(item.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(item.getCourseNum());
                    break;
                default:
            }
        });

        map.put("dataList", dataList);
        map.put("dateList", dateList);
        return map;
    }
}
