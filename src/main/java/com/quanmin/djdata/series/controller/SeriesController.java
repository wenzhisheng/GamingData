package com.quanmin.djdata.series.controller;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.series.SeriesVO;
import com.quanmin.djdata.series.service.ISeriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 系列赛列表
 * @CreateDate: 2019-11-13 12:56
 * @ClassName: com.quanmin.djdata.series.controller.SeriesController
 */
@RestController
@RequestMapping("/series")
@Api(value = "SeriesController", tags = "系列赛列表")
public class SeriesController {

    @Autowired
    private ISeriesService iSeriesService;

    /**
     * @author: ate
     * @description: 系列赛分页
     * @date: 2019/11/18 16:13
     * @param: [seriesVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "系列赛分页", notes = "必传参数：pageNo=页码，pageSize=每页条数，status=状态（1：未开始，2：进行中，3：结束）")
    public Object page(SeriesVO seriesVO, PageVO pageVO){
        return iSeriesService.page(seriesVO, pageVO);
    }

    /**
     * @author: ate
     * @description: 保存系列赛列表
     * @date: 2019/11/13 13:06
     * @param: [seriesVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存系列赛列表", notes = "必传参数：begin_time=开始时间，end_time=结束时间，offset=页码，limit=每页条数，")
    public Object insert(SeriesVO seriesVO){
        return iSeriesService.insert(seriesVO);
    }

}
