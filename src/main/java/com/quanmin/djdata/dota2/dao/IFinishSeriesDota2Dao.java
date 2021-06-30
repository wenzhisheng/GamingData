package com.quanmin.djdata.dota2.dao;

import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.finish.FinishSeriesVO;
import com.quanmin.djdata.pojo.series.SeriesTeamVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 21:34
 * @ClassName: com.quanmin.djdata.aov.dao.IFinishSeries
 */
@Repository
public interface IFinishSeriesDota2Dao {

    /**
     * @author: ate
     * @description: 保存赛果系列赛
     * @date: 2019/11/14 21:35
     * @param: [finishSeriesVO]
     * @return: int
     */
    int insert(@Param("vo") FinishSeriesVO finishMatchAovVO);

    /**
     * @author: ate
     * @description: 查询赛果系列赛
     * @date: 2019/11/14 21:36
     * @param: [finishSeriesVO]
     * @return: com.quanmin.djdata.pojo.finish.FinishSeriesVO
     */
    FinishSeriesVO get(@Param("vo") FinishSeriesVO finishSeriesVO);

    /**
     * @author: ate
     * @description:
     * @date: 2019/11/19 10:42
     * @param: [aovVO1]
     * @return: java.util.List<com.quanmin.djdata.pojo.series.SeriesTeamVO>
     */
    List<SeriesTeamVO> list(@Param("vo") AovVO aovVO1);
}
