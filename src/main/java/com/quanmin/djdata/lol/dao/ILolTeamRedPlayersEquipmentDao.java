package com.quanmin.djdata.lol.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-14 16:32
 * @ClassName: com.quanmin.djdata.lol.dao.ILolTeamRedPlayersEquipmentDao
 */
@Repository
public interface ILolTeamRedPlayersEquipmentDao {

    /**
     * @author: ate
     * @description: 保存英雄联盟赛果战队玩家装备
     * @date: 2019/11/20 12:59
     * @param: [lolTeamPlayersEquipmentVO]
     * @return: int
     */
    int insert(@Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersEquipmentVO);

    /**
     * @author: ate
     * @description: 查询英雄联盟赛果战队玩家装备
     * @date: 2019/11/20 13:40
     * @param: [lolTeamPlayersVO]
     * @return: boolean
     */
    LolTeamPlayersEquipmentVO get(@Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersVO);

    /**
     * @author: ate
     * @description: 分页英雄联盟赛果战队玩家装备
     * @date: 2019/11/20 13:16
     * @param: [page, lolTeamPlayersVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.lol.LolTeamPlayersVO>
     */
    IPage<LolTeamPlayersEquipmentVO> page(Page<LolTeamPlayersEquipmentVO> page, @Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersVO);

    /**
     * @author: ate
     * @description: 红队队员装备
     * @date: 2019/11/21 19:29
     * @param: [equipmentVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO>
     */
    List<LolTeamPlayersEquipmentVO> list(@Param("vo") LolTeamPlayersEquipmentVO equipmentVO);
}
