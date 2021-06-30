package com.quanmin.djdata.competitionarea.controller;

import com.quanmin.djdata.competitionarea.service.ICompetitionAreaService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.competitionarea.CompetitionAreaVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ate
 * @Description: 赛区列表
 * @CreateDate: 2019-11-12 10:39
 * @ClassName: com.quanmin.djdata.competitionarea.CompetitionAreaController
 */
@RestController
@RequestMapping("/areas")
@Api(value = "CompetitionAreaController", tags = "赛区列表")
public class CompetitionAreaController {

    private static final Logger logger = LoggerFactory.getLogger(com.quanmin.djdata.game.controller.GameController.class);

    @Autowired
    private ICompetitionAreaService iCompetitionAreaService;

    /**
     * @author: ate
     * @description: 保存赛区列表
     * @date: 2019/11/12 10:42
     * @param: [gamesVO]
     * @return: java.lang.Object
     */
    @ResponseBody
    @GetMapping("/insert")
    @ApiOperation(value = "保存赛区列表", notes = "必传参数：无")
    public Object insert(CompetitionAreaVO competitionAreaVO){
        return iCompetitionAreaService.insert(competitionAreaVO);
    }

    /**
     * @author: ate
     * @description: 分页赛区
     * @date: 2019/11/30 15:03
     * @param: [competitionAreaVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页赛区", notes = "必传参数：pageNo=页码，pageSize=每页条数")
    public Object insert(CompetitionAreaVO competitionAreaVO, PageVO pageVO){
        return iCompetitionAreaService.page(competitionAreaVO, pageVO);
    }

}
