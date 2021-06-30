package com.quanmin.djdata.scheduled.lol;

import com.quanmin.djdata.lol.service.ILolService;
import com.quanmin.djdata.pojo.lol.LolVO;
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
public class LolScheduled {

    @Autowired
    private ILolService iLolService;

    //每天3：05执行
    @Scheduled(cron = "0 25 05 ? * *")
    public void testTasks() {
        iLolService.insert(new LolVO());
    }

}
