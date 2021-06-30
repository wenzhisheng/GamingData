package com.quanmin.djdata.scheduled.game;

import com.quanmin.djdata.game.service.IGameRoleService;
import com.quanmin.djdata.pojo.game.GameRoleVO;
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
public class GameRoleScheduled {

    @Autowired
    private IGameRoleService iGameRoleService;

    //每天3：05执行
    @Scheduled(cron = "0 05 04 ? * *")
    public void testTasks() {
        iGameRoleService.insert(new GameRoleVO());
    }

}
