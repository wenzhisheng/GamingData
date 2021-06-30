package com.quanmin.djdata.course.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.course.CourseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 11:59
 * @ClassName: com.quanmin.djdata.course.dao.ICourseDao
 */
@Repository
public interface ICourseDao {

    /**
     * @author: ate
     * @description: 保存赛程列表
     * @date: 2019/11/13 12:15
     * @param: [courseVO]
     * @return: int
     */
    int insert(@Param("vo") CourseVO courseVO);

    /**
     * @author: ate
     * @description: 获取赛程
     * @date: 2019/11/13 12:27
     * @param: [courseVO]
     * @return: com.quanmin.djdata.pojo.course.CourseVO
     */
    CourseVO get(@Param("vo") CourseVO courseVO);

    /**
     * @author: ate
     * @description: 分页联赛
     * @date: 2019/11/30 15:10
     * @param: [page, courseVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.course.CourseVO>
     */
    IPage<CourseVO> page(Page<CourseVO> page, @Param("vo") CourseVO courseVO);
}
