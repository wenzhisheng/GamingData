package com.quanmin.djdata.game.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 15:10
 * @ClassName: com.quanmin.djdata.game.service.IGameRoleService
 */
public interface IGameRoleService {

    /**
     * @author: ate
     * @description: 保存游戏角色
     * @date: 2019/11/13 15:20
     * @param: [gameRoleVO]
     * @return: java.lang.Object
     */
    Object insert(GameRoleVO gameRoleVO);

    /**
     * @author: ate
     * @description: 分页游戏英雄
     * @date: 2019/11/29 14:13
     * @param: [gameRoleVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(GameRoleVO gameRoleVO, PageVO pageVO);
}
