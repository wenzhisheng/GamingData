package com.quanmin.djdata.team.controller;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.team.TeamRankVO;
import com.quanmin.djdata.team.service.ITeamRankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 战队排名
 * @CreateDate: 2019-11-14 13:15
 * @ClassName: com.quanmin.djdata.team.controller.TeamRankController
 */
@RestController
@RequestMapping("/rank")
@Api(value = "TeamRankController", tags = "战队排名")
public class TeamRankController {

    @Autowired
    private ITeamRankService iTeamRankService;

    /**
     * @author: ate
     * @description: 保存战队排名
     * @date: 2019/11/14 13:25
     * @param: [teamRankVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存战队排名", notes = "可选参数：offset=页码，limit=每页条数")
    public Object insert(TeamRankVO teamRankVO){
        return iTeamRankService.insert(teamRankVO);
    }

    /**
     * @author: ate
     * @description: 分页战队排名
     * @date: 2019/11/22 16:36
     * @param: [teamRankVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页战队排名", notes = "必传参数：pageNo=页码，pageSize=每页条数，game_id=游戏ID")
    public Object insert(TeamRankVO teamRankVO, PageVO pageVO){
        return iTeamRankService.page(teamRankVO, pageVO);
    }

}
