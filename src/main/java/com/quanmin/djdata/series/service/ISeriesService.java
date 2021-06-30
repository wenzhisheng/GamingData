package com.quanmin.djdata.series.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.series.SeriesVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 12:57
 * @ClassName: com.quanmin.djdata.series.service.ISeriesService
 */
public interface ISeriesService {

    /**
     * @author: ate
     * @description: 保存系列赛列表
     * @date: 2019/11/13 13:07
     * @param: [seriesVO]
     * @return: java.lang.Object
     */
    Object insert(SeriesVO seriesVO);

    /**
     * @author: ate
     * @description: 系列赛分页
     * @date: 2019/11/18 16:15
     * @param: [seriesVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(SeriesVO seriesVO, PageVO pageVO);
}
