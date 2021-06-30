package com.quanmin.djdata.game.service;

import com.quanmin.djdata.pojo.base.PageVO;
import com.quanmin.djdata.pojo.game.GameSkillVO;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 16:28
 * @ClassName: com.quanmin.djdata.game.service.IGameSkillService
 */
public interface IGameSkillService {

    /**
     * @author: ate
     * @description: 保存游戏技能
     * @date: 2019/11/13 16:35
     * @param: [gameSkillVO]
     * @return: java.lang.Object
     */
    Object insert(GameSkillVO gameSkillVO);

    /**
     * @author: ate
     * @description: 分页游戏技能
     * @date: 2019/11/29 14:27
     * @param: [gameSkillVO, pageVO]
     * @return: java.lang.Object
     */
    Object page(GameSkillVO gameSkillVO, PageVO pageVO);
}
