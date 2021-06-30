package com.quanmin.djdata.league.comtroller;

import com.quanmin.djdata.league.service.ILeagueService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.league.LeagueVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 联赛列表
 * @CreateDate: 2019-11-12 18:26
 * @ClassName: com.quanmin.djdata.leagues.comtroller.LeagueController
 */
@RestController
@RequestMapping("/leagues")
@Api(value = "LeagueController", tags = "联赛列表")
public class LeagueController {

    @Autowired
    private ILeagueService iLeagueService;

    /**
     * @author: ate
     * @description: 保存联赛列表
     * @date: 2019/11/12 20:08
     * @param: [gamesVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存联赛列表", notes = "必传参数：无")
    public Object insert(LeagueVO leagueVO){
        return iLeagueService.insert(leagueVO);
    }

    /**
     * @author: ate
     * @description: 分页联赛
     * @date: 2019/11/30 14:54
     * @param: [leagueVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页联赛", notes = "必传参数：pageNo=页码，pageSize=每页条数")
    public Object insert(LeagueVO leagueVO, PageVO pageVO){
        return iLeagueService.page(leagueVO, pageVO);
    }

}
