package com.quanmin.djdata.lol.controller;

import com.quanmin.djdata.lol.service.ILolService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.lol.LolVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 英雄联盟赛果
 * @CreateDate: 2019-11-14 16:31
 * @ClassName: com.quanmin.djdata.lol.controller.LolController
 */
@RestController
@RequestMapping("/lol")
@Api(value = "LolController", tags = "英雄联盟赛果")
public class LolController {

    @Autowired
    private ILolService iLolService;

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果
     * @date: 2019/11/14 16:14
     * @param: [gamesVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存英雄联盟赛果", notes = "可选参数：series_id=系列赛ID")
    public Object insert(LolVO lolVO){
        return iLolService.insert(lolVO);
    }

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果战队信息
     * @date: 2019/11/30 12:54
     * @param: [lolVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo")
    @ApiOperation(value = "保存英雄联盟赛果战队信息", notes = "必传参数：无")
    public Object insertTeamInfo(LolVO lolVO){
        return iLolService.insertTeamInfo(lolVO);
    }

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果依据第三方
     * @date: 2019/11/30 14:39
     * @param: [dota2VO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insertTeamInfo2")
    @ApiOperation(value = "保存英雄联盟赛果依据第三方", notes = "必传参数：无")
    public Object insertTeamInfo2(LolVO lolVO){
        return iLolService.insertTeamInfo2(lolVO);
    }

    /**
     * @author: ate
     * @description: 分页英雄联盟赛果
     * @date: 2019/11/20 11:48
     * @param: [lolVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页英雄联盟赛果", notes = "必传参数：pageNo=页码，pageSize=每页条数，series_id=系列赛ID")
    public Object page(LolVO lolVO, PageVO pageVO){
        return iLolService.page(lolVO, pageVO);
    }

}
