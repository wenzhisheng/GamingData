package com.quanmin.djdata.game.controller;

import com.quanmin.djdata.game.service.IGameRoleService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import com.quanmin.djdata.pojo.game.GameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: 游戏角色
 * @CreateDate: 2019-11-13 15:08
 * @ClassName: com.quanmin.djdata.game.controller.GameRoleController
 */
@RestController
@RequestMapping("/role")
@Api(value = "GameRoleController", tags = "游戏角色")
public class GameRoleController {

    @Autowired
    private IGameRoleService iGameRoleService;

    /**
     * @author: ate
     * @description: 保存游戏角色
     * @date: 2019/11/13 15:09
     * @param: [gamesVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存游戏角色", notes = "必传参数：offset=页码，limit=每页条数")
    public Object insert(GameRoleVO gameRoleVO){
        return iGameRoleService.insert(gameRoleVO);
    }

    /**
     * @author: ate
     * @description: 分页游戏英雄
     * @date: 2019/11/29 14:12
     * @param: [gameRoleVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页游戏英雄", notes = "可选参数：pageNo=页码，pageSize=每页条数")
    public Object insert(GameRoleVO gameRoleVO, PageVO pageVO){
        return iGameRoleService.page(gameRoleVO, pageVO);
    }

}
