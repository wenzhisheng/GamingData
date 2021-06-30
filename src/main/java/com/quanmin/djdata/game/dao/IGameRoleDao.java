package com.quanmin.djdata.game.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 15:09
 * @ClassName: com.quanmin.djdata.game.dao.IGameRoleDao
 */
@Repository
public interface IGameRoleDao {

    /**
     * @author: ate
     * @description: 保存游戏角色
     * @date: 2019/11/13 15:20
     * @param: [gameRoleVO]
     * @return: java.lang.Object
     */
    int insert(@Param("vo") GameRoleVO gameRoleVO);

    /**
     * @author: ate
     * @description: 查询游戏角色
     * @date: 2019/11/13 15:24
     * @param: [gameRoleVO]
     * @return: com.quanmin.djdata.pojo.game.GameRoleVO
     */
    GameRoleVO get(@Param("vo") GameRoleVO gameRoleVO);

    /**
     * @author: ate
     * @description: 分页游戏英雄
     * @date: 2019/11/29 14:14
     * @param: [page, gameRoleVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.game.GameRoleVO>
     */
    IPage<GameRoleVO> page(Page<GameRoleVO> page, @Param("vo") GameRoleVO gameRoleVO);
}
