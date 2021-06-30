package com.quanmin.djdata.scheduled.aov;

import com.quanmin.djdata.aov.service.IAovService;
import com.quanmin.djdata.pojo.aov.AovVO;
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
public class AovScheduled {

    @Autowired
    private IAovService iAovService;

    //每天3：05执行
    @Scheduled(cron = "0 05 02 ? * *")
    public void testTasks() {
        iAovService.insert(new AovVO());
    }

}
