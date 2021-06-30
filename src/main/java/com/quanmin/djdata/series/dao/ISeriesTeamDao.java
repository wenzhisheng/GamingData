package com.quanmin.djdata.series.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.series.SeriesTeamVO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 12:57
 * @ClassName: com.quanmin.djdata.series.dao.ISeriesDao
 */
@Repository
public interface ISeriesTeamDao {

    /**
     * @author: ate
     * @description: 保存系列赛列表团队
     * @date: 2019/11/13 13:13
     * @param: [seriesVO]
     * @return: int
     */
    int insert(@Param("vo") SeriesTeamVO SeriesTeamVO);

    /**
     * @author: ate
     * @description: 查询是否存在
     * @date: 2019/11/13 13:18
     * @param: [seriesVO]
     * @return: com.quanmin.djdata.pojo.series.SeriesVO
     */
    SeriesTeamVO get(@Param("vo") SeriesTeamVO SeriesTeamVO);

    /**
     * @author: ate
     * @description: 系列赛战队分页
     * @date: 2019/11/18 17:55
     * @param: [page, seriesVO1]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.series.SeriesVO>
     */
    IPage<SeriesVO> page(Page<SeriesVO> page, @Param("vo") SeriesVO seriesVO1);

    /**
     * @author: ate
     * @description: 系列赛战队列表
     * @date: 2019/11/18 17:59
     * @param: [seriesVO1]
     * @return: java.util.List<com.quanmin.djdata.pojo.series.SeriesTeamVO>
     */
    List<SeriesTeamVO> list(@Param("vo") SeriesVO seriesVO1);
}
