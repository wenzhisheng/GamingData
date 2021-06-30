package com.quanmin.djdata.scheduled.team;

import com.quanmin.djdata.pojo.team.TeamRankVO;
import com.quanmin.djdata.team.service.ITeamRankService;
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
public class TeamRankScheduled {

    @Autowired
    private ITeamRankService iTeamRankService;

    //每天3：05执行
    @Scheduled(cron = "0 25 06 ? * *")
    public void testTasks() {
        iTeamRankService.insert(new TeamRankVO());
    }

}
