package com.quanmin.djdata.scheduled.csgo;

import com.quanmin.djdata.csgo.service.ICsgoService;
import com.quanmin.djdata.pojo.csgo.CsgoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: ate
 * @Description: Csgo赛果数据抓取
 * @CreateDate: 2019-11-08 18:47
 * @ClassName: com.quanmin.djdata.scheduled.csgo.CsgoScheduled
 */
@Component
public class CsgoScheduled {

    @Autowired
    private ICsgoService iCsgoService;

    //每天3：05执行
    @Scheduled(cron = "0 05 03 ? * *")
    public void testTasks() {
        iCsgoService.insert(new CsgoVO());
    }

}
