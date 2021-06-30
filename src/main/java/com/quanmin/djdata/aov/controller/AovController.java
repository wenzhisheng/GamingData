package com.quanmin.djdata.aov.controller;

import com.quanmin.djdata.aov.service.IAovService;
import com.quanmin.djdata.pojo.aov.AovVO;
import com.quanmin.djdata.pojo.base.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 王者荣耀赛果
 * @CreateDate: 2019-11-14 16:12
 * @ClassName: com.quanmin.djdata.aov.controller.AovController
 */
@RestController
@RequestMapping("/aov")
@Api(value = "AovController", tags = "王者荣耀赛果")
public class AovController {

    @Autowired
    private IAovService iAovService;

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果
     * @date: 2019/11/14 16:14
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存王者荣耀赛果", notes = "可选参数：series_id=系列赛ID")
    public Object insert(AovVO aovVO){
        return iAovService.insert(aovVO);
    }

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果战队信息
     * @date: 2019/11/27 18:26
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo")
    @ApiOperation(value = "保存王者荣耀赛果战队信息", notes = "必传参数：无")
    public Object insertTeamInfo(AovVO aovVO){
        return iAovService.insertTeamInfo(aovVO);
    }

    /**
     * @author: ate
     * @description: 保存王者荣耀赛果战队信息依据第三方
     * @date: 2019/11/30 13:09
     * @param: [aovVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo2")
    @ApiOperation(value = "保存王者荣耀赛果战队信息依据第三方", notes = "必传参数：无")
    public Object insertTeamInfo2(AovVO aovVO){
        return iAovService.insertTeamInfo2(aovVO);
    }

    /**
     * @author: ate
     * @description: 分页王者荣耀赛果
     * @date: 2019/11/19 10:18
     * @param: [aovVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页王者荣耀赛果", notes = "必传参数：pageNo=页码，pageSize=每页条数，series_id=系列赛ID")
    public Object page(AovVO aovVO, PageVO pageVO){
        return iAovService.page(aovVO, pageVO);
    }

}
