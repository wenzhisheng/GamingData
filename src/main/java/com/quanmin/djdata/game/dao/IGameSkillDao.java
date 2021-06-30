package com.quanmin.djdata.game.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import com.quanmin.djdata.pojo.game.GameSkillVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 16:27
 * @ClassName: com.quanmin.djdata.game.dao.IGameSkillDao
 */
@Repository
public interface IGameSkillDao {

    /**
     * @author: ate
     * @description: 保存游戏技能
     * @date: 2019/11/13 16:31
     * @param: [gameRoleVO]
     * @return: int
     */
    int insert(@Param("vo") GameSkillVO gameSkillVO);

    /**
     * @author: ate
     * @description: 查询游戏技能
     * @date: 2019/11/13 16:24
     * @param: [gameSkillVO]
     * @return: com.quanmin.djdata.pojo.game.GameSkillVO
     */
    GameSkillVO get(@Param("vo") GameSkillVO gameSkillVO);

    /**
     * @author: ate
     * @description: 分页游戏技能
     * @date: 2019/11/29 14:37
     * @param: [page, gameSkillVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.game.GameSkillVO>
     */
    IPage<GameSkillVO> page(Page<GameRoleVO> page, @Param("vo") GameSkillVO gameSkillVO);
}
