package com.quanmin.djdata.team.controller;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.team.TeamVO;
import com.quanmin.djdata.team.service.ITeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 战队
 * @CreateDate: 2019-11-13 23:02
 * @ClassName: com.quanmin.djdata.team.controller.TeamController
 */
@RestController
@RequestMapping("/team")
@Api(value = "TeamController", tags = "战队")
public class TeamController {

    @Autowired
    private ITeamService iTeamService;

    /**
     * @author: ate
     * @description: 保存战队列表
     * @date: 2019/11/13 23:11
     * @param: [teamVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/insert")
    @ApiOperation(value = "保存战队列表", notes = "必传参数：offset=页码，limit=每页条数")
    public Object insert(TeamVO teamVO){
        return iTeamService.insert(teamVO);
    }

    /**
     * @author: ate
     * @description: 分页战队
     * @date: 2019/11/22 16:02
     * @param: [teamVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页战队", notes = "必传参数：pageNo=页码，pageSize=每页条数，game_id=游戏ID")
    public Object insert(TeamVO teamVO, PageVO pageVO){
        return iTeamService.page(teamVO, pageVO);
    }

}
