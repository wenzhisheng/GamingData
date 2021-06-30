package com.quanmin.djdata.course.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.course.CourseVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 11:59
 * @ClassName: com.quanmin.djdata.course.service.ICourseService
 */
public interface ICourseService {

    /**
     * @author: ate
     * @description: 保存联赛列表
     * @date: 2019/11/13 12:07
     * @param: [courseVO]
     * @return: java.lang.Object
     */
    Object insert(CourseVO courseVO);

    /**
     * @author: ate
     * @description: 分页联赛
     * @date: 2019/11/30 15:09
     * @param: [courseVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(CourseVO courseVO, PageVO pageVO);
}
