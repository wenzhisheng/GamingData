package com.quanmin.djdata.game.controller;

import com.quanmin.djdata.game.service.IGameService;
import com.quanmin.djdata.pojo.base.PageVO;
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
 * @Description: 游戏列表
 * @CreateDate: 2019-11-08 14:33
 * @ClassName: com.quanmin.djdata.csgo.controller.CsgoController
 */
@RestController
@RequestMapping("/game")
@Api(value = "Game Controller", tags = "游戏列表")
public class GameController {

    @Autowired
    private IGameService iGameService;

    /**
     * @author: ate
     * @description: 保存游戏列表
     * @date: 2019/11/8 15:49
     * @param: []
     * @return: java.lang.Object
     */
    @GetMapping("/insert")
    @ApiOperation(value = "保存游戏列表", notes = "必传参数：无")
    public Object insert(GameVO gamesVO){
        return iGameService.insert(gamesVO);
    }

    /**
     * @author: ate
     * @description: 分页游戏
     * @date: 2019/11/22 16:25
     * @param: [gamesVO, pageVO]
     * @return: java.lang.Object
     */
    @CrossOrigin
    @GetMapping("/page")
    @ApiOperation(value = "分页游戏", notes = "必传参数：pageNo=页码，pageSize=每页条数")
    public Object insert(GameVO gamesVO, PageVO pageVO){
        return iGameService.page(gamesVO, pageVO);
    }

}
