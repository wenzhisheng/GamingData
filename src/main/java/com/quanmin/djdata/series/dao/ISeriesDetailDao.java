package com.quanmin.djdata.series.dao;

import com.quanmin.djdata.pojo.series.SeriesDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 12:57
 * @ClassName: com.quanmin.djdata.series.dao.ISeriesDao
 */
@Repository
public interface ISeriesDetailDao {

    /**
     * @author: ate
     * @description: 保存系列赛列表
     * @date: 2019/11/13 18:13
     * @param: [seriesDetailVO]
     * @return: int
     */
    int insert(@Param("vo") SeriesDetailVO seriesDetailVO);

    /**
     * @author: ate
     * @description: 查询是否存在
     * @date: 2019/11/13 18:18
     * @param: [seriesDetailVO]
     * @return: com.quanmin.djdata.pojo.series.SeriesVO
     */
    SeriesDetailVO get(@Param("vo") SeriesDetailVO seriesDetailVO);
}
