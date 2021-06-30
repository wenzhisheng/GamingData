package com.quanmin.djdata.game.dao;

import com.quanmin.djdata.pojo.game.GameSkillAliasVO;
import com.quanmin.djdata.pojo.game.GameSkillVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 16:27
 * @ClassName: com.quanmin.djdata.game.dao.IGameSkillDao
 */
@Repository
public interface IGameSkillAliasDao {

    /**
     * @author: ate
     * @description: 保存游戏技能
     * @date: 2019/11/13 16:31
     * @param: [gameRoleVO]
     * @return: int
     */
    int insert(@Param("vo") GameSkillAliasVO gameSkillAliasVO);

    /**
     * @author: ate
     * @description: 查询游戏技能
     * @date: 2019/11/13 16:24
     * @param: [gameSkillVO]
     * @return: com.quanmin.djdata.pojo.game.GameSkillVO
     */
    GameSkillAliasVO get(@Param("vo") GameSkillAliasVO gameSkillAliasVO);

    /**
     * @author: ate
     * @description: 列表游戏技能别名
     * @date: 2019/11/29 14:45
     * @param: [gameSkillAliasVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.game.GameSkillAliasVO>
     */
    List<GameSkillAliasVO> list(@Param("vo") GameSkillAliasVO gameSkillAliasVO);
}
