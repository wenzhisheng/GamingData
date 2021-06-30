package com.quanmin.djdata.dota2.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-23 16:28
 * @ClassName: com.quanmin.djdata.dota2.dao.IDota2PlayersEquipmentDao
 */
@Repository
public interface IDota2PlayersEquipmentDao {

    /**
     * @author: ate
     * @description: 保存dota2赛果玩家装备
     * @date: 2019/11/23 16:04
     * @param: [lolTeamPlayersEquipmentVO]
     * @return: int
     */
    int insert(@Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersEquipmentVO);

    /**
     * @author: ate
     * @description: 查询dota2赛果玩家装备
     * @date: 2019/11/23 16:04
     * @param: [lolTeamPlayersEquipmentVO]
     * @return: com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO
     */
    LolTeamPlayersEquipmentVO get(@Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersEquipmentVO);

    /**
     * @author: ate
     * @description: 是否存在
     * @date: 2019/11/23 16:33
     * @param: [lolTeamPlayersEquipmentVO]
     * @return: com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO
     */
    LolTeamPlayersEquipmentVO isExist(@Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersEquipmentVO);

    /**
     * @author: ate
     * @description: 分页dota2VO赛果玩家装备
     * @date: 2019/11/23 16:04
     * @param: [page, lolTeamPlayersEquipmentVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO>
     */
    IPage<LolTeamPlayersEquipmentVO> page(Page<LolTeamPlayersEquipmentVO> page, @Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersEquipmentVO);

    /**
     * @author: ate
     * @description: 列表dota2VO赛果玩家装备
     * @date: 2019/11/23 16:04
     * @param: [lolTeamPlayersEquipmentVO]
     * @return: java.util.List<com.quanmin.djdata.pojo.lol.LolTeamPlayersEquipmentVO>
     */
    List<LolTeamPlayersEquipmentVO> list(@Param("vo") LolTeamPlayersEquipmentVO lolTeamPlayersEquipmentVO);
}
