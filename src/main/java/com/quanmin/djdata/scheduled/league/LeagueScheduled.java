package com.quanmin.djdata.scheduled.league;

import com.quanmin.djdata.league.service.ILeagueService;
import com.quanmin.djdata.pojo.league.LeagueVO;
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
public class LeagueScheduled {

    @Autowired
    private ILeagueService iLeagueService;

    //每天3：05执行
    @Scheduled(cron = "0 05 05 ? * *")
    public void testTasks() {
        iLeagueService.insert(new LeagueVO());
    }

}
