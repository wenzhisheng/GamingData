package com.quanmin.djdata.scheduled.series;

import com.quanmin.djdata.pojo.series.SeriesDetailVO;
import com.quanmin.djdata.series.service.ISeriesDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-11 15:44
 * @ClassName: com.quanmin.djdata.scheduled.team.TeamScheduled
 */
@Component
public class SeriesDetailScheduled {

    @Autowired
    private ISeriesDetailService iSeriesDetailService;

    //每天3：05执行
    @Scheduled(cron = "0 05 06 ? * *")
    public void testTasks() {
        iSeriesDetailService.insert(new SeriesDetailVO());
    }

}
