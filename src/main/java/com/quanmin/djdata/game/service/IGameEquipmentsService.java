package com.quanmin.djdata.game.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameEquipmentVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 15:10
 * @ClassName: com.quanmin.djdata.game.service.IGameRoleService
 */
public interface IGameEquipmentsService {

    /**
     * @author: ate
     * @description: 保存游戏装备
     * @date: 2019/11/13 15:20
     * @param: [gameEquipmentVO]
     * @return: java.lang.Object
     */
    Object insert(GameEquipmentVO gameEquipmentVO);

    /**
     * @author: ate
     * @description: 分页游戏装备
     * @date: 2019/11/22 16:31
     * @param: [gameEquipmentVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(GameEquipmentVO gameEquipmentVO, PageVO pageVO);
}
