package com.quanmin.djdata.scheduled.series;

import com.quanmin.djdata.pojo.series.SeriesVO;
import com.quanmin.djdata.series.service.ISeriesService;
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
public class SeriesScheduled {

    @Autowired
    private ISeriesService iSeriesService;

    //每天3：05执行
    @Scheduled(cron = "0 45 05 ? * *")
    public void testTasks() {
        iSeriesService.insert(new SeriesVO());
    }

}
