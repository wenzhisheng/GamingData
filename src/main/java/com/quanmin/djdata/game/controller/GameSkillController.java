package com.quanmin.djdata.game.controller;

import com.quanmin.djdata.game.service.IGameService;
import com.quanmin.djdata.game.service.IGameSkillService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import com.quanmin.djdata.pojo.game.GameSkillVO;
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
 * @Description: 游戏技能
 * @CreateDate: 2019-11-13 16:25
 * @ClassName: com.quanmin.djdata.game.controller.GameSkillController
 */
@RestController
@RequestMapping("/skill")
@Api(value = "GameSkillController", tags = "游戏技能")
public class GameSkillController {

    @Autowired
    private IGameSkillService iGameSkillService;

    /**
     * @author: ate
     * @description: 保存游戏技能
     * @date: 2019/11/13 16:27
     * @param: [gamesVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存游戏技能", notes = "必传参数：offset=页码，limit=每页条数")
    public Object insert(GameSkillVO gameSkillVO){
        return iGameSkillService.insert(gameSkillVO);
    }

    /**
     * @author: ate
     * @description: 分页游戏技能
     * @date: 2019/11/29 14:27
     * @param: [gameSkillVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页游戏技能", notes = "可选参数：pageNo=页码，pageSize=每页条数，game_id=游戏ID，game_role_id=游戏角色ID，game_equipment_id=游戏装备ID")
    public Object insert(GameSkillVO gameSkillVO, PageVO pageVO){
        return iGameSkillService.page(gameSkillVO, pageVO);
    }

}
