package com.quanmin.djdata.scheduled.course;

import com.quanmin.djdata.course.service.ICourseService;
import com.quanmin.djdata.pojo.course.CourseVO;
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
public class CourseScheduled {

    @Autowired
    private ICourseService iCourseService;

    //每天3：05执行
    @Scheduled(cron = "0 45 02 ? * *")
    public void testTasks() {
        iCourseService.insert(new CourseVO());
    }

}
