package com.quanmin.djdata.scheduled.team;

import com.quanmin.djdata.pojo.team.TeamDetailVO;
import com.quanmin.djdata.team.service.ITeamDetailService;
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
public class TeamDetailScheduled {

    @Autowired
    private ITeamDetailService iTeamDetailService;

    //每天3：05执行
    @Scheduled(cron = "0 45 06 ? * *")
    public void testTasks() {
        iTeamDetailService.insert(new TeamDetailVO());
    }

}
