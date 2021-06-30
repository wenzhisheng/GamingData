package com.quanmin.djdata.scheduled.competitionarea;

import com.quanmin.djdata.competitionarea.service.ICompetitionAreaService;
import com.quanmin.djdata.pojo.competitionarea.CompetitionAreaVO;
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
public class CompetitionAresScheduled {

    @Autowired
    private ICompetitionAreaService iCompetitionAreaService;

    //每天3：05执行
    @Scheduled(cron = "0 25 02 ? * *")
    public void testTasks() {
        iCompetitionAreaService.insert(new CompetitionAreaVO());
    }

}
