package com.quanmin.djdata.game.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.game.GameEquipmentVO;
import com.quanmin.djdata.pojo.game.GameRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-13 15:09
 * @ClassName: com.quanmin.djdata.game.dao.IGameEquipmentsDao
 */
@Repository
public interface IGameEquipmentsDao {

    /**
     * @author: ate
     * @description: 保存游戏装备
     * @date: 2019/11/13 15:20
     * @param: [gameEquipmentVO]
     * @return: java.lang.Object
     */
    int insert(@Param("vo") GameEquipmentVO gameEquipmentVO);

    /**
     * @author: ate
     * @description: 查询游戏装备
     * @date: 2019/11/13 15:24
     * @param: [gameEquipmentVO]
     * @return: com.quanmin.djdata.pojo.game.GameRoleVO
     */
    GameEquipmentVO get(@Param("vo") GameEquipmentVO gameEquipmentVO);

    /**
     * @author: ate
     * @description: 分页游戏装备
     * @date: 2019/11/22 16:32
     * @param: [page, gameEquipmentVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.game.GameEquipmentVO>
     */
    IPage<GameEquipmentVO> page(Page<GameEquipmentVO> page, @Param("vo") GameEquipmentVO gameEquipmentVO);
}
