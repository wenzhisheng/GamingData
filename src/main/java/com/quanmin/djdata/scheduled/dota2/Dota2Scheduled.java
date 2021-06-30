package com.quanmin.djdata.scheduled.dota2;

import com.quanmin.djdata.dota2.service.IDota2Service;
import com.quanmin.djdata.pojo.dota2.Dota2VO;
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
public class Dota2Scheduled {

    @Autowired
    private IDota2Service iDota2Service;

    //每天3：05执行
    @Scheduled(cron = "0 25 03 ? * *")
    public void testTasks() {
        iDota2Service.insert(new Dota2VO());
    }

}
