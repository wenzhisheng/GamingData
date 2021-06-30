package com.quanmin.djdata.series.controller;

import com.quanmin.djdata.pojo.series.SeriesDetailVO;
import com.quanmin.djdata.series.service.ISeriesDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ate
 * @Description: 系列赛详情
 * @CreateDate: 2019-11-13 18:01
 * @ClassName: com.quanmin.djdata.series.controller.SeriesTeamComtroller
 */
@RestController
@RequestMapping("/seriesDetail")
@Api(value = "SeriesDetailComtroller", tags = "系列赛详情")
public class SeriesDetailComtroller {

    @Autowired
    private ISeriesDetailService iSeriesDetailService;

    /**
     * @author: ate
     * @description: 保存系列赛详情
     * @date: 2019/11/13 18:05
     * @param: [seriesVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存系列赛详情", notes = "必传参数：无")
    public Object insert(SeriesDetailVO seriesDetailVO){
        return iSeriesDetailService.insert(seriesDetailVO);
    }

    /**
     * @author: ate
     * @description: 获取系列赛详情
     * @date: 2019/11/30 14:49
     * @param: [seriesDetailVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/get")
    @ApiOperation(value = "获取系列赛详情", notes = "必传参数：series_id=系列赛ID")
    public Object get(SeriesDetailVO seriesDetailVO){
        return iSeriesDetailService.get(seriesDetailVO);
    }

}
