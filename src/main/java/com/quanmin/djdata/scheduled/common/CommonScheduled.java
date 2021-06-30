package com.quanmin.djdata.scheduled.common;

import com.alibaba.fastjson.JSONObject;
import com.quanmin.djdata.scheduled.csgo.CsgoScheduled;
import com.quanmin.djdata.util.CurlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-11 15:44
 * @ClassName: com.quanmin.djdata.scheduled.team.TeamScheduled
 */
@Component
public class CommonScheduled {

    private static final Logger logger = LoggerFactory.getLogger(CsgoScheduled.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每隔2秒执行一次
    @Scheduled(fixedRate = 2000)
    //每天3：05执行
    //@Scheduled(cron = "0 05 03 ? * *")
    public void testTasks() {
        //System.out.println("定时任务执行时间11111111：" + dateFormat.format(new Date()));
    }

}
