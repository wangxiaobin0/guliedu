package com.service.statistics.task;

import com.service.statistics.service.IStatisticsDailyService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author
 * @date 2020/5/7
 */
@Component
public class ScheduledTask {

    @Autowired
    IStatisticsDailyService dailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void countTask(){
        DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
    }
}
