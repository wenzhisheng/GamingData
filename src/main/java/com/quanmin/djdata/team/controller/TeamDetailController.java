package com.quanmin.djdata.team.controller;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.team.TeamDetailVO;
import com.quanmin.djdata.team.service.ITeamDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 战队详情
 * @CreateDate: 2019-11-14 14:13
 * @ClassName: com.quanmin.djdata.team.controller.TeamDetailController
 */
@RestController
@RequestMapping("/teamDetail")
@Api(value = "TeamController", tags = "战队详情")
public class TeamDetailController {

    @Autowired
    private ITeamDetailService iTeamDetailService;

    /**
     * @author: ate
     * @description: 保存战队详情
     * @date: 2019/11/14 14:13
     * @param: [teamVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存战队详情", notes = "必传参数：无")
    public Object insert(TeamDetailVO teamDetailVO){
        return iTeamDetailService.insert(teamDetailVO);
    }

    /**
     * @author: ate
     * @description: 分页战队详情
     * @date: 2019/11/29 14:07
     * @param: [teamDetailVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页战队详情", notes = "必传参数：pageNo=页码，pageSize=每页条数，game_id=游戏ID，id=战队ID，game_type=游戏类型")
    public Object insert(TeamDetailVO teamDetailVO, PageVO pageVO){
        return iTeamDetailService.page(teamDetailVO, pageVO);
    }

    /**
     * @author: ate
     * @description: 获取战队详情
     * @date: 2019/11/30 15:15
     * @param: [teamDetailVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/get")
    @ApiOperation(value = "获取战队详情", notes = "必传参数：id=战队ID")
    public Object get(TeamDetailVO teamDetailVO){
        return iTeamDetailService.get(teamDetailVO);
    }

}
