package com.quanmin.djdata.scheduled.team;

import com.quanmin.djdata.pojo.team.TeamVO;
import com.quanmin.djdata.team.service.ITeamService;
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
public class TeamScheduled {

    @Autowired
    private ITeamService iTeamService;

    //每天3：05执行
    @Scheduled(cron = "0 05 06 ? * *")
    public void testTasks() {
        iTeamService.insert(new TeamVO());
    }

}
