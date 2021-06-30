package com.quanmin.djdata.game.controller;

import com.quanmin.djdata.game.service.IGameEquipmentsService;
import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameEquipmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 15:43
 * @ClassName: com.quanmin.djdata.game.controller.GameEquipmentsController
 */
@RestController
@RequestMapping("/equipment")
@Api(value = "GameEquipmentsController", tags = "游戏装备")
public class GameEquipmentsController {

    @Autowired
    private IGameEquipmentsService iGameEquipmentsService;

    /**
     * @author: ate
     * @description: 保存游戏装备
     * @date: 2019/11/13 15:44
     * @param: [gameEquipmentVO]
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存游戏装备", notes = "必传参数：offset=页码，limit=每页条数")
    public Object insert(GameEquipmentVO gameEquipmentVO){
        return iGameEquipmentsService.insert(gameEquipmentVO);
    }

    /**
     * @author: ate
     * @description: 分页游戏装备
     * @date: 2019/11/22 16:31
     * @param: [gameEquipmentVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页游戏装备", notes = "必传参数：pageNo=页码，pageSize=每页条数，game_id=游戏ID")
    public Object insert(GameEquipmentVO gameEquipmentVO, PageVO pageVO){
        return iGameEquipmentsService.page(gameEquipmentVO, pageVO);
    }

}
