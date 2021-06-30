package com.quanmin.djdata.series.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface ISeriesDao {

    /**
     * @author: ate
     * @description: 保存系列赛列表
     * @date: 2019/11/13 13:13
     * @param: [seriesVO]
     * @return: int
     */
    int insert(@Param("vo") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 查询是否存在
     * @date: 2019/11/13 13:18
     * @param: [seriesVO]
     * @return: com.quanmin.djdata.pojo.series.SeriesVO
     */
    SeriesVO get(@Param("vo") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 是否存在
     * @date: 2019/11/26 10:22
     * @param: [seriesVO]
     * @return: com.quanmin.djdata.pojo.series.SeriesVO
     */
    SeriesVO isExist(@Param("vo") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 系列赛列表
     * @date: 2019/11/13 18:23
     * @param: []
     * @return: java.util.List<com.quanmin.djdata.pojo.series.SeriesVO>
     */
    List<SeriesVO> list(@Param("vo") SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 系列赛分页
     * @date: 2019/11/18 16:18
     * @param: [page, seriesVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.series.SeriesVO>
     */
    IPage<SeriesVO> pageLol(Page<SeriesVO> page, @Param("vo") SeriesVO seriesVO);
    IPage<SeriesVO> pageAov(Page<SeriesVO> page, @Param("vo") SeriesVO seriesVO);
    IPage<SeriesVO> pageDota2(Page<SeriesVO> page, @Param("vo") SeriesVO seriesVO);
    IPage<SeriesVO> pageCsgo(Page<SeriesVO> page, @Param("vo") SeriesVO seriesVO);

}
