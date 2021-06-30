package com.quanmin.djdata.course.controller;

import com.quanmin.djdata.course.service.ICourseService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.course.CourseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 赛程列表
 * @CreateDate: 2019-11-13 11:59
 * @ClassName: com.quanmin.djdata.course.controller.CourseController
 */
@RestController
@RequestMapping("/courses")
@Api(value = "CourseController", tags = "赛程列表")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;

    /**
     * @author: ate
     * @description: 保存联赛列表
     * @date: 2019/11/12 20:08
     * @param: [gamesVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存联赛列表", notes = "必传参数：无")
    public Object insert(CourseVO courseVO){
        return iCourseService.insert(courseVO);
    }

    /**
     * @author: ate
     * @description: 分页联赛
     * @date: 2019/11/30 15:09
     * @param: [courseVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页联赛", notes = "必传参数：pageNo=页码，pageSize=每页条数")
    public Object insert(CourseVO courseVO, PageVO pageVO){
        return iCourseService.page(courseVO, pageVO);
    }

}
