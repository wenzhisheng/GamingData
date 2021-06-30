package com.quanmin.djdata.game.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-08 15:50
 * @ClassName: com.quanmin.djdata.csgo.service.ICsgoService
 */
public interface IGameService {

    /**
     * @author: ate
     * @description: 保存游戏列表
     * @date: 2019/11/11 16:50
     * @param: []
     * @return: java.lang.Object
     */
    Object insert(GameVO gamesVO);

    /**
     * @author: ate
     * @description: 根据ID和名称查询游戏是否存在
     * @date: 2019/11/12 10:23
     * @param: [gamesVO]
     * @return: com.quanmin.djdata.pojo.game.GamesVO
     */
    GameVO get(GameVO gamesVO);

    /**
     * @author: ate
     * @description: 分页游戏
     * @date: 2019/11/22 16:25
     * @param: [gamesVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(GameVO gamesVO, PageVO pageVO);
}
