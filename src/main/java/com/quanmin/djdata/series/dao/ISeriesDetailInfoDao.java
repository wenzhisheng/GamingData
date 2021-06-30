package com.quanmin.djdata.series.dao;

import com.quanmin.djdata.pojo.series.SeriesDetailInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 12:57
 * @ClassName: com.quanmin.djdata.series.dao.ISeriesDetailInfoDao
 */
@Repository
public interface ISeriesDetailInfoDao {

    /**
     * @author: ate
     * @description: 保存系列赛列表
     * @date: 2019/11/13 18:13
     * @param: [seriesDetailInfoVO]
     * @return: int
     */
    int insert(@Param("vo") SeriesDetailInfoVO seriesDetailInfoVO);

    /**
     * @author: ate
     * @description: 查询是否存在
     * @date: 2019/11/13 18:18
     * @param: [seriesDetailInfoVO]
     * @return: com.quanmin.djdata.pojo.series.SeriesVO
     */
    SeriesDetailInfoVO get(@Param("vo") SeriesDetailInfoVO seriesDetailInfoVO);

}
