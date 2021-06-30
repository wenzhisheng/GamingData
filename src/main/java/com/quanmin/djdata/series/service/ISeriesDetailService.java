package com.quanmin.djdata.series.service;

import com.quanmin.djdata.pojo.series.SeriesDetailVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 12:57
 * @ClassName: com.quanmin.djdata.series.service.ISeriesService
 */
public interface ISeriesDetailService {

    /**
     * @author: ate
     * @description: 保存系列赛详情
     * @date: 2019/11/13 18:07
     * @param: [seriesDetailVO]
     * @return: java.lang.Object
     */
    Object insert(SeriesDetailVO seriesDetailVO);

    /**
     * @author: ate
     * @description: 获取系列赛详情
     * @date: 2019/11/30 14:49
     * @param: [seriesDetailVO]
     * @return: java.lang.Object
     */
    Object get(SeriesDetailVO seriesDetailVO);
}
